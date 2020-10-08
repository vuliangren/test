package com.welearn.dictionary.notify;

/**
 * Description :
 * Created by Setsuna Jin on 2019/2/11.
 */
public enum NoticeStatusConst {
    // 0-待通知
    UN_SEND,
    // 1-通知中
    SENDING,
    // 2-已通知
    ALL_SEND,
    // 3-部分通知(发生异常)
    PART_SEND,
}
