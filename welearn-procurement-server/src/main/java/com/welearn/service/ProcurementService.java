package com.welearn.service;

import com.welearn.entity.po.apply.Contract;
import com.welearn.entity.po.procurement.Procurement;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.procurement.ProcurementQueryCondition;
import com.welearn.entity.vo.response.procurement.ProcurementDetailInfo;
import com.welearn.entity.vo.response.procurement.ProcurementInfo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : ProcurementService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ProcurementService extends BaseService<Procurement, ProcurementQueryCondition>{

    /**
     * 根据 查询条件 查询 采购项目详情
     * @param condition 查询条件
     * @return 采购项目详情列表
     */
    List<ProcurementInfo> searchInfo(ProcurementQueryCondition condition);

    /**
     * 选择待审批项创建采购项目
     * @param detailIds 带采购项id列表
     * @param userId 创建人id
     * @param name 采购项目名称
     * @param type 采购项目类型
     * @param budget 采购项目预算
     * @param method 采购方式
     * @return Procurement
     */
    Procurement selectItem(@NotBlank String name,
                           @NotNull Integer type,
                           @NotNull Double budget,
                           @NotNull Integer method,
                           @NotEmpty List<String> detailIds,
                           @NotBlank String userId);

    /**
     * 采购项目流程记录
     * @param documentJson 采购流程记录 Json
     * @param procurementId 采购项目id
     * @param isNextStep 采购项目是否进入下一步
     * @return Procurement
     */
    Procurement processRecord(@NotBlank String documentJson, @NotBlank String procurementId, @NotNull Boolean isNextStep);

    /**
     * 双方签订合同
     *
     * @param details 采购项目详情列表
     * @param contract      合同
     * @param procurementId 采购项目id
     * @param salespersonIDCard 销售负责人身份证号
     * @param salespersonName 销售负责人姓名
     * @param salespersonIDCardPhotocopy 销售负责人身份证复印件加盖公章
     * @return Procurement
     */
    Procurement signingContract(@NotEmpty List<ProcurementDetail> details, @NotNull Contract contract, @NotBlank String procurementId,
                                @NotBlank String salespersonIDCard, @NotBlank String salespersonName, @NotBlank String salespersonIDCardPhotocopy);

    /**
     * 根据 采购项目id 获取采购项目详情信息列表
     * @param procurementId 采购项目id
     * @return 采购项目详情信息列表
     */
    List<ProcurementDetailInfo> registerInfo(String procurementId);

    /**
     * 采购设备供应商登记设备对应的产品
     * @param details 采购项目详情列表
     * @param procurementId 采购项目id
     * @return Procurement
     */
    Procurement register(@NotEmpty List<ProcurementDetail> details, @NotBlank String procurementId);

    /**
     * 供应商对商品进行装箱与运输
     * @param contentJson 商品清单Json
     * @param packageJson 运输包装Json
     * @param procurementId 采购项目id
     * @param isNextStep 采购项目是否进入下一步
     * @return Procurement
     */
    Procurement distribution(@NotBlank String contentJson,
                             @NotBlank String packageJson,
                             @NotBlank String procurementId,
                             @NotNull Boolean isNextStep);

    /**
     * 院方对供应商运输来的商品进行商业验收
     * @param contentJson 商品清单Json
     * @param packageJson 运输包装Json
     * @param checkRecordJson 验收记录Json
     * @param procurementId 采购项目id
     * @param isNextStep 采购项目是否进入下一步
     * @return Procurement
     */
    Procurement commercialAcceptance(@NotBlank String contentJson,
                                     @NotBlank String packageJson,
                                     @NotBlank String checkRecordJson,
                                     @NotBlank String procurementId,
                                     @NotNull Boolean isNextStep);

    /**
     * 院方对供应商的商品进行技术验收
     * @param checkRecordJson 验收记录Json
     * @param checkReportPhotocopy 验收报告图片
     * @param procurementId 采购项目id
     * @param isNextStep 采购项目是否进入下一步
     * @return Procurement
     */
    Procurement technologyAcceptance(@NotBlank String checkRecordJson,
                                     String checkReportPhotocopy,
                                     @NotBlank String procurementId,
                                     @NotNull Boolean isNextStep);

    /**
     * 供应商申请对项目技术验收进行重审
     * @param procurementId 采购项目id
     */
    Procurement technologyAcceptanceRetrial(@NotBlank String procurementId);

    /**
     * 采购项目款项结算 发票配置
     * @param isNextStep 采购项目是否进入下一步
     * @param invoiceIds 未关联的 发票id 列表
     * @param procurementId 采购项目id
     * @return Procurement
     */
    Procurement settlementInvoice(@NotBlank String procurementId, List<String> invoiceIds,  @NotNull Boolean isNextStep);

    /**
     * 采购项目款项结算 支付凭证
     * @param procurementId 采购项目id
     * @return Procurement
     */
    Procurement settlementPayment(@NotBlank String procurementId);

    /**
     * 采购项目取消
     * @param cancellationReasonId 项目取消原因id
     * @param procurementId 采购项目id
     * @return Procurement
     */
    Procurement cancel(@NotBlank String cancellationReasonId, @NotBlank String procurementId);
}