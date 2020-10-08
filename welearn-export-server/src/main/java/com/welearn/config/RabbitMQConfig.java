package com.welearn.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description : 文件导出相关消息队列配置
 * Created by Setsuna Jin on 2019/6/10.
 */
@Slf4j
@Configuration
public class RabbitMQConfig {

    // 消息队列 ---------------------------------------------------------

    /**
     * 根据模板文件导出文件 队列
     * @return 队列
     */
    @Bean(name = "template-export-task")
    public Queue newTemplateExportTaskQueue(){
        return new Queue("template-export-task");
    }

    /**
     * 根据JSON数据导出文件 队列
     * @return 队列
     */
    @Bean(name = "json-export-task")
    public Queue newJsonExportTaskQueue(){
        return new Queue("json-export-task");
    }

    /**
     * 根据系统预设导出文件 队列
     * @return 队列
     */
    @Bean(name = "system-export-task")
    public Queue newSystemExportTaskQueue(){
        return new Queue("system-export-task");
    }

    // 交换机 -----------------------------------------------------------

    @Bean(name = "file-export")
    public Exchange newFileExportExchange(){
        return new DirectExchange("file-export",true,false);
    }

    // 绑定关系 ---------------------------------------------------------

    @Bean @Autowired
    public Binding newTemplateExportTaskBinding(@Qualifier("template-export-task") Queue queue, @Qualifier("file-export") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("template-export-task").noargs();
    }

    @Bean @Autowired
    public Binding newJsonExportTaskBinding(@Qualifier("json-export-task") Queue queue, @Qualifier("file-export") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("json-export-task").noargs();
    }

    @Bean @Autowired
    public Binding newSystemExportTaskBinding(@Qualifier("system-export-task") Queue queue, @Qualifier("file-export") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("system-export-task").noargs();
    }
}
