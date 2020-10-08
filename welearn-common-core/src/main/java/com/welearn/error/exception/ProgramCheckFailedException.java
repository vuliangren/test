package com.welearn.error.exception;

/**
 * Description : 编程检查错误
 * Created by Setsuna Jin on 2018/2/27.
 */
public class ProgramCheckFailedException extends RuntimeException {

    public ProgramCheckFailedException(String format, Object... args) {
        super(String.format(format,args));
    }

    public ProgramCheckFailedException(Throwable cause, String format, Object... args) {
        super(String.format(format,args),cause);
    }

    public ProgramCheckFailedException(Throwable cause, String message) {
        super(message, cause);
    }

    public ProgramCheckFailedException(Throwable cause) {
        super("Program Check Failed", cause);
    }
}

