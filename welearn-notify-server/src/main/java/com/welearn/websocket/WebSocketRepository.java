package com.welearn.websocket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.welearn.entity.dto.notify.WebSocketClient;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Description :
 * Created by Setsuna Jin on 2019/7/5.
 */
public interface WebSocketRepository {
    @Data
    @EqualsAndHashCode(callSuper = true)
    class ChannelIndex extends WebSocketClient {
        private String channelId;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    class ChannelInfo extends WebSocketClient {
        @JsonIgnore
        private ChannelHandlerContext context;
        private Date createdAt;
        private Date updatedAt;
        private Boolean isAuthorized;
    }

    // 未认证的链接
    ConcurrentMap<String, ChannelInfo> UN_AUTH_CHANNELS = new ConcurrentHashMap<>();
    // channelId 对应的链接
    ConcurrentMap<String /** channelId */, ChannelInfo> CHANNELS = new ConcurrentHashMap<>();
    // 客户端链接 userId / deviceId
    ConcurrentMap<String /** id */, List<ChannelIndex>> CLIENTS = new ConcurrentHashMap<>();

    // 客户端 关注 的其它客户端 channelId
    ConcurrentMap<String, Set<String>> LISTENED_CHANNELS = new ConcurrentHashMap<>();
    // 客户端 被其它客户端 channelId 关注
    ConcurrentMap<String, Set<String>> CHANNEL_LISTENERS = new ConcurrentHashMap<>();
}
