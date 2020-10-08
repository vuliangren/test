package com.welearn.dictionary.procurement;

/**
 * Description : 采购项目状态
 * Created by Setsuna Jin on 2018/10/29.
 */
public enum ProcurementStatusConst {
    // 0-选择待采购项
    SELECT_ITEM,
    // 1-采购流程记录
    PROCESS_RECORD,
    // 2-双方合同签订
    SIGNING_CONTRACT,
    // 3-产品信息登记
    REGISTER,
    // 4-商品货物配送
    DISTRIBUTE,
    // 5-项目商业验收
    COMMERCIAL_ACCEPTANCE,
    // 6-项目技术验收
    TECHNOLOGY_ACCEPTANCE,
    // 7-项目款项结算-发票登记
    SETTLEMENT_INVOICE,
    // 8-项目款项结算-余款结算
    SETTLEMENT_PAYMENT,
    // 9-采购项目结项
    FINISH,
}
