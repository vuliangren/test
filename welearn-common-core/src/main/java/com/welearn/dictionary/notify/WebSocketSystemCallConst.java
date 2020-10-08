package com.welearn.dictionary.notify;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/20.
 */
@AllArgsConstructor
public enum  WebSocketSystemCallConst {
    AUTHORIZATION("WS首次连接时进行授权认证","authorization"),
    HEARTBEAT("客户端发送心跳包 防止Nginx断开和服务器的链接","heartbeat"),
    SEARCH_CONNECT_CLIENTS("查询连接的客户端列表, 根据公司id和部门id","search_connect_clients"),
    SUBSCRIBE_TO("开始订阅其它客户端消息", "subscribe_to"),
    UN_SUBSCRIBE("取消订阅其它客户端消息", "un_subscribe"),
    // 对应服务需要 连接 RabbitMQ 才可接收到消息, 并非是请求转发
    SEND_TO_SERVER("将消息转发给相应的服务", "send_to_server"),
    ;
    @Getter
    private String description;
    @Getter
    private String call;

    public static WebSocketSystemCallConst get(String call){
        List<WebSocketSystemCallConst> collect = Arrays.stream(WebSocketSystemCallConst.values())
                .filter(c -> c.getCall().equals(call))
                .collect(Collectors.toList());
        return collect.isEmpty() ? null : collect.get(0);
    }
}
