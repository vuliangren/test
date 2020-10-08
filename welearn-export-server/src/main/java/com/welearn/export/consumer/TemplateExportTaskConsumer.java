package com.welearn.export.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.welearn.entity.dto.export.TemplateExportArgs;
import com.welearn.entity.po.export.ExportTask;
import com.welearn.entity.po.export.ExportTemplate;
import com.welearn.entity.po.storage.UploadRecord;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.export.ExportTaskConsumerHelper;
import com.welearn.feign.storage.QiniuFileFeignClient;
import com.welearn.service.ExportTemplateService;
import com.welearn.xdoc.XDocService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/11.
 */
@Slf4j
@Service
public class TemplateExportTaskConsumer {

    @Autowired
    private ExportTaskConsumerHelper consumerHelper;

    @Autowired
    private ExportTemplateService exportTemplateService;

    @Autowired
    private XDocService xDocService;


    @RabbitListener(queues = "template-export-task")
    public void consume(String exportTaskId) {
        ExportTask exportTask = consumerHelper.selectTask(exportTaskId);
        try {
            this.doExport(exportTask);
        } catch (Exception e) {
            log.error("template-export-task consume failed", e);
            consumerHelper.taskFailed(exportTask, e.getMessage());
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    public void doExport(ExportTask exportTask) throws IOException {
        long startTimestamp = System.currentTimeMillis();
        // 确认接收任务
        consumerHelper.receiveTask(exportTask);
        // 检查服务状态
        if (xDocService.hi()) {
            // 验证请求参数
            TemplateExportArgs templateExportArgs = JSON.parseObject(exportTask.getArgsJson(), TemplateExportArgs.class);
            if (Objects.isNull(templateExportArgs) || StringUtils.isBlank(templateExportArgs.getTemplateId()))
                throw new BusinessVerifyFailedException("请求参数格式非法");
            // 获取模板文件
            ExportTemplate template = exportTemplateService.select(templateExportArgs.getTemplateId());
            if (Objects.isNull(template) || !template.getIsEnable())
                throw new BusinessVerifyFailedException("请求参数 templateId 非法");
            // 获取模板URL
            String templateFileUrl = consumerHelper.getFirstFileUrl(template.getFile());
            // 确定文件格式
            String exportFileFormat = templateExportArgs.getIsPdfExport() ? "pdf" : template.getFileSuffix();
            // 生成上传URL
            String exportFileUpload = consumerHelper.generateHttpUploadUrl(exportTask, String.format(".%s", exportFileFormat));
            // 执行导出服务
            String response = xDocService.run(templateFileUrl, templateExportArgs.getParams(), exportFileUpload, exportFileFormat, exportTask.getName());
            // 更新任务状态
            consumerHelper.finishTask(exportTask, response, System.currentTimeMillis() - startTimestamp);
        } else {
            throw new BusinessVerifyFailedException("XDOC 服务器无应达");
        }
    }
}
