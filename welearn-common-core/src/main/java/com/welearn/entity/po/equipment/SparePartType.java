package com.welearn.entity.po.equipment;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Persistent Object : ryme_equipment : spare_part_type
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/28 15:28:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SparePartType", description = "SparePartType 领域实体")
public class SparePartType extends BasePersistant
{
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 4 )
    private String name;
    
    /**
     * Description  : 规格
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "规格", allowEmptyValue = false, position = 5 )
    private String specification;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "描述", allowEmptyValue = true, position = 6 )
    private String description;
    
    /**
     * Description  : 价格类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "价格类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件", allowEmptyValue = false, position = 7 )
    private Integer priceType;
    
    /**
     * Description  : 平均单价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     * DefaultValue : 0.00
     */
    @ApiModelProperty( value = "平均单价", allowEmptyValue = true, position = 8 )
    private Double priceAvg;
    
    /**
     * Description  : 单位
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "单位", allowEmptyValue = true, position = 9 )
    private String unit;
    
    /**
     * Description  : 图片
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "图片", allowEmptyValue = true, position = 10 )
    private String picture;
    
    /**
     * Description  : 文档
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "文档", allowEmptyValue = true, position = 11 )
    private String document;
    
    /**
     * Description  : 最小库存数量(低于报警) 0-不限制
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "最小库存数量(低于报警) 0-不限制", allowEmptyValue = true, position = 12 )
    private Integer minCount;
    
    /**
     * Description  : 最大库存数量(高于报警) 0-不限制
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "最大库存数量(高于报警) 0-不限制", allowEmptyValue = true, position = 13 )
    private Integer maxCount;
    
    /**
     * Description  : 在途入库数量(内含数量 表示将有货物运进来)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "在途入库数量(内含数量 表示将有货物运进来)", allowEmptyValue = true, position = 14 )
    private Integer inTransitInCount;
    
    /**
     * Description  : 在途出库数量(内含数量 表示将有货物运出去)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "在途出库数量(内含数量 表示将有货物运出去)", allowEmptyValue = true, position = 15 )
    private Integer inTransitOutCount;
    
    /**
     * Description  : 进货量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "进货量", allowEmptyValue = true, position = 16 )
    private Integer retailPurchases;
    
    /**
     * Description  : 消耗量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "消耗量", allowEmptyValue = true, position = 17 )
    private Integer consumption;

    /**
     * 计算当前逻辑余量
     * @return 逻辑余量
     */
    public Integer calLogicBalance(){
        return this.retailPurchases + this.inTransitInCount - this.consumption - this.inTransitOutCount;
    }
}
