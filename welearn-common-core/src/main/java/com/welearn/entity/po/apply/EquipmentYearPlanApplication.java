package com.welearn.entity.po.apply;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_apply : equipment_year_plan_application
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/30 11:19:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentYearPlanApplication extends BasePersistant
{
    /**
     * Description  : 年度设备采购计划id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String equipmentYearPlanId;
    
    /**
     * Description  : 大型医疗设备：0-非大型医疗设备，1-甲类大型医疗设备 2-乙类大型医疗设备
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer isLargeEquipment;
    
    /**
     * Description  : 设备类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String equipmentTypeId;
    
    /**
     * Description  : 设备类型名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:256]
     */
    @NotBlank
    private String equipmentTypeName;
    
    /**
     * Description  : 设备规格
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:256]
     */
    @NotBlank
    private String specification;
    
    /**
     * Description  : 预估单价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    private Double unitPrice;
    
    /**
     * Description  : 申购数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    private Integer count;
    
    /**
     * Description  : 预估总价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    private Double sumPrice;
    
    /**
     * Description  : 资金来源
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    private Integer capitalSource;
    
    /**
     * Description  : 专项资金类型
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    private Integer specialFundsType;
    
    /**
     * Description  : 申请理由类型
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    private Integer reasonType;
    
    /**
     * Description  : 原设备信息
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String oldEquipmentId;
    
    /**
     * Description  : 申请理由内容id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String reasonDescriptionId;
    
    /**
     * Description  : 设备选型
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:text][SIZE:65535]
     */
    @NotBlank
    private String recommendsJson;
    
    /**
     * Description  : 相关辅助配套设备
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:text][SIZE:65535]
     */
    @NotBlank
    private String auxiliariesJson;
    
    /**
     * Description  : 正常服务年限
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    private Integer serviceYear;
    
    /**
     * Description  : 每月使用人次
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    private Integer monthlyUsage;
    
    /**
     * Description  : 收费标准/人次
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:decimal][SCALE:2][PRECISION:14]
     */
    private Double fee;
    
    /**
     * Description  : 每年收入
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:decimal][SCALE:2][PRECISION:14]
     */
    private Double yearlyIncome;
    
    /**
     * Description  : 每年维护费用
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:decimal][SCALE:2][PRECISION:14]
     */
    private Double yearlyMaintenanceCost;
    
    /**
     * Description  : 每年耗材费用
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:decimal][SCALE:2][PRECISION:14]
     */
    private Double yearlyConsumablesCost;
    
    /**
     * Description  : 预计一年经济效益
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:decimal][SCALE:2][PRECISION:14]
     */
    private Double yearlyBenefit;
    
    /**
     * Description  : 预计回收成本年限
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    private Integer recoveryCostYears;
    
    /**
     * Description  : 社会效益分析id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String socialBenefitAnalysisId;
    
    /**
     * Description  : 技术可行性分析id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String technicalFeasibilityAnalysisId;
    
    /**
     * Description  : 安装条件可行性分析id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String installationConditionsAnalysisId;
    
    /**
     * Description  : 实地考察论证分析id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String fieldInvestigationAnalysisId;
    
    /**
     * Description  : 选型设备技术评价id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String recommendsTechnicalAnalysisId;
    
    /**
     * Description  : 状态: 0-审批中 1-审批失败 2-设备科审批通过 3-委员会审批通过 4-院长审批通过
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     * DefaultValue : 0
     */
    private Integer status;
    
    /**
     * Description  : 委员会评审结果
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:text][SIZE:65535]
     */
    private String committeeApprovalResultJson;
    
    /**
     * Description  : 院长评审结果
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:text][SIZE:65535]
     */
    private String directorApprovalResultJson;
    
}
