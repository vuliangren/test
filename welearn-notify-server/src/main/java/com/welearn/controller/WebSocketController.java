package com.welearn.controller;

import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.websocket.WebSocketRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/18.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/webSocket")
public class WebSocketController implements WebSocketRepository {

    @RequestMapping(value = "/channel/all")
    @ApiOperation(value = "所有的链接", httpMethod = "POST")
    public CommonResponse<Map<String, ChannelInfo>> channelAll(){
        return new CommonResponse<>(CHANNELS);
    }

    @RequestMapping(value = "/channel/unAuth")
    @ApiOperation(value = "未认证链接", httpMethod = "POST")
    public CommonResponse<Map<String, ChannelInfo>> channelUnAuth(){
        return new CommonResponse<>(UN_AUTH_CHANNELS);
    }

    @RequestMapping(value = "/channel/authed")
    @ApiOperation(value = "已认证链接", httpMethod = "POST")
    public CommonResponse<Map<String, List<ChannelIndex>>> channelAuthed(){
        return new CommonResponse<>(CLIENTS);
    }

    @RequestMapping(value = "/channel/listen")
    @ApiOperation(value = "链接的监听", httpMethod = "POST")
    public CommonResponse<Map<String, Set<String>>> channelListen(@RequestBody String channelId){
        Map<String, Set<String>> response = new HashMap<>();
        response.put("listened", LISTENED_CHANNELS.get(channelId));
        response.put("listener", CHANNEL_LISTENERS.get(channelId));
        return new CommonResponse<>(response);
    }
}
