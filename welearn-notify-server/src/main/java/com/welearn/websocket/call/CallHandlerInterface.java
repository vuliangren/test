package com.welearn.websocket.call;

import com.welearn.entity.dto.notify.WebSocketMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * Description : CallHandlerInterface
 * Created by Setsuna Jin on 2018/12/21.
 */
public interface CallHandlerInterface {
    /**
     * CALL 的逻辑处理接口
     * @param context ChannelHandlerContext
     * @param message WebSocketMessage
     * @param frame   TextWebSocketFrame
     */
    void handler(ChannelHandlerContext context, WebSocketMessage message, TextWebSocketFrame frame);
}
