package com.welearn.dictionary.equipment;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/11.
 */
public enum EquipmentBorrowApplyStatusConst {
    // 0-借用待审批
    UN_APPROVAL,
    // 1-借用待领取
    UN_LOAN_OUT,
    // 2-借出待归还
    UN_GIVE_BACK,
    // 3-归还待验收
    UN_CHECK,
    // 4-验收通过
    CHECK_SUCCESS,
    // 5-验收失败
    CHECK_FAILED,
    // 6-审批失败
    APPROVAL_FAILED,
    // 7-取消借用
    CANCEL,
}
