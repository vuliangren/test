package com.welearn.entity.po.equipment;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_equipment : equipment_borrow
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/28 10:48:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "EquipmentBorrow", description = "EquipmentBorrow 领域实体")
public class EquipmentBorrow extends BasePersistant
{
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 4 )
    private String equipmentId;
    
    /**
     * Description  : 设备类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型id", allowEmptyValue = false, position = 5 )
    private String equipmentTypeId;
    
    /**
     * Description  : 固定资产id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "固定资产id", allowEmptyValue = true, position = 6 )
    private String fixedAssetId;
    
    /**
     * Description  : 状态:0-借用待审批 1-借用待领取 2-借出待归还 3-归还待验收 4-验收通过 5-验收失败
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态:0-借用待审批 1-借用待领取 2-借出待归还 3-归还待验收 4-验收通过 5-验收失败", allowEmptyValue = true, position = 7 )
    private Integer status;
    
    /**
     * Description  : 验收结果: 0-正常 1-消耗损坏 2-人为损坏 3-附件缺失 4-设备缺失 5-全部缺失
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @ApiModelProperty( value = "验收结果: 0-正常 1-消耗损坏 2-人为损坏 3-附件缺失 4-设备缺失 5-全部缺失", allowEmptyValue = true, position = 8 )
    private Integer checkResult;
    
    /**
     * Description  : 借出时间点
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "借出时间点", allowEmptyValue = true, position = 9 )
    private Date loanOutAt;
    
    /**
     * Description  : 计划归还时间点
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "计划归还时间点", allowEmptyValue = true, position = 10 )
    private Date giveBackPlanAt;
    
    /**
     * Description  : 实际归还时间点
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "实际归还时间点", allowEmptyValue = true, position = 11 )
    private Date giveBackRealAt;
    
    /**
     * Description  : 折旧单价(每天折旧的金额)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     * DefaultValue : 0.00
     */
    @ApiModelProperty( value = "折旧单价(每天折旧的金额)", allowEmptyValue = true, position = 12 )
    private Double depreciationPrice;
    
    /**
     * Description  : 借用倍率(默认 1.0)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     * DefaultValue : 1.00
     */
    @ApiModelProperty( value = "借用倍率(默认 1.0)", allowEmptyValue = true, position = 13 )
    private Double multiplyingPower;
    
    /**
     * Description  : 借用单价(单位:天 折旧单价 * 借用倍率)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     * DefaultValue : 0.00
     */
    @ApiModelProperty( value = "借用单价(单位:天 折旧单价 * 借用倍率)", allowEmptyValue = true, position = 14 )
    private Double dayPrice;
    
    /**
     * Description  : 实际借用天数(精确到0.01天 )
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @ApiModelProperty( value = "实际借用天数(精确到0.01天 )", allowEmptyValue = true, position = 15 )
    private Double dayCount;
    
    /**
     * Description  : 借用总花费
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @ApiModelProperty( value = "借用总花费", allowEmptyValue = true, position = 16 )
    private Double sumPrice;
    
    /**
     * Description  : 归还满意度
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @ApiModelProperty( value = "归还满意度", allowEmptyValue = true, position = 17 )
    private Integer satisfaction;
    
    /**
     * Description  : 借用申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "借用申请id", allowEmptyValue = true, position = 18 )
    private String borrowApplicationId;
    
    /**
     * Description  : 归还申请id(暂保留)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "归还申请id(暂保留)", allowEmptyValue = true, position = 19 )
    private String giveBackApplicationId;
    
    /**
     * Description  : 借入科室id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "借入科室id", allowEmptyValue = false, position = 20 )
    private String borrowDepartmentId;
    
    /**
     * Description  : 借入科室名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "借入科室名称", allowEmptyValue = false, position = 21 )
    private String borrowDepartmentName;
    
    /**
     * Description  : 借入人员id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "借入人员id", allowEmptyValue = false, position = 22 )
    private String borrowUserId;
    
    /**
     * Description  : 借入人员名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "借入人员名称", allowEmptyValue = false, position = 23 )
    private String borrowUserName;
    
    /**
     * Description  : 借入人员签字id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "借入人员签字id", allowEmptyValue = true, position = 24 )
    private String borrowUserSignatureId;
    
    /**
     * Description  : 归还人员id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "归还人员id", allowEmptyValue = true, position = 25 )
    private String giveBackUserId;
    
    /**
     * Description  : 归还人员名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "归还人员名称", allowEmptyValue = true, position = 26 )
    private String giveBackUserName;
    
    /**
     * Description  : 归还人员签字id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "归还人员签字id", allowEmptyValue = true, position = 27 )
    private String giveBackUserSignatureId;
    
    /**
     * Description  : 借出科室id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "借出科室id", allowEmptyValue = true, position = 28 )
    private String loanOutDepartmentId;
    
    /**
     * Description  : 借出科室名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "借出科室名称", allowEmptyValue = true, position = 29 )
    private String loanOutDepartmentName;
    
    /**
     * Description  : 借出人员id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "借出人员id", allowEmptyValue = true, position = 30 )
    private String loanOutUserId;
    
    /**
     * Description  : 借出人员名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "借出人员名称", allowEmptyValue = true, position = 31 )
    private String loanOutUserName;
    
    /**
     * Description  : 借出人员签字id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "借出人员签字id", allowEmptyValue = true, position = 32 )
    private String loanOutUserSignatureId;
    
    /**
     * Description  : 验收人员id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "验收人员id", allowEmptyValue = true, position = 33 )
    private String checkUserId;
    
    /**
     * Description  : 验收人员名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "验收人员名称", allowEmptyValue = true, position = 34 )
    private String checkUserName;
    
    /**
     * Description  : 验收人员签字id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "验收人员签字id", allowEmptyValue = true, position = 35 )
    private String checkUserSignatureId;
    
    /**
     * Description  : 借用设备附件数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "借用设备附件数量", allowEmptyValue = true, position = 36 )
    private Integer accessoryCount;
    
    /**
     * Description  : 借入备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "借入备注", allowEmptyValue = true, position = 37 )
    private String borrowRemark;
    
    /**
     * Description  : 借出备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "借出备注", allowEmptyValue = true, position = 38 )
    private String loanOutRemark;
    
    /**
     * Description  : 验收备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "验收备注", allowEmptyValue = true, position = 39 )
    private String checkRemark;
    
    /**
     * Description  : 归还验收单
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "归还验收单", allowEmptyValue = true, position = 40 )
    private String giveBackBill;
    
    /**
     * Description  : 借出领用单
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "借出领用单", allowEmptyValue = true, position = 41 )
    private String loanOutBill;
    
}
