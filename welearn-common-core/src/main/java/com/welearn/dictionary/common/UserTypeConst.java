package com.welearn.dictionary.common;

/**
 * 用户类型
 */
public enum UserTypeConst {
    // 任意 一般指代用户
    ANY,
    // 机器 一般指机器人
    MACHINE;

    public static UserTypeConst get(String indexStr){
        return UserTypeConst.values()[Integer.parseInt(indexStr)];
    }
}
