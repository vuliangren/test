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
 * Persistent Object : ryme_equipment : equipment_borrow_accessory
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 16:20:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "EquipmentBorrowAccessory", description = "EquipmentBorrowAccessory 领域实体")
public class EquipmentBorrowAccessory extends BasePersistant
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
     * Description  : 设备借用id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备借用id", allowEmptyValue = false, position = 5 )
    private String borrowId;
    
    /**
     * Description  : 设备附件id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备附件id", allowEmptyValue = false, position = 6 )
    private String accessoryId;
    
    /**
     * Description  : 借用人员id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "借用人员id", allowEmptyValue = false, position = 7 )
    private String borrowUserId;
    
    /**
     * Description  : 借出人员id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "借出人员id", allowEmptyValue = true, position = 8 )
    private String loanOutUserId;
    
    /**
     * Description  : 验收人员id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "验收人员id", allowEmptyValue = true, position = 9 )
    private String checkUserId;
    
    /**
     * Description  : 验收人员名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "验收人员名称", allowEmptyValue = true, position = 10 )
    private String checkUserName;
    
    /**
     * Description  : 验收结果: 0-正常 1-损坏 2-缺失
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "验收结果: 0-正常 1-损坏 2-缺失", allowEmptyValue = true, position = 11 )
    private Integer status;
    
    /**
     * Description  : 验收时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "验收时间", allowEmptyValue = true, position = 12 )
    private Date checkedAt;
    
}
