package com.welearn.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.dto.notify.WebSocketMessage;
import com.welearn.feign.alink.DeviceFeignClient;
import com.welearn.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Description :
 * Created by Setsuna Jin on 2019/7/6.
 */
@Slf4j
@Configuration
public class RabbitMqConfiguration {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Value("${spring.application.name}")
    private String applicationName;

    private volatile ConcurrentMap<String, HandlerMethod> urlHandleMethodIndex;


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


    /**
     * 消费发送到当前队列的消息
     * @param webSocketMessageJson JSON 格式的 WebSocketMessage
     */
    @RabbitListener(queues = "send-to-alink")
    public void consume(String webSocketMessageJson) {
        // 懒加载 初始化 urlHandleMethodIndex
        if (Objects.isNull(urlHandleMethodIndex)) {
            // 加锁
            synchronized (this) {
                // 防重复执行
                if (Objects.isNull(urlHandleMethodIndex)) {
                    String serviceName = ServiceConst.get(applicationName).getServiceName();
                    urlHandleMethodIndex = new ConcurrentHashMap<>();
                    handlerMapping.getHandlerMethods().forEach((key, value)-> {
                        // 对于方法参数长度大于 1 的路由不予调用
                        if (value.getMethodParameters().length > 1)
                            return;
                        // 将生成的匹配信息缓存, 便于后续查找使用
                        for (String url : key.getPatternsCondition().getPatterns()) {
                            urlHandleMethodIndex.put(String.format("/api/%s%s", serviceName, url), value);
                        }
                    });
                }
            }
        }

        try {
            // 开始执行方法的调用
            urlHandleMethodInvoke(webSocketMessageJson);
        } catch (Exception e) {
            log.error("WebSocket方式调用服务方法异常", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    /**
     * 处理 WebSocketMessage 的服务放弃调用请求
     * {
     *   "data": {
     *     "url": "/device/search",
     *     "payload": "{JSON}"
     *   },
     *   ...
     * }
     * @param webSocketMessageJson JSON 格式的 WebSocketMessage
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void urlHandleMethodInvoke(String webSocketMessageJson) throws InvocationTargetException, IllegalAccessException {
        // 解析消息的url
        WebSocketMessage message = JSON.parseObject(webSocketMessageJson, WebSocketMessage.class);
        // 开始执行该方法 忽略返回值
        String url = message.getData().getString("url");
        HandlerMethod handlerMethod = urlHandleMethodIndex.get(url);
        if (Objects.nonNull(handlerMethod)) {
            // 获取 Spring Bean容器 的控制器实例
            Object controller = SpringContextUtil.getBean(handlerMethod.getBeanType());
            Method method = handlerMethod.getMethod();
            switch (handlerMethod.getMethodParameters().length) {
                case 0:
                    // 无参数调用
                    method.invoke(controller);
                    break;
                case 1:
                    // 单参数调用
                    method.invoke(controller, JSON.parseObject(message.getData().getString("payload"),
                            handlerMethod.getMethodParameters()[0].getParameterType()));
                    break;
            }
        } else {
            log.warn("WebSocket方式调用服务方法的URL非法: {}", url);
        }
    }

}
