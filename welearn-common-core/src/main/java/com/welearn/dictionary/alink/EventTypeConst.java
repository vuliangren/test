package com.welearn.dictionary.alink;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/1.
 */
public enum EventTypeConst {
    INFO, ALERT, ERROR;

    public static EventTypeConst getByStr(String type) {
        return EventTypeConst.valueOf(type.toUpperCase());
    }
}
