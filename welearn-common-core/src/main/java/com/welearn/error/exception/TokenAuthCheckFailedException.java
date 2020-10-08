package com.welearn.error.exception;

/**
 * Description : TOKEN 认证验证错误
 * Created by Setsuna Jin on 2018/2/27.
 */
public class TokenAuthCheckFailedException extends RuntimeException {

    public TokenAuthCheckFailedException(String format, Object... args) {
        super(String.format(format,args));
    }

    public TokenAuthCheckFailedException(Throwable cause, String format, Object... args) {
        super(String.format(format,args),cause);
    }

    public TokenAuthCheckFailedException(Throwable cause, String message) {
        super(message, cause);
    }

    public TokenAuthCheckFailedException(Throwable cause) {
        super("TOKEN Authorization Check Failed", cause);
    }
}

