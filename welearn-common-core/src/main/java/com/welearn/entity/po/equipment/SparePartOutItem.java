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
 * Persistent Object : ryme_equipment : spare_part_out_item
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/1 11:12:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SparePartOutItem", description = "SparePartOutItem 领域实体")
public class SparePartOutItem extends BasePersistant
{
    /**
     * Description  : 价格类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "价格类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件", allowEmptyValue = false, position = 4 )
    private Integer priceType;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 5 )
    private String name;
    
    /**
     * Description  : 规格
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "规格", allowEmptyValue = false, position = 6 )
    private String specification;
    
    /**
     * Description  : 单位
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "单位", allowEmptyValue = true, position = 7 )
    private String unit;
    
    /**
     * Description  : 单价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "单价", allowEmptyValue = false, position = 8 )
    private Double price;
    
    /**
     * Description  : 数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "数量", allowEmptyValue = false, position = 9 )
    private Integer count;
    
    /**
     * Description  : 金额
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "金额", allowEmptyValue = false, position = 10 )
    private Double sumPrice;
    
    /**
     * Description  : 排序
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "排序", allowEmptyValue = true, position = 11 )
    private Integer sort;
    
    /**
     * Description  : 状态:0-申请中 1-待出库 2-待确认 2-已出库 3-已取消
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态:0-申请中 1-待出库 2-待确认 2-已出库 3-已取消", allowEmptyValue = true, position = 12 )
    private Integer status;
    
    /**
     * Description  : 维修配件更换id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "维修配件更换id", allowEmptyValue = true, position = 13 )
    private String repairReplacementId;
    
    /**
     * Description  : 出库申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "出库申请id", allowEmptyValue = true, position = 14 )
    private String sparePartOutApplyId;
    
    /**
     * Description  : 出库申请类型
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "出库申请类型", allowEmptyValue = true, position = 15 )
    private String sparePartOutApplyType;
    
    /**
     * Description  : 出库单id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "出库单id", allowEmptyValue = true, position = 16 )
    private String sparePartOutBillId;
    
    /**
     * Description  : 生产厂商名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "生产厂商名称", allowEmptyValue = true, position = 17 )
    private String manufacturerName;
    
    /**
     * Description  : 型号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "型号", allowEmptyValue = true, position = 18 )
    private String model;
    
    /**
     * Description  : 配件批次id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "配件批次id", allowEmptyValue = false, position = 19 )
    private String sparePartId;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 20 )
    private String remark;
    
}
