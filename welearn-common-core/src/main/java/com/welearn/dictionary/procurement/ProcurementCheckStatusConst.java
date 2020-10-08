package com.welearn.dictionary.procurement;

/**
 * Description : 采购项目验收状态
 * Created by Setsuna Jin on 2018/10/29.
 */
public enum ProcurementCheckStatusConst {
    // 0-待验收
    UN_CHECKED,
    // 1-验收通过
    SUCCESS,
    // 2-验收失败
    FAILED,
    // 3-无须验收
    PASS,
}
