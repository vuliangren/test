package com.welearn.dictionary.equipment;

/**
 * Description :
 * Created by Setsuna Jin on 2019/2/12.
 */
public enum RepairRequestStatusConst {
    // 0-待派工
    UN_DISPATCH,
    // 1-待重派
    UN_RE_DISPATCH,
    // 2-待领工
    UN_RECEIVE,
    // 3-待维修
    UN_REPAIR,
    // 4-维修中
    REPAIRING,
    // 5-待验收
    UN_CHECK,
    // 6-待评价
    UN_EVALUATE,
    // 7-已完成
    FINISH,
    // 8-待取消
    UN_CANCEL,
    // 9-已取消
    CANCEL,
    // 10-已中止
    INTERRUPT,
    // 11-待厂商维修
    WARRANTY_UN_REPAIR,
    // 12-待外援维修
    HELP_APPLY_UN_REPAIR,
    // 13-待中止
    WILL_INTERRUPT,
}
