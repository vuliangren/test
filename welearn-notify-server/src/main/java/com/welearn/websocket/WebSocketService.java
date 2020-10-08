package com.welearn.websocket;

import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.notify.WebSocketClientRegisterConst;
import com.welearn.dictionary.notify.WebSocketClientTypeConst;
import com.welearn.dictionary.notify.WebSocketClientTypeDetailConst;

/**
 * Description : WebSocketService
 * Created by Setsuna Jin on 2019/2/16.
 */
public interface WebSocketService {

    /**
     * 通过 WebSocket 通知用户特定在线客户端
     * @param userId 用户id
     * @param clientType 客户端类型
     * @param typeDetail 客户端类型详情
     * @param call 调用客户端注册的方法
     * @param data 调用客户端注册方法的参数
     */
    void notifyClient(String userId, WebSocketClientTypeConst clientType, WebSocketClientTypeDetailConst typeDetail, WebSocketClientRegisterConst call, JSONObject data);

    /**
     * 通过 WebSocket 通知用户部分类型在线客户端
     * @param userId 用户id
     * @param clientType 客户端类型
     * @param call 调用客户端注册的方法
     * @param data 调用客户端注册方法的参数
     */
    void notifyClient(String userId, WebSocketClientTypeConst clientType, WebSocketClientRegisterConst call, JSONObject data);

    /**
     * 通过 WebSocket 通知用户所有的在线客户端
     * @param userId 用户id
     * @param call 调用客户端注册的方法
     * @param data 调用客户端注册方法的参数
     */
    void notifyClient(String userId, WebSocketClientRegisterConst call, JSONObject data);

    /**
     * 通过 WebSocket 通知用户所有的在线客户端
     * @param userId 用户id
     * @param call 调用客户端注册的方法
     */
    void notifyClient(String userId, WebSocketClientRegisterConst call);



}
