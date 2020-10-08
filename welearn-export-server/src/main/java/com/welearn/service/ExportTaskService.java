package com.welearn.service;

import com.welearn.entity.po.export.ExportTask;
import com.welearn.entity.qo.export.ExportTaskQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ExportTaskService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ExportTaskService extends BaseService<ExportTask, ExportTaskQueryCondition>{

    /**
     * 重新执行
     * @param exportTaskId 原任务id
     */
    void retry(@NotBlank String exportTaskId);


    /**
     * 再次导出
     * @param exportTaskId 原任务id
     */
    void reExport(@NotBlank String exportTaskId);
}