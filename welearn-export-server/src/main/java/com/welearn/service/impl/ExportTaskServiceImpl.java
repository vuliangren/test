package com.welearn.service.impl;

import com.welearn.dictionary.export.ExportTaskStatusConst;
import com.welearn.dictionary.export.ExportTaskTypeConst;
import com.welearn.entity.po.export.ExportTask;
import com.welearn.entity.qo.export.ExportTaskQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.ExportTaskMapper;
import com.welearn.service.ExportTaskService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description : ExportTaskService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ExportTaskServiceImpl extends BaseServiceImpl<ExportTask,ExportTaskQueryCondition,ExportTaskMapper>
        implements ExportTaskService{
    
    @Autowired
    private ExportTaskMapper exportTaskMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    ExportTaskMapper getMapper() {
        return exportTaskMapper;
    }

    /**
     * 将导出任务推送到消息队列中
     * @param task 数据导出任务
     * @return 数据导出任务
     */
    private ExportTask pushTaskToMQ(ExportTask task) {
        switch (ExportTaskTypeConst.values()[task.getType()]) {
            case TEMPLATE_EXPORT:
                rabbitTemplate.convertAndSend("file-export", "template-export-task", task.getId());
                break;
            case JSON_EXPORT:
                rabbitTemplate.convertAndSend("file-export", "json-export-task", task.getId());
                break;
            case SYSTEM_EXPORT:
                rabbitTemplate.convertAndSend("file-export", "system-export-task", task.getId());
                break;
            default:
                throw new BusinessVerifyFailedException("exportTask type 非法");
        }
        return task;
    }

    @Override
    @Transactional
    public ExportTask create(ExportTask exportTask) {
        exportTask.setStatus(ExportTaskStatusConst.WAITING.ordinal());
        return this.pushTaskToMQ(super.create(exportTask));
    }

    /**
     * 重新执行
     * @param exportTaskId 原任务id
     */
    @Override
    @Transactional
    public void retry(String exportTaskId) {
        ExportTask exportTask = this.select(exportTaskId);
        if (exportTask.getStatus() == ExportTaskStatusConst.FAILED.ordinal()){
            exportTask.setStatus(ExportTaskStatusConst.WAITING.ordinal());
            exportTask.setReceivedAt(null);
            exportTaskMapper.updateByPK(exportTask);
            this.pushTaskToMQ(exportTask);
        } else {
            throw new BusinessVerifyFailedException("导出任务并未执行失败, 无法重试");
        }
    }

    /**
     * 再次导出
     * @param exportTaskId 原任务id
     */
    @Override
    public void reExport(String exportTaskId) {
        ExportTask exportTask = this.select(exportTaskId);
        ExportTask newTask = new ExportTask();
        newTask.setName(exportTask.getName());
        newTask.setType(exportTask.getType());
        newTask.setArgsJson(exportTask.getArgsJson());
        newTask.setCompanyId(exportTask.getCompanyId());
        newTask.setCreatorId(exportTask.getCreatorId());
        newTask.setCreatorName(exportTask.getCreatorName());
        this.create(newTask);
    }
}
