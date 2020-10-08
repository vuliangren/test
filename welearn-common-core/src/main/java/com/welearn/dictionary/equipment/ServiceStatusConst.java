package com.welearn.dictionary.equipment;

/**
 * Description : 设备业务状态
 * Created by Setsuna Jin on 2018/11/12.
 */
public enum ServiceStatusConst {
    // 0-正常
    NORMAL,
    // 1-入库待领用
    STOCK_RECEIVE_UN_USE,
    // 2-借用待审批
    BORROW_UN_APPROVAL,
    // 3-借用待归还
    BORROW_UN_RETURN,
    // 4-调剂待审批
    TRANSFER_UN_APPROVAL,
    // 5-调剂待封存
    TRANSFER_UN_SEAL,
    // 6-调剂待处理
    TRANSFER_UN_PROCESS,
    // 7-报废待审批
    SCRAP_UN_APPROVAL,
    // 8-报废待封存
    SCRAP_UN_SEAL,
    // 9-报废待处理
    SCRAP_UN_PROCESS,
    // 10-报修待派工
    REPAIR_UN_DISPATCH,
    // 11-报修待领工
    REPAIR_UN_LEAD_WORK,
    // 12-报修待维修
    REPAIR_UN_SERVICE,
    // 13-维修待验收
    REPAIR_UN_CHECK,
    // 14-维护待完成
    MAINTENANCE_UN_FINISH,
}
