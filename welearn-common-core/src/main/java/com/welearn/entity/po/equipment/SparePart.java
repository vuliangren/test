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
 * Persistent Object : ryme_equipment : spare_part
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/1 20:15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SparePart", description = "SparePart 领域实体")
public class SparePart extends BasePersistant
{
    /**
     * Description  : 来源:0-原厂备件 1-采购配件 2-回收配件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "来源:0-原厂备件 1-采购配件 2-回收配件", allowEmptyValue = false, position = 4 )
    private Integer origin;
    
    /**
     * Description  : 类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "类型id", allowEmptyValue = false, position = 5 )
    private String typeId;
    
    /**
     * Description  : 来源为原厂备件时表明属于哪个设备
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "来源为原厂备件时表明属于哪个设备", allowEmptyValue = true, position = 6 )
    private String equipmentId;
    
    /**
     * Description  : 单价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     * DefaultValue : 0.00
     */
    @ApiModelProperty( value = "单价", allowEmptyValue = true, position = 7 )
    private Double price;
    
    /**
     * Description  : 总量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "总量", allowEmptyValue = false, position = 8 )
    private Integer count;
    
    /**
     * Description  : 余量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "余量", allowEmptyValue = false, position = 9 )
    private Integer remain;
    
    /**
     * Description  : 在途出库数量(内含数量 表示将有货物运出去)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "在途出库数量(内含数量 表示将有货物运出去)", allowEmptyValue = true, position = 10 )
    private Integer inTransitOutCount;
    
    /**
     * Description  : 生产厂商名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "生产厂商名称", allowEmptyValue = true, position = 11 )
    private String manufacturerName;
    
    /**
     * Description  : 型号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "型号", allowEmptyValue = true, position = 12 )
    private String model;
    
    /**
     * Description  : 采购耗时(小时)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "采购耗时(小时)", allowEmptyValue = true, position = 13 )
    private Integer timeCost;
    
    /**
     * Description  : 使用寿命长短(0-10)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 5
     */
    @ApiModelProperty( value = "使用寿命长短(0-10)", allowEmptyValue = true, position = 14 )
    private Integer serviceLife;
    
    /**
     * Description  : 采购项目id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "采购项目id", allowEmptyValue = true, position = 15 )
    private String procurementId;
    
    /**
     * Description  : 供应商id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "供应商id", allowEmptyValue = true, position = 16 )
    private String supplierId;
    
    /**
     * Description  : 回收维修工程师id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "回收维修工程师id", allowEmptyValue = true, position = 17 )
    private String maintenanceEngineerId;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 18 )
    private String remark;
    
}
