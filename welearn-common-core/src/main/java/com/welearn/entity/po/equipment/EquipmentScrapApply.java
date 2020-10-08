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
 * Persistent Object : ryme_equipment : equipment_scrap_apply
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/28 10:48:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "EquipmentScrapApply", description = "EquipmentScrapApply 领域实体")
public class EquipmentScrapApply extends BasePersistant
{
    /**
     * Description  : 设备类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型id", allowEmptyValue = false, position = 4 )
    private String equipmentTypeId;
    
    /**
     * Description  : 设备产品id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备产品id", allowEmptyValue = false, position = 5 )
    private String productId;
    
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 6 )
    private String equipmentId;
    
    /**
     * Description  : 设备名称规格
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "设备名称规格", allowEmptyValue = false, position = 7 )
    private String equipmentName;
    
    /**
     * Description  : 设备厂商型号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备厂商型号", allowEmptyValue = false, position = 8 )
    private String equipmentProductName;
    
    /**
     * Description  : 设备单价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "设备单价", allowEmptyValue = false, position = 9 )
    private Double originValue;
    
    /**
     * Description  : 申请人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人id", allowEmptyValue = false, position = 10 )
    private String applicantId;
    
    /**
     * Description  : 申请人姓名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人姓名", allowEmptyValue = false, position = 11 )
    private String applicantName;
    
    /**
     * Description  : 申请人部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人部门id", allowEmptyValue = false, position = 12 )
    private String applicantDepartmentId;
    
    /**
     * Description  : 申请人部门名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人部门名称", allowEmptyValue = false, position = 13 )
    private String applicantDepartmentName;
    
    /**
     * Description  : 申请人公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人公司id", allowEmptyValue = false, position = 14 )
    private String applicantCompanyId;
    
    /**
     * Description  : 申请人签名id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人签名id", allowEmptyValue = false, position = 15 )
    private String applicantSignatureId;
    
    /**
     * Description  : 报废理由: [0,1,2...]
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     * DefaultValue : []
     */
    @ApiModelProperty( value = "报废理由: [0,1,2...]", allowEmptyValue = true, position = 16 )
    private String reasonTypeJson;
    
    /**
     * Description  : 报废理由备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "报废理由备注", allowEmptyValue = true, position = 17 )
    private String reasonDetail;
    
    /**
     * Description  : 报废说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "报废说明", allowEmptyValue = true, position = 18 )
    private String description;
    
    /**
     * Description  : 状态: 0-待审批 1-审批通过 2-审批失败
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-待审批 1-审批通过 2-审批失败", allowEmptyValue = true, position = 19 )
    private Integer status;
    
    /**
     * Description  : 申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "申请id", allowEmptyValue = true, position = 20 )
    private String applicationId;
    
    /**
     * Description  : 设备所属部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备所属部门id", allowEmptyValue = false, position = 21 )
    private String equipmentDepartmentId;
    
}
