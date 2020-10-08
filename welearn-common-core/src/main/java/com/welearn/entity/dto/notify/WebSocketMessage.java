package com.welearn.entity.dto.notify;

import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.notify.WebSocketMessageWaitConst;
import com.welearn.util.UUIDGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/20.
 */
@Data
@EqualsAndHashCode
public class WebSocketMessage {
    private WebSocketClient sender;
    private List<WebSocketClient> receiver;
    private String call;
    private JSONObject data;
    private String uuid;
    private Integer wait;

    public WebSocketMessage createResponseMessage(JSONObject data){
        WebSocketMessage response = new WebSocketMessage();
        // 构建接收人
        List<WebSocketClient> receiver = new ArrayList<>();
        receiver.add(sender);
        // 构建响应消息
        response.setCall(uuid);
        response.setData(data);
        response.setReceiver(receiver);
        response.setSender(null);
        response.setUuid(UUIDGenerator.get());
        response.setWait(WebSocketMessageWaitConst.WAIT_NOT.ordinal());
        return response;
    }
}
