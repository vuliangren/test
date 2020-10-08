package com.welearn.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.notify.WebSocketClientRegisterConst;
import com.welearn.dictionary.notify.WebSocketClientTypeConst;
import com.welearn.dictionary.notify.WebSocketClientTypeDetailConst;
import com.welearn.dictionary.notify.WebSocketMessageWaitConst;
import com.welearn.entity.dto.notify.WebSocketClient;
import com.welearn.entity.dto.notify.WebSocketMessage;
import com.welearn.util.UUIDGenerator;
import com.welearn.websocket.WebSocketRepository.ChannelIndex;
import com.welearn.websocket.WebSocketRepository.ChannelInfo;
import com.welearn.websocket.handler.WebSocketTextFrameHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description :
 * Created by Setsuna Jin on 2019/2/16.
 */
@Slf4j
@Service
public class WebSocketServiceImpl implements WebSocketService {
    /**
     * 通过 WebSocket 通知用户特定在线客户端
     *
     * @param userId     用户id
     * @param clientType 客户端类型
     * @param typeDetail 客户端类型详情
     * @param call       调用客户端注册的方法
     * @param data       调用客户端注册方法的参数
     */
    @Override
    public void notifyClient(String userId, WebSocketClientTypeConst clientType, WebSocketClientTypeDetailConst typeDetail, WebSocketClientRegisterConst call, JSONObject data) {
        // 构建消息体
        WebSocketMessage webSocketMessage = new WebSocketMessage();
        webSocketMessage.setData(data);
        webSocketMessage.setUuid(UUIDGenerator.get());
        webSocketMessage.setCall(call.name());
        webSocketMessage.setWait(WebSocketMessageWaitConst.WAIT_NOT.ordinal());
        webSocketMessage.setSender(null);
        List<WebSocketClient> receiver = new ArrayList<>();
        WebSocketClient webSocketClient = new WebSocketClient();
        webSocketClient.setId(userId);
        if (Objects.nonNull(clientType))
            webSocketClient.setType(clientType.ordinal());
        if (Objects.nonNull(typeDetail))
            webSocketClient.setDetail(typeDetail.getValue());
        receiver.add(webSocketClient);
        webSocketMessage.setReceiver(receiver);
        String textMessage = JSON.toJSONString(webSocketMessage);
        // 查找通道 发送
        List<ChannelIndex> channelIndices = WebSocketTextFrameHandler.CLIENTS.get(userId);
        if (Objects.nonNull(channelIndices) && !channelIndices.isEmpty()){
            List<ChannelIndex> collect;
            collect = channelIndices.stream()
                    .filter(channelIndex -> {
                        boolean flag = true;
                        if (Objects.nonNull(clientType))
                            flag = flag && channelIndex.getType().equals(clientType.ordinal());
                        if (Objects.nonNull(typeDetail) && typeDetail != WebSocketClientTypeDetailConst.ALL_CLIENT)
                            flag = flag && channelIndex.getDetail().equals(typeDetail.getValue());
                        return flag;
                    })
                    .collect(Collectors.toList());
            // 对符合的 channel 转发消息
            collect.stream().filter(Objects::nonNull).forEach(channelIndex -> {
                ChannelInfo channelInfo = WebSocketTextFrameHandler.CHANNELS.get(channelIndex.getChannelId());
                try {
                    if (Objects.nonNull(channelInfo))
                        channelInfo.getContext().channel().writeAndFlush(new TextWebSocketFrame(textMessage));
                } catch (Exception e){
                    log.error("通知用户在线客户端异常", e);
                }
            });
        }
    }

    /**
     * 通过 WebSocket 通知用户部分类型在线客户端
     *
     * @param userId     用户id
     * @param clientType 客户端类型
     * @param call       调用客户端注册的方法
     * @param data       调用客户端注册方法的参数
     */
    @Override
    public void notifyClient(String userId, WebSocketClientTypeConst clientType, WebSocketClientRegisterConst call, JSONObject data) {
        this.notifyClient(userId, clientType, null, call, data);
    }

    /**
     * 通过 WebSocket 通知用户所有的在线客户端
     *
     * @param userId 用户id
     * @param call   调用客户端注册的方法
     * @param data   调用客户端注册方法的参数
     */
    @Override
    public void notifyClient(String userId, WebSocketClientRegisterConst call, JSONObject data) {
        this.notifyClient(userId, null, null, call, data);
    }

    /**
     * 通过 WebSocket 通知用户所有的在线客户端
     *
     * @param userId 用户id
     * @param call   调用客户端注册的方法
     */
    @Override
    public void notifyClient(String userId, WebSocketClientRegisterConst call) {
        this.notifyClient(userId, null, null, call, null);
    }
}
