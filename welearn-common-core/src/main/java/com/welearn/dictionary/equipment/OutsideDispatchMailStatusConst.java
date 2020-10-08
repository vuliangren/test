package com.welearn.dictionary.equipment;

/**
 * Description : 外部派工快递寄件状态
 * Created by Setsuna Jin on 2019/2/12.
 */
public enum OutsideDispatchMailStatusConst {
    // 0-待寄件
    UN_SEND,
    // 1-已寄件
    SEND,
    // 2-已确认
    CHECK,
    // 3-待收件
    UN_RECEIVE,
    // 4-已收件
    RECEIVE,
}
