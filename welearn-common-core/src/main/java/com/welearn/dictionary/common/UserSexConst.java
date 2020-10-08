package com.welearn.dictionary.common;

/**
 * Description : 用户性别
 * Created by Setsuna Jin on 2018/1/15.
 */
public enum UserSexConst {
    UNKNOWN(0), MAN(1), WOMAN(2);

    private int sex;
    UserSexConst(int sex){
        this.sex = sex;
    }

    int val(){
        return sex;
    }
}
