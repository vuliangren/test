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
 * Persistent Object : ryme_equipment : spare_part_in_bill
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/1 15:16:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SparePartInBill", description = "SparePartInBill 领域实体")
public class SparePartInBill extends BasePersistant
{
    /**
     * Description  : 入库简述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "入库简述", allowEmptyValue = false, position = 4 )
    private String outlook;
    
    /**
     * Description  : 来源:0-原厂备件 1-采购配件 2-回收配件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "来源:0-原厂备件 1-采购配件 2-回收配件", allowEmptyValue = false, position = 5 )
    private Integer origin;
    
    /**
     * Description  : 申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "申请id", allowEmptyValue = true, position = 6 )
    private String applyId;
    
    /**
     * Description  : 状态: 0-待入库 1-已入库 2-已取消
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-待入库 1-已入库 2-已取消", allowEmptyValue = true, position = 7 )
    private Integer status;
    
    /**
     * Description  : 送货人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "送货人id", allowEmptyValue = false, position = 8 )
    private String senderId;
    
    /**
     * Description  : 送货人名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "送货人名称", allowEmptyValue = false, position = 9 )
    private String senderName;
    
    /**
     * Description  : 送货方部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "送货方部门id", allowEmptyValue = false, position = 10 )
    private String senderDepartmentId;
    
    /**
     * Description  : 送货方部门名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "送货方部门名称", allowEmptyValue = false, position = 11 )
    private String senderDepartmentName;
    
    /**
     * Description  : 送货方公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "送货方公司id", allowEmptyValue = false, position = 12 )
    private String senderCompanyId;
    
    /**
     * Description  : 送货方公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "送货方公司名称", allowEmptyValue = false, position = 13 )
    private String senderCompanyName;
    
    /**
     * Description  : 总数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @ApiModelProperty( value = "总数量", allowEmptyValue = true, position = 14 )
    private Integer sumCount;
    
    /**
     * Description  : 总金额(小写)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @ApiModelProperty( value = "总金额(小写)", allowEmptyValue = true, position = 15 )
    private Double sumPrice;
    
    /**
     * Description  : 总金额(大写)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "总金额(大写)", allowEmptyValue = true, position = 16 )
    private String sumPriceChinese;
    
    /**
     * Description  : 核验人id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "核验人id", allowEmptyValue = true, position = 17 )
    private String approverId;
    
    /**
     * Description  : 核验人名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "核验人名称", allowEmptyValue = true, position = 18 )
    private String approverName;
    
    /**
     * Description  : 核验人签名id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "核验人签名id", allowEmptyValue = true, position = 19 )
    private String approverSignatureId;
    
    /**
     * Description  : 库存方部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "库存方部门id", allowEmptyValue = true, position = 20 )
    private String approverDepartmentId;
    
    /**
     * Description  : 库存方部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "库存方部门名称", allowEmptyValue = true, position = 21 )
    private String approverDepartmentName;
    
    /**
     * Description  : 库存方公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "库存方公司id", allowEmptyValue = true, position = 22 )
    private String approverCompanyId;
    
    /**
     * Description  : 库存方公司名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "库存方公司名称", allowEmptyValue = true, position = 23 )
    private String approverCompanyName;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 24 )
    private String remark;

    /**
     * 复制其它入库单的核验人信息
     * @param bill 其它入库单
     */
    public void copyApproverInfo(SparePartInBill bill){
        this.setApproverId(bill.getApproverId());
        this.setApproverName(bill.getApproverName());
        this.setApproverDepartmentId(bill.getApproverDepartmentId());
        this.setApproverDepartmentName(bill.getApproverDepartmentName());
        this.setApproverCompanyId(bill.getApproverCompanyId());
        this.setApproverCompanyName(bill.getApproverCompanyName());
    }

}
