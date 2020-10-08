package com.welearn.dictionary.notify;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description : 短信签名常量
 * Created by Setsuna Jin on 2019/3/18.
 */
@AllArgsConstructor
public enum SmsSignatureConst {
    RUIYANG_MEDICAL("锐阳医疗"),

    ;
    @Getter
    private String signature;
}
