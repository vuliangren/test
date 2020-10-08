package com.welearn.error.exception;

/**
 * Description : 数据库操作失败异常
 * Created by Setsuna Jin on 2018/1/7.
 */
public class DbOperationFailedException extends RuntimeException {

    public DbOperationFailedException(String format, Object... args) {
        super(String.format(format,args));
    }

    public DbOperationFailedException(Throwable cause, String format, Object... args) {
        super(String.format(format,args),cause);
    }

    public DbOperationFailedException(Throwable cause, String message) {
        super(message, cause);
    }

    public DbOperationFailedException(Throwable cause) {
        super("Db Operation Failed", cause);
    }
}
