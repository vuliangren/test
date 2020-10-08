package com.welearn.export;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.welearn.dictionary.export.ExportTaskStatusConst;
import com.welearn.dictionary.notify.NoticeMethodConst;
import com.welearn.dictionary.notify.NoticeRefTypeConst;
import com.welearn.dictionary.notify.NoticeTypeConst;
import com.welearn.entity.po.export.ExportTask;
import com.welearn.entity.po.notify.Notice;
import com.welearn.entity.po.storage.UploadRecord;
import com.welearn.entity.vo.request.storage.UploadToken;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.notify.NoticeFeignClient;
import com.welearn.feign.storage.QiniuFileFeignClient;
import com.welearn.service.ExportTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

/**
 * Description : 导出任务消费帮助类
 * Created by Setsuna Jin on 2019/6/11.
 */
@Slf4j
@Service
public class ExportTaskConsumerHelper {

    @Autowired
    private Auth auth;

    @Autowired
    private ExportTaskService exportTaskService;

    @Autowired
    private QiniuFileFeignClient qiniuFileFeignClient;

    @Autowired
    private NoticeFeignClient noticeFeignClient;

    public ExportTask selectTask(String exportTaskId) {
        ExportTask exportTask = exportTaskService.select(exportTaskId);
        if (Objects.isNull(exportTask) || !exportTask.getIsEnable())
            throw new AmqpRejectAndDontRequeueException("exportTaskId 非法 或 任务已被取消");
        return exportTask;
    }

    public void finishTask(ExportTask exportTask, String response, Long startAt) {
        UploadRecord uploadRecord = JSON.parseObject(response).getJSONObject("data").getObject("fileInfo", UploadRecord.class);
        exportTask.setResultSize(uploadRecord.getSize());
        exportTask.setFinishedAt(new Date());
        exportTask.setResultJson(JSON.toJSONString(uploadRecord));
        exportTask.setStatus(ExportTaskStatusConst.SUCCESS.ordinal());
        exportTaskService.update(exportTask);
        log.info("导出任务:{} 执行完成, 导出耗时: {} ms", exportTask.getName(), System.currentTimeMillis() - startAt);
        this.sendNotice(exportTask);
    }

    public void finishTask(ExportTask exportTask, String resultJson, String remark) {
        exportTask.setFinishedAt(new Date());
        exportTask.setResultJson(resultJson);
        if (StringUtils.isNotBlank(remark))
            exportTask.setRemark(remark);
        exportTask.setStatus(ExportTaskStatusConst.SUCCESS.ordinal());
        exportTaskService.update(exportTask);
        this.sendNotice(exportTask);
    }

    public void taskFailed(ExportTask exportTask, String remark) {
        try {
            if (StringUtils.isNotBlank(remark)) {
                if (remark.length() > 250) {
                    exportTask.setRemark(remark.substring(0, 250) + "...");
                } else {
                    exportTask.setRemark(remark);
                }
            }
            exportTask.setStatus(ExportTaskStatusConst.FAILED.ordinal());
            exportTaskService.update(exportTask);
            log.info("导出任务:{} 执行失败, 错误备注: {} ms", exportTask.getName(), remark);
            this.sendNotice(exportTask);
        } catch (Exception e) {
            log.error("标记导出任务状态失败时发生异常: {}", e);
        }
    }

    public void receiveTask(ExportTask exportTask) {
        exportTask.setReceivedAt(new Date());
        exportTask.setStatus(ExportTaskStatusConst.PROCESSING.ordinal());
        exportTaskService.update(exportTask);
    }

    public void uploadTask(ExportTask exportTask) {
        exportTask.setStatus(ExportTaskStatusConst.UPLOADING.ordinal());
        exportTaskService.update(exportTask);
    }

    public String generateHttpUploadUrl(ExportTask exportTask, String suffix) {
        UploadToken uploadToken = new UploadToken(false, true, exportTask.getCreatorId(), suffix);
        return String.format("http://upload-z1.qiniup.com?token=%s", qiniuFileFeignClient.uploadToken(uploadToken).getData());
    }

    public String getFirstFileUrl(String fileJsonStr) {
        JSONArray files = JSON.parseArray(fileJsonStr);
        if (Objects.nonNull(files) && !files.isEmpty()){
            String fileKey = files.getJSONObject(0).getString("uid");
            return qiniuFileFeignClient.downloadUrl(fileKey).getData();
        } else {
            throw new BusinessVerifyFailedException("无法获取模板文件的url");
        }
    }

    public String uploadToQiniuFile(String fileName, InputStream inputStream, String creatorId, String suffix, String mime){
        Configuration cfg = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(cfg);
        String uploadToken = qiniuFileFeignClient.uploadToken(new UploadToken(false, true, creatorId, suffix)).getData();
        StringMap params = new StringMap();
        params.put("fname", fileName);
        try {
            Response response = uploadManager.put(inputStream, null, uploadToken, params, mime);
            return response.bodyString();
        } catch (QiniuException e) {
            throw new BusinessVerifyFailedException("导出文件上传失败", e);
        }
    }

    /**
     * 导出任务的消息通知
     * @param exportTask 导出任务
     */
    private void sendNotice(ExportTask exportTask){
        Notice notice = new Notice();
        notice.setType(NoticeTypeConst.USER_NOTICE.ordinal());
        notice.setMethod(NoticeMethodConst.MESSAGE.ordinal());
        String status = "";
        switch (ExportTaskStatusConst.values()[exportTask.getStatus()]){
            case FAILED:
                status = "导出失败";
                break;
            case SUCCESS:
                status = "导出成功";
                break;
            case WAITING:
                status = "排队等待中";
                break;
            case UPLOADING:
                status = "数据上传中";
                break;
            case PROCESSING:
                status = "导出处理中";
                break;
        }
        notice.setTitle(String.format("[%s]%s", status, exportTask.getName()));
        notice.setReceiverId(exportTask.getCreatorId());
        notice.setReceiverName(exportTask.getCreatorName());
        notice.setRefId(exportTask.getId());
        notice.setRefType(NoticeRefTypeConst.EXPORT_TASK.name());
        notice.setRemark(exportTask.getRemark());
        noticeFeignClient.create(notice);
    }

}
