package com.welearn.websocket.call.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.welearn.entity.dto.notify.WebSocketMessage;
import com.welearn.websocket.call.CallHandlerInterface;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * Description : HeartbeatHandler 心跳包处理
 * Created by Setsuna Jin on 2018/12/21.
 */
public class HeartbeatHandler implements CallHandlerInterface {

    private static final HeartbeatHandler instance = new HeartbeatHandler();

    public static HeartbeatHandler getInstance(){
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
        JSONObject data = new JSONObject();
        data.put("isBeating", true);
        String responseText = JSON.toJSONString(message.createResponseMessage(data));
        context.channel().writeAndFlush(new TextWebSocketFrame(responseText));
    }
}
