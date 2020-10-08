package com.welearn.dictionary.common;

/**
 * 客户端类型
 */
public enum ClientTypeConst {
    ANY, WEB, WECHAT, SERVICE,
    // 2018/12/22 添加
    WECHAT_APP;

    public static ClientTypeConst get(String indexStr){
        Integer index = Integer.parseInt(indexStr);
        return ClientTypeConst.values()[index];
    }
}
