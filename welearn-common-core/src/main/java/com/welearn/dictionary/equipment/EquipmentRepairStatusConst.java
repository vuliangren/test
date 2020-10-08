package com.welearn.dictionary.equipment;

/**
 * Description : 设备维修状态
 * Created by Setsuna Jin on 2018/11/12.
 */
public enum EquipmentRepairStatusConst {
    // 0-正常
    OK,
    // 1-报修待派工
    UN_DISPATCH,
    // 2-报修待领工
    UN_RECEIVE,
    // 3-报修待维修
    UN_REPAIR,
    // 4-维修待验收
    UN_CHECK,
}
