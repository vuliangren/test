package com.welearn.dictionary.finance;

/**
 * Description : 库存执行类型
 * Created by Setsuna Jin on 2018/12/17.
 */
public enum StockExecuteTypeConst {
    // 0-按计划执行(批量采购)
    PLAN,
    // 1-按实际执行(零库存, 但部门出库登记需及时)
    REAL,
}
