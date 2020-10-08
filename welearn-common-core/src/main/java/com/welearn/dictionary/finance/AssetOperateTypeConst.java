package com.welearn.dictionary.finance;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/7.
 */
public enum AssetOperateTypeConst {
    // 0-登记-入库
    IN_REGISTER,
    // 1-采购-入库
    IN_PROCUREMENT,
    // 2-借出-出库
    OUT_LOAN,
    // 3-归还-入库
    IN_RETURN,
    // 4-领用-出库
    OUT_receive,
    // 5-封存-入库
    IN_storage,
    // 6-借入-入库
    IN_BORROW,
    // 7-外还-出库
    OUT_RETURN,
    // 8-调剂-出库
    OUT_ADJUST,
    // 9-报废-出库
    OUT_SCRAP,
    // 10-遗失-出库
    OUT_LOSS
}
