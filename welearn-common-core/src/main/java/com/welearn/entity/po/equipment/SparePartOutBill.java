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
 * Persistent Object : ryme_equipment : spare_part_out_bill
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/28 15:27:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SparePartOutBill", description = "SparePartOutBill 领域实体")
public class SparePartOutBill extends BasePersistant
{
    /**
     * Description  : 出库简述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "出库简述", allowEmptyValue = false, position = 4 )
    private String outlook;
    
    /**
     * Description  : 状态: 0-待出库 1-已出库 2-已取消
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-待出库 1-已出库 2-已取消", allowEmptyValue = true, position = 5 )
    private Integer status;
    
    /**
     * Description  : 接收人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "接收人id", allowEmptyValue = false, position = 6 )
    private String recipientId;
    
    /**
     * Description  : 接收人名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "接收人名称", allowEmptyValue = false, position = 7 )
    private String recipientName;
    
    /**
     * Description  : 接收人签名id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "接收人签名id", allowEmptyValue = true, position = 8 )
    private String recipientSignatureId;
    
    /**
     * Description  : 接收方部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "接收方部门id", allowEmptyValue = false, position = 9 )
    private String recipientDepartmentId;
    
    /**
     * Description  : 接收方部门名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "接收方部门名称", allowEmptyValue = false, position = 10 )
    private String recipientDepartmentName;
    
    /**
     * Description  : 接收方公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "接收方公司id", allowEmptyValue = false, position = 11 )
    private String recipientCompanyId;
    
    /**
     * Description  : 接收方公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "接收方公司名称", allowEmptyValue = false, position = 12 )
    private String recipientCompanyName;
    
    /**
     * Description  : 总数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "总数量", allowEmptyValue = false, position = 13 )
    private Integer sumCount;
    
    /**
     * Description  : 总金额(小写)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "总金额(小写)", allowEmptyValue = false, position = 14 )
    private Double sumPrice;
    
    /**
     * Description  : 总金额(大写)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "总金额(大写)", allowEmptyValue = false, position = 15 )
    private String sumPriceChinese;
    
    /**
     * Description  : 核验人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "核验人id", allowEmptyValue = false, position = 16 )
    private String approverId;
    
    /**
     * Description  : 核验人名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "核验人名称", allowEmptyValue = false, position = 17 )
    private String approverName;
    
    /**
     * Description  : 库存方部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "库存方部门id", allowEmptyValue = false, position = 18 )
    private String approverDepartmentId;
    
    /**
     * Description  : 库存方部门名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "库存方部门名称", allowEmptyValue = false, position = 19 )
    private String approverDepartmentName;
    
    /**
     * Description  : 库存方公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "库存方公司id", allowEmptyValue = false, position = 20 )
    private String approverCompanyId;
    
    /**
     * Description  : 库存方公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "库存方公司名称", allowEmptyValue = false, position = 21 )
    private String approverCompanyName;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 22 )
    private String remark;
    
}
