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
 * Persistent Object : ryme_equipment : repair_replacement
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/1 17:54:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairReplacement", description = "RepairReplacement 领域实体")
public class RepairReplacement extends BasePersistant
{
    /**
     * Description  : 报修id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "报修id", allowEmptyValue = false, position = 4 )
    private String requestId;
    
    /**
     * Description  : 派工id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "派工id", allowEmptyValue = false, position = 5 )
    private String dispatchId;
    
    /**
     * Description  : 工程师id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师id", allowEmptyValue = false, position = 6 )
    private String engineerId;
    
    /**
     * Description  : 工程师名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师名称", allowEmptyValue = false, position = 7 )
    private String engineerName;
    
    /**
     * Description  : 外部派工id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "外部派工id", allowEmptyValue = true, position = 8 )
    private String outsideDispatchId;
    
    /**
     * Description  : 外部工程师id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "外部工程师id", allowEmptyValue = true, position = 9 )
    private String outsideEngineerId;
    
    /**
     * Description  : 外部工程师名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "外部工程师名称", allowEmptyValue = true, position = 10 )
    private String outsideEngineerName;
    
    /**
     * Description  : 配件类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "配件类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件", allowEmptyValue = false, position = 11 )
    private Integer type;
    
    /**
     * Description  : 状态: 0-待审批 1-待采购 2-待入库 3-待出库 4-待更换 5-审批失败 6-待取消 7-已取消 
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-待审批 1-待采购 2-待入库 3-待出库 4-待更换 5-审批失败 6-待取消 7-已取消 ", allowEmptyValue = true, position = 12 )
    private Integer status;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 13 )
    private String name;
    
    /**
     * Description  : 规格
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "规格", allowEmptyValue = false, position = 14 )
    private String specification;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "描述", allowEmptyValue = true, position = 15 )
    private String description;
    
    /**
     * Description  : 预计单价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "预计单价", allowEmptyValue = false, position = 16 )
    private Double price;
    
    /**
     * Description  : 预计数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "预计数量", allowEmptyValue = true, position = 17 )
    private Integer count;
    
    /**
     * Description  : 预计总价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "预计总价", allowEmptyValue = false, position = 18 )
    private Double sumPrice;
    
    /**
     * Description  : 配件采购申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "配件采购申请id", allowEmptyValue = true, position = 19 )
    private String applyId;
    
    /**
     * Description  : 采购项目id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "采购项目id", allowEmptyValue = true, position = 20 )
    private String procurementId;
    
    /**
     * Description  : 采购项目详情id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "采购项目详情id", allowEmptyValue = true, position = 21 )
    private String procurementDetailId;
    
    /**
     * Description  : 出库申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "出库申请id", allowEmptyValue = true, position = 22 )
    private String sparePartOutRepairApplyId;
    
    /**
     * Description  : 出库单id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "出库单id", allowEmptyValue = true, position = 23 )
    private String sparePartOutBillId;
    
    /**
     * Description  : 出库项id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "出库项id", allowEmptyValue = true, position = 24 )
    private String sparePartOutItemId;
    
    /**
     * Description  : 工程师签名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师签名", allowEmptyValue = false, position = 25 )
    private String signatureId;
    
}
