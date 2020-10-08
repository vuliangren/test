package com.welearn.websocket.call.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.welearn.entity.dto.notify.WebSocketMessage;
import com.welearn.websocket.WebSocketRepository;
import com.welearn.websocket.call.CallHandlerInterface;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description : 客户端 开始关注 另一个客户端
 * Created by Setsuna Jin on 2018/12/21.
 */
public class SubscribeToHandler implements CallHandlerInterface, WebSocketRepository {

    private static final SubscribeToHandler instance = new SubscribeToHandler();

    public static SubscribeToHandler getInstance(){
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
        String thisChannelId = context.channel().id().asLongText();
        List<ChannelIndex> channelIndices = CLIENTS.get(message.getData().getString("id"));

        boolean isSubscribeTo = false;
        Set<String> listenChannelIds = null;
        // 如果需要监听的链接 存在
        if (Objects.nonNull(channelIndices) && !channelIndices.isEmpty()) {
            // 创建索引 便于取消监听时的处理
            listenChannelIds = channelIndices.stream().map(ChannelIndex::getChannelId).collect(Collectors.toSet());
            Set<String> thisChannelListenedIds = LISTENED_CHANNELS.get(thisChannelId);
            if (Objects.isNull(thisChannelListenedIds)) {
                thisChannelListenedIds = new HashSet<>();
                LISTENED_CHANNELS.put(thisChannelId, thisChannelListenedIds);
            }
            thisChannelListenedIds.addAll(listenChannelIds);
            // 创建索引 便于进行监听时的处理
            listenChannelIds.forEach(listenChannelId-> {
                Set<String> listenerChannelIds = CHANNEL_LISTENERS.get(listenChannelId);
                if (Objects.isNull(listenerChannelIds)) {
                    listenerChannelIds = new HashSet<>();
                    CHANNEL_LISTENERS.put(listenChannelId, listenerChannelIds);
                }
                listenerChannelIds.add(thisChannelId);
            });
            isSubscribeTo = true;
        }
        // 返回操作响应
        JSONObject data = new JSONObject();
        data.put("isSubscribeTo", isSubscribeTo);
        if (Objects.nonNull(listenChannelIds)){
            data.put("listenChannelIds", listenChannelIds);
        }
        String responseText = JSON.toJSONString(message.createResponseMessage(data));
        context.channel().writeAndFlush(new TextWebSocketFrame(responseText));
    }

    public static void onReceiveMessage(String channelId, String frameText){
        // 查找关注当前链接的客户端, 向其转发消息
        Set<String> listenerIds = CHANNEL_LISTENERS.get(channelId);
        if (Objects.isNull(listenerIds))
            return;
        listenerIds.forEach(listenerId -> {
            ChannelInfo channelInfo = CHANNELS.get(listenerId);
            if (Objects.nonNull(channelInfo)) {
                channelInfo.getContext().channel().writeAndFlush(new TextWebSocketFrame(frameText));
            }
        });
    }
}
