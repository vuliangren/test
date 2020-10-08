package com.welearn.websocket.call.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.common.PersistantConst;
import com.welearn.dictionary.notify.WebSocketClientTypeConst;
import com.welearn.entity.dto.notify.WebSocketClient;
import com.welearn.entity.dto.notify.WebSocketMessage;
import com.welearn.entity.po.alink.Device;
import com.welearn.entity.po.alink.RfidTag;
import com.welearn.entity.qo.alink.RfidTagQueryCondition;
import com.welearn.entity.vo.request.alink.SecretLogin;
import com.welearn.entity.vo.request.authorization.AccessToken;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.error.exception.UserAuthCheckFailedException;
import com.welearn.feign.alink.DeviceFeignClient;
import com.welearn.feign.alink.RfidTagFeignClient;
import com.welearn.feign.authorization.TokenFeignClient;
import com.welearn.util.SpringContextUtil;
import com.welearn.websocket.handler.WebSocketTextFrameHandler;
import com.welearn.websocket.call.CallHandlerInterface;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description : AuthorizationHandler 授权认证
 * Created by Setsuna Jin on 2018/12/21.
 */
public class AuthorizationHandler implements CallHandlerInterface {

    private static final AuthorizationHandler instance = new AuthorizationHandler();

    public static AuthorizationHandler getInstance(){
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
        WebSocketClientTypeConst clientType = WebSocketClientTypeConst.values()[message.getSender().getType()];
        switch (clientType){
            case USER:
                userAuth(context, message);
                break;
            case DEVICE:
                deviceAuth(context, message);
                break;
        }
    }

    private void deviceAuth(ChannelHandlerContext context, WebSocketMessage message){
        String productKey = message.getData().getString("productKey");
        String deviceName = message.getData().getString("deviceName");
        String deviceSecret = message.getData().getString("deviceSecret");
        DeviceFeignClient deviceFeignClient = SpringContextUtil.getBean(DeviceFeignClient.class);
        Device device = deviceFeignClient.secretLogin(new SecretLogin(productKey, deviceName, deviceSecret)).getData();
        // 返回响应数据
        JSONObject data = new JSONObject();
        if (Objects.nonNull(device)){
            // 针对PMP开头的产品 的设备 在返回时携带相关标签内容
            if (device.getProductName().startsWith("PMP")) {
                JSONObject jsonObject = JSON.parseObject(device.getConfigurationJson());
                // 查询相关的RFID标签
                RfidTagFeignClient rfidTagFeignClient = SpringContextUtil.getBean(RfidTagFeignClient.class);
                RfidTagQueryCondition condition = new RfidTagQueryCondition();
                condition.setIsEnable(true);
                condition.setIotId(device.getIotId());
                List<RfidTag> tags = rfidTagFeignClient.search(condition).getData();
                // 随配置文件一同下发
                jsonObject.put("userTags", tags.stream().filter(item -> PersistantConst.User.name().equals(item.getRefType())).collect(Collectors.toSet()));
                jsonObject.put("itemTags", tags.stream().filter(item -> PersistantConst.Equipment.name().equals(item.getRefType())).collect(Collectors.toSet()));
                device.setConfigurationJson(JSON.toJSONString(jsonObject));
            }
            data.put("isAuthorized", true);
            data.put("device", device);
            // 更新内部 MAP 索引
            WebSocketClient sender = message.getSender();
            sender.setId(device.getIotId());
            sender.setCompanyId(device.getCompanyId());
            WebSocketTextFrameHandler.handleAuthCheckSuccess(context, sender);
        } else {
            data.put("isAuthorized", false);
        }
        String responseText = JSON.toJSONString(message.createResponseMessage(data));
        context.channel().writeAndFlush(new TextWebSocketFrame(responseText));
        // 发送消息后报错 触发关闭
        if (Objects.isNull(device))
            throw new UserAuthCheckFailedException("设备相关认证信息不符, 认证失败");
    }

    private void userAuth(ChannelHandlerContext context, WebSocketMessage message){
        TokenFeignClient tokenFeignClient = SpringContextUtil.getBean(TokenFeignClient.class);
        AccessToken accessToken = new AccessToken(message.getData().getString("accessToken"));
        AuthResult authResult = tokenFeignClient.check(accessToken).getData();
        if (message.getSender().getId().equals(authResult.getAccessToken().getUser().getId())){
            // 返回响应数据
            JSONObject data = new JSONObject();
            data.put("isAuthorized", true);
            String responseText = JSON.toJSONString(message.createResponseMessage(data));
            context.channel().writeAndFlush(new TextWebSocketFrame(responseText));
            // 更新内部 MAP 索引
            WebSocketTextFrameHandler.handleAuthCheckSuccess(context, message.getSender());
        } else {
            throw new UserAuthCheckFailedException("用户id 与 TOKEN 不符, 认证失败");
        }
    }
}
