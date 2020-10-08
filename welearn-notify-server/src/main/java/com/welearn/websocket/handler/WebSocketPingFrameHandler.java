package com.welearn.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.notify.WebSocketClientTypeDetailConst;
import com.welearn.dictionary.notify.WebSocketSystemCallConst;
import com.welearn.entity.dto.notify.WebSocketClient;
import com.welearn.entity.dto.notify.WebSocketMessage;
import com.welearn.error.exception.UserAuthCheckFailedException;
import com.welearn.websocket.WebSocketRepository;
import com.welearn.websocket.call.handler.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description : 针对 PING 控制帧 的 响应
 * Created by Setsuna Jin on 2018/12/19.
 */
@Slf4j
public class WebSocketPingFrameHandler extends SimpleChannelInboundHandler<PingWebSocketFrame> implements WebSocketRepository {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PingWebSocketFrame msg) throws Exception {
        ctx.channel().write(new PongWebSocketFrame(msg.content().retain()));
    }
}
