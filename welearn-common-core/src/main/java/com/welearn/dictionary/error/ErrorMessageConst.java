package com.welearn.dictionary.error;

import lombok.Getter;

/**
 * 异常信息
 */
public enum ErrorMessageConst {

    ARGUMENT_VALID_FAILED(ErrorTypeConst.ARGUMENT,"00001","参数验证失败"),

    USER_AUTH_FAILED(ErrorTypeConst.AUTH,"00001","用户认证失败"),
    API_AUTH_CHECK_FAILED(ErrorTypeConst.AUTH,"00002","接口认证失败"),
    PERMISSION_CHECK_FAILED(ErrorTypeConst.AUTH,"00003","权限验证失败"),

    SYSTEM_ERROR(ErrorTypeConst.SYSTEM,"00001","系统异常"),
    BUSINESS_ERROR(ErrorTypeConst.BUSINESS,"00001","业务异常");



    ErrorMessageConst(ErrorTypeConst type, String code, String message) {
        this.type = type;
        // type和code 一起具有唯一性即可
        this.code = type.name() + "_" + code;
        this.message = message;
    }
    @Getter
    private ErrorTypeConst type;
    @Getter
    private String code;
    @Getter
    private String message;
}
