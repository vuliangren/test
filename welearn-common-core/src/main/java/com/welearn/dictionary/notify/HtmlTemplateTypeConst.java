package com.welearn.dictionary.notify;

/**
 * Description : HTML 模板类型枚举
 * Created by Setsuna Jin on 2018/11/29.
 */
public enum HtmlTemplateTypeConst {
    // 供应商注册申请提交成功通知
    SUPPLIER_REGISTER_APPLY_SUCCESS,
    // 供应商注册申请处理提醒通知
    SUPPLIER_REGISTER_PROCESS_NOTIFY,
    // 供应商注册申请审批通过通知
    SUPPLIER_REGISTER_APPROVAL_PASS,
    // 供应商注册申请审批失败通知
    SUPPLIER_REGISTER_APPROVAL_FAIL,

    // 系统公告
    SYSTEM_NOTICE,
    // 公司公告
    COMPANY_NOTICE,
    // 部门公告
    DEPARTMENT_NOTICE,
    // 员工通知
    USER_NOTICE,

    // 密码重置
    RESET_PASSWORD,
    // 账户锁定
    LOCK_ACCOUNT,
    // 账户解除锁定
    UN_LOCK_ACCOUNT,
    // 忘记密码
    FORGET_PASSWORD,
    // 账户登记
    EMPLOYEE_ENTRY,
}
