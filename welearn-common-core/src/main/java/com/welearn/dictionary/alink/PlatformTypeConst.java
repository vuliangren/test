package com.welearn.dictionary.alink;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/1.
 */
public enum PlatformTypeConst {
    IOTHUB, IOTHUB_SENIOR;

    public static PlatformTypeConst getByStr(String platformType){
        return PlatformTypeConst.valueOf(platformType.toUpperCase());
    }
}
