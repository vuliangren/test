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
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/19.
 */
@Slf4j
public class WebSocketTextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> implements WebSocketRepository {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame frame) {
        String frameText = frame.text();
        WebSocketMessage message = JSON.parseObject(frameText, WebSocketMessage.class);
        // 验证其连接的合法性
        handleAuthCheck(channelHandlerContext, message);
        // 判断是否由系统接收
        if (message.getReceiver().isEmpty())
            handleSystemCall(channelHandlerContext, message, frame);
        // 转发给其它Channel
        else
            handleMessageProxy(message, frameText);
        // 处理消息的监听转发
        SubscribeToHandler.onReceiveMessage(channelHandlerContext.channel().id().asLongText(), frameText);
    }

    //每个channel都有一个唯一的id值
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        ChannelInfo channelInfo = new ChannelInfo();
        Date current = new Date();
        channelInfo.setCreatedAt(current);
        channelInfo.setUpdatedAt(current);
        channelInfo.setContext(ctx);
        channelInfo.setIsAuthorized(false);
        CHANNELS.put(ctx.channel().id().asLongText(), channelInfo);
        UN_AUTH_CHANNELS.put(ctx.channel().id().asLongText(), channelInfo);
        log.info("handlerAdded, {}, {}, {}", UN_AUTH_CHANNELS.size(), CHANNELS.size(), CLIENTS.size());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        String channelId = ctx.channel().id().asLongText();
        ChannelInfo channelInfo = CHANNELS.get(channelId);
        // 查找是否有客户端索引 关联此 channel 信息
        if (Objects.nonNull(channelInfo) && channelInfo.getIsAuthorized()){
            List<ChannelIndex> channelIndices = CLIENTS.get(channelInfo.getId());
            if (Objects.nonNull(channelIndices)){
                List<ChannelIndex> currentChannelIndex = channelIndices.stream()
                        .filter(Objects::nonNull)
                        .filter(channelIndex -> channelId.equals(channelIndex.getChannelId()))
                        .collect(Collectors.toList());
                // 清理掉当前索引
                channelIndices.removeAll(currentChannelIndex);
                // 如果客户端索引已为空 则清理掉该客户端索引
                if (channelIndices.isEmpty())
                    CLIENTS.remove(channelInfo.getId());
            }
        }
        // 删除此 channel 信息
        CHANNELS.remove(channelId);
        UN_AUTH_CHANNELS.remove(channelId);
        // 删除其关注 和 被关注 的相关索引信息
        UnSubscribeHandler.onChannelClose(channelId);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("异常发生", cause);
        ctx.close();
    }

    private void handleAuthCheck(ChannelHandlerContext ctx, WebSocketMessage message){
        if (message.getReceiver().isEmpty() && message.getCall().equals(WebSocketSystemCallConst.AUTHORIZATION.getCall()))
            return;
        ChannelInfo channelInfo = CHANNELS.get(ctx.channel().id().asLongText());
        if (!channelInfo.getIsAuthorized())
            throw new UserAuthCheckFailedException("WebSocket 未进行授权认证");
    }

    public static void handleAuthCheckSuccess(ChannelHandlerContext ctx, WebSocketClient client){
        String channelId = ctx.channel().id().asLongText();
        // 从未认证列表里删除
        UN_AUTH_CHANNELS.remove(channelId);
        // 更新 CHANNELS 列表的数据
        ChannelInfo channelInfo = CHANNELS.get(channelId);
        channelInfo.setIsAuthorized(true);
        channelInfo.setUpdatedAt(new Date());
        channelInfo.setId(client.getId());
        channelInfo.setType(client.getType());
        channelInfo.setDetail(client.getDetail());
        // 添加至 CLIENTS 中
        ChannelIndex channelIndex = new ChannelIndex();
        channelIndex.setChannelId(channelId);
        channelIndex.setId(client.getId());
        channelIndex.setType(client.getType());
        channelIndex.setDetail(client.getDetail());
        List<ChannelIndex> channelIndices = CLIENTS.get(client.getId());
        if (Objects.isNull(channelIndices)){
            // 如原先不存在则添加
            channelIndices = new ArrayList<>();
            channelIndices.add(channelIndex);
            CLIENTS.put(client.getId(), channelIndices);
        } else {
            channelIndices.add(channelIndex);
        }
    }

    private void handleSystemCall(ChannelHandlerContext ctx, WebSocketMessage message, TextWebSocketFrame frame){
        WebSocketSystemCallConst call = WebSocketSystemCallConst.get(message.getCall());
        if (Objects.isNull(call)) {
            log.error("Call 类型未在 WebSocketSystemCallConst 中注册: {}", frame.text());
            return;
        }
        switch (call){
            case AUTHORIZATION:
                AuthorizationHandler.getInstance().handler(ctx, message, frame);
                break;
            case HEARTBEAT:
                HeartbeatHandler.getInstance().handler(ctx, message, frame);
                break;
            case SUBSCRIBE_TO:
                SubscribeToHandler.getInstance().handler(ctx, message, frame);
                break;
            case UN_SUBSCRIBE:
                UnSubscribeHandler.getInstance().handler(ctx, message, frame);
                break;
            case SEND_TO_SERVER:
                SendToServerHandler.getInstance().handler(ctx, message, frame);
                break;
            default:
                log.error("不支持的 MESSAGE: {}", frame.text());
                break;
        }
    }

    private void handleMessageProxy(WebSocketMessage message, String frameText){
        List<WebSocketClient> receivers = message.getReceiver();
        receivers.forEach(receiver -> {
            // 查找符合的 ChannelIndex
            List<ChannelIndex> channelIndices = CLIENTS.get(receiver.getId());
            if (Objects.nonNull(channelIndices) && !channelIndices.isEmpty()){
                List<ChannelIndex> collect;
                // 所有在线客户端
                if (receiver.getDetail().equals(WebSocketClientTypeDetailConst.ALL_CLIENT.ordinal()))
                    collect = channelIndices.stream()
                            .filter(channelIndex -> channelIndex.getType().equals(receiver.getType()))
                            .collect(Collectors.toList());
                // 特定在线客户端
                else
                    collect = channelIndices.stream()
                            .filter(channelIndex -> {
                                boolean flag = true;
                                if (Objects.nonNull(receiver.getType()))
                                    flag = flag && channelIndex.getType().equals(receiver.getType());
                                if (Objects.nonNull(receiver.getDetail()) && receiver.getDetail() != WebSocketClientTypeDetailConst.ALL_CLIENT.ordinal())
                                    flag = flag && channelIndex.getDetail().equals(receiver.getDetail());
                                return flag;
                            })
                            .collect(Collectors.toList());
                // 对符合的 channel 转发消息
                collect.forEach(channelIndex -> {
                    ChannelInfo channelInfo = CHANNELS.get(channelIndex.getChannelId());
                    channelInfo.getContext().channel().writeAndFlush(new TextWebSocketFrame(frameText));
                });
            }
        });
    }
}
