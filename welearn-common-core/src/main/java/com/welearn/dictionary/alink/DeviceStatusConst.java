package com.welearn.dictionary.alink;

import java.util.Arrays;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/1.
 */
public enum DeviceStatusConst {
    OFFLINE, ONLINE, UNACTIVE, DISABLE;


    public static DeviceStatusConst getByStr(String statusStr) {
        return DeviceStatusConst.valueOf(statusStr.toUpperCase());
    }
}
