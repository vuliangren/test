package com.welearn.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description :
 * Created by Setsuna Jin on 2019/7/6.
 */
@Slf4j
@Configuration
public class RabbitMqConfiguration {
    // 消息队列 ---------------------------------------------------------

    /**
     * 发送至 alink 服务的消息队列
     * @return 队列
     */
    @Bean(name = "send-to-alink")
    public Queue newSendToAlinkQueue(){
        return new Queue("send-to-alink");
    }

    // 交换机 -----------------------------------------------------------

    @Bean(name = "web-socket-send")
    public Exchange newWebSocketSendExchange(){
        return new DirectExchange("web-socket-send",true,false);
    }

    // 绑定关系 ---------------------------------------------------------

    @Bean @Autowired
    public Binding newTemplateExportTaskBinding(@Qualifier("send-to-alink") Queue queue, @Qualifier("web-socket-send") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("send-to-alink").noargs();
    }
}
