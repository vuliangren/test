package com.welearn.dictionary.equipment;

/**
 * Description : 设备借用状态
 * Created by Setsuna Jin on 2018/11/12.
 */
public enum EquipmentBorrowStatusConst {
    // 0-正常
    OK,
    // 1-借用待审批
    UN_APPROVAL,
    // 2-借用待领取
    UN_LOAN_OUT,
    // 3-借用待归还
    UN_GIVE_BACK,
}
