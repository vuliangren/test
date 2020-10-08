package com.welearn.dictionary.equipment;

/**
 * Description :
 * Created by Setsuna Jin on 2019/2/12.
 */
public enum RepairPartReplaceStatusConst {
    // 0-待审批
    UN_APPROVAL,
    // 1-待采购
    UN_PROCUREMENT,
    // 2-待入库
    UN_STOCK_IN,
    // 3-待出库
    UN_STOCK_OUT,
    // 4-待更换
    UN_REPLACE,
    // 5-审批失败
    APPROVAL_FAIL,
    // 6-待取消
    UN_CANCEL,
    // 7-已取消
    CANCEL,
    // 8-已更换
    FINISH,
}
