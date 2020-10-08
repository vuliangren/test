package com.welearn.error.exception;

/**
 * Description : 用户认证验证错误
 * Created by Setsuna Jin on 2018/2/27.
 */
public class ApiAuthCheckFailedException extends RuntimeException {

    public ApiAuthCheckFailedException(String format, Object... args) {
        super(String.format(format,args));
    }

    public ApiAuthCheckFailedException(Throwable cause, String format, Object... args) {
        super(String.format(format,args),cause);
    }

    public ApiAuthCheckFailedException(Throwable cause, String message) {
        super(message, cause);
    }

    public ApiAuthCheckFailedException(Throwable cause) {
        super("Api Authorization Check Failed", cause);
    }
}

