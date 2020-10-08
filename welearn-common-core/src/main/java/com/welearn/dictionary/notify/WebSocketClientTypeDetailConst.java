package com.welearn.dictionary.notify;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/20.
 */
@AllArgsConstructor
public enum  WebSocketClientTypeDetailConst {
    ALL_CLIENT("所有 客户端", -1),
    // -------------------------------------------
    USER_WEB_CLIENT("用户 WEB端", 1000),
    USER_WXX_CLIENT("用户 微信小程序端", 1001),
    // -------------------------------------------
    DEVICE_PI_CLIENT("设备 树莓派端", 2000),
    ;
    @Getter
    private String description;
    @Getter
    private Integer value;
}
