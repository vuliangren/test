package com.welearn.error.exception;

/**
 * Description : 权限检查错误
 * Created by Setsuna Jin on 2018/2/27.
 */
public class PermissionCheckFailedException extends RuntimeException {

    public PermissionCheckFailedException(String format, Object... args) {
        super(String.format(format,args));
    }

    public PermissionCheckFailedException(Throwable cause, String format, Object... args) {
        super(String.format(format,args),cause);
    }

    public PermissionCheckFailedException(Throwable cause, String message) {
        super(message, cause);
    }

    public PermissionCheckFailedException(Throwable cause) {
        super("Permission Check Failed", cause);
    }
}

