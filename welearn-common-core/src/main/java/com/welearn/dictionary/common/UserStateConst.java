package com.welearn.dictionary.common;

/**
 * Description : 用户状态
 * Created by Setsuna Jin on 2018/1/15.
 */
public enum UserStateConst {
    LEAVE(0),
    NORMAL(1),
    // 注册中
    REGISTERING(2);

    private int status;
    UserStateConst(int status){
        this.status = status;
    }

    int val(){
        return status;
    }
}
