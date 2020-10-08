package com.welearn.websocket.call.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.welearn.entity.dto.notify.WebSocketMessage;
import com.welearn.websocket.WebSocketRepository;
import com.welearn.websocket.call.CallHandlerInterface;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Description : 客户端 取消关注 另一个客户端
 * Created by Setsuna Jin on 2018/12/21.
 */
public class UnSubscribeHandler implements CallHandlerInterface, WebSocketRepository {

    private static final UnSubscribeHandler instance = new UnSubscribeHandler();

    public static UnSubscribeHandler getInstance(){
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
        Set<String> listenedChannels = LISTENED_CHANNELS.get(thisChannelId);

        boolean isUnSubscribe = false;
        if (Objects.nonNull(listenedChannels) && !listenedChannels.isEmpty()) {
            List<ChannelIndex> channelIndices = CLIENTS.get(message.getData().getString("id"));
            if (Objects.nonNull(channelIndices) && !channelIndices.isEmpty()) {
                // 将取关客户端对应的链接从当前链接的关注列表中移除
                Set<String> channelIds = channelIndices.stream().map(ChannelIndex::getChannelId).collect(Collectors.toSet());
                listenedChannels.removeAll(channelIds);
                // 将当前链接从取关客户端对应链接的被关注列表中移除
                channelIds.forEach(channelId -> {
                    Set<String> listenerIds = CHANNEL_LISTENERS.get(channelId);
                    if (Objects.nonNull(listenerIds) && !listenerIds.isEmpty()) {
                        listenerIds.remove(thisChannelId);
                    }
                });
                isUnSubscribe = true;
            }
        }
        JSONObject data = new JSONObject();
        data.put("isUnSubscribe", isUnSubscribe);
        String responseText = JSON.toJSONString(message.createResponseMessage(data));
        context.channel().writeAndFlush(new TextWebSocketFrame(responseText));
    }

    public static void onChannelClose(String channelId){
        cleanIndex(CHANNEL_LISTENERS, LISTENED_CHANNELS, channelId);
        cleanIndex(LISTENED_CHANNELS, CHANNEL_LISTENERS, channelId);
    }

    private static void cleanIndex(ConcurrentMap<String, Set<String>> map1, ConcurrentMap<String, Set<String>> map2, String index) {
        Set<String> map1Indices = map1.get(index);
        if (Objects.nonNull(map1Indices)) {
            map1Indices.forEach(map1Index -> {
                Set<String> map2Indices = map2.get(map1Index);
                if (Objects.nonNull(map2Indices)) {
                    map2Indices.remove(index);
                }
            });
            map1.remove(index);
        }
    }
}
