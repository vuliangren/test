package com.welearn.error.exception;

/**
 * Description : 用户认证验证错误
 * Created by Setsuna Jin on 2018/2/27.
 */
public class UserAuthCheckFailedException extends RuntimeException {

    public UserAuthCheckFailedException(String format, Object... args) {
        super(String.format(format,args));
    }

    public UserAuthCheckFailedException(Throwable cause, String format, Object... args) {
        super(String.format(format,args),cause);
    }

    public UserAuthCheckFailedException(Throwable cause, String message) {
        super(message, cause);
    }

    public UserAuthCheckFailedException(Throwable cause) {
        super("User Authorization Check Failed", cause);
    }
}

