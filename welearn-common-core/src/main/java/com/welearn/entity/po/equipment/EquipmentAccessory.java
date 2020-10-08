package com.welearn.entity.po.equipment;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_equipment : equipment_accessory
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/6 20:33:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentAccessory extends BasePersistant
{
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String equipmentId;
    
    /**
     * Description  : 类型:0-操作附件 1-设备文档
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    private Integer type;
    
    /**
     * Description  : 是否可借用：0-不可，1-可
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 1
     */
    @NotNull
    private Boolean canBorrow;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @NotBlank
    private String name;
    
    /**
     * Description  : 规格
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    private String specification;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:1024]
     */
    private String description;
    
    /**
     * Description  : 状态: 0-待初始化, 1-正常
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer status;
    
    /**
     * Description  : 设备相关图片
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:65535]
     */
    private String photos;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:65535]
     */
    private String remark;
    
}
