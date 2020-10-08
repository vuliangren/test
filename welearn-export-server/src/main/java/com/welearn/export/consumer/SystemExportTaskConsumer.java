package com.welearn.export.consumer;

import com.welearn.dictionary.export.ExportTaskStatusConst;
import com.welearn.entity.po.export.ExportTask;
import com.welearn.export.ExportTaskConsumerHelper;
import com.welearn.service.ExportTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/11.
 */
@Slf4j
@Service
public class SystemExportTaskConsumer {

    @Autowired
    private ExportTaskConsumerHelper consumerHelper;

    @RabbitListener(queues = "system-export-task")
    public void consume(String exportTaskId) {
        ExportTask exportTask = consumerHelper.selectTask(exportTaskId);
        consumerHelper.receiveTask(exportTask);


    }
}
