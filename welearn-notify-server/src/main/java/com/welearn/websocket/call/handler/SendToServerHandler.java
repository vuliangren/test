package com.welearn.websocket.call.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.welearn.entity.dto.notify.WebSocketMessage;
import com.welearn.util.SpringContextUtil;
import com.welearn.websocket.call.CallHandlerInterface;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Description : HeartbeatHandler 心跳包处理
 * Created by Setsuna Jin on 2018/12/21.
 */
@Slf4j
public class SendToServerHandler implements CallHandlerInterface {

    private static final SendToServerHandler instance = new SendToServerHandler();

    public static SendToServerHandler getInstance(){
        return instance;
    }

    /**
     * CALL 的逻辑处理接口
     *
     * @param context ChannelHandlerContext
     * @param message WebSocketMessage
     * @param frame   TextWebSocketFrame
     */
    @Override
    public void handler(ChannelHandlerContext context, WebSocketMessage message, TextWebSocketFrame frame) {
        RabbitTemplate rabbitTemplate = SpringContextUtil.getBean(RabbitTemplate.class);
        String url = message.getData().getString("url");
        if (url.startsWith("/api/")) {
            String serverStartUrl = url.replaceFirst("/api/", "");
            String server = serverStartUrl.substring(0, serverStartUrl.indexOf("/"));
            boolean isSendToServer = false;
            try {
                rabbitTemplate.convertAndSend("web-socket-send", String.format("send-to-%s", server), frame.text());
                isSendToServer = true;
            } catch (Exception e) {
                log.error("WebSocket消息发送给对应服务失败", e);
            }
            JSONObject data = new JSONObject();
            data.put("isSendToServer", isSendToServer);
            String responseText = JSON.toJSONString(message.createResponseMessage(data));
            context.channel().writeAndFlush(new TextWebSocketFrame(responseText));
        }
    }
}
