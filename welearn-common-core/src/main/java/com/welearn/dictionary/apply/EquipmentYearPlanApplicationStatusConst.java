package com.welearn.dictionary.apply;

/**
 * Description :
 * Created by Setsuna Jin on 2018/10/30.
 */
public enum EquipmentYearPlanApplicationStatusConst {
    // 0-审批中
    UN_APPROVAL,
    // 1-设备科审批失败
    DEPARTMENT_FAILED,
    // 2-设备科审批通过
    DEPARTMENT_PASS,
    // 3-委员会审批失败
    COMMITTEE_FAILED,
    // 4-委员会审批通过
    COMMITTEE_PASS,
    // 5-院长审批失败
    DIRECTOR_FAILED,
    // 6-院长审批通过
    DIRECTOR_PASS,
    // 7-采购已执行
    PURCHASING
}
