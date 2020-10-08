package com.welearn.dictionary.apply;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/20.
 */
public enum ApprovalResultConst {
    // 0-待审批
    UN_APPROVAL,
    // 1-审批通过
    PASS,
    // 2-审批失败
    REJECT,
    // 3-修改重审
    MODIFY_AND_RE_APPROVAL,
    // 4-无须审批
    NO_APPROVAL;

    public static ApprovalResultConst get(Integer ordinal){
        return ApprovalResultConst.values()[ordinal];
    }
}
