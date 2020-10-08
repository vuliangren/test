package com.welearn.error.exception;

/**
 * Description : 业务逻辑验证错误
 * Created by Setsuna Jin on 2018/1/18.
 */
public class BusinessVerifyFailedException extends RuntimeException {
    public BusinessVerifyFailedException(String format, Object... args) {
        super(String.format(format,args));
    }

    public BusinessVerifyFailedException(Throwable cause, String format, Object... args) {
        super(String.format(format,args),cause);
    }

    public BusinessVerifyFailedException(Throwable cause, String message) {
        super(message, cause);
    }

    public BusinessVerifyFailedException(Throwable cause) {
        super("Business Verify Failed", cause);
    }
}
