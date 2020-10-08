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
 * Persistent Object : ryme_equipment : equipment_guarantee_repair_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/21 10:20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "EquipmentGuaranteeRepairRecord", description = "EquipmentGuaranteeRepairRecord 领域实体")
public class EquipmentGuaranteeRepairRecord extends BasePersistant
{
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 4 )
    private String equipmentId;
    
    /**
     * Description  : 维保公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "维保公司id", allowEmptyValue = true, position = 5 )
    private String companyId;
    
    /**
     * Description  : 维保公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "维保公司名称", allowEmptyValue = false, position = 6 )
    private String companyName;
    
    /**
     * Description  : 维保公司联系方式
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:16]
     */
    @NotBlank
    @ApiModelProperty( value = "维保公司联系方式", allowEmptyValue = false, position = 7 )
    private String companyContact;
    
    /**
     * Description  : 保修开始时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "保修开始时间", allowEmptyValue = true, position = 8 )
    private Date startAt;
    
    /**
     * Description  : 保修结束时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "保修结束时间", allowEmptyValue = true, position = 9 )
    private Date endAt;
    
    /**
     * Description  : 保修类型: 0-出厂保修 1-原厂续保 2-第三方保修 3-其它保修
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "保修类型: 0-出厂保修 1-原厂续保 2-第三方保修 3-其它保修", allowEmptyValue = true, position = 10 )
    private Integer type;
    
    /**
     * Description  : 合同id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "合同id", allowEmptyValue = false, position = 11 )
    private String contractId;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 12 )
    private String remark;
    
}
