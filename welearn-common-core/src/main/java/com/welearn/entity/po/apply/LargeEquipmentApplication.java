package com.welearn.entity.po.apply;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_apply : large_equipment_application
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/30 10:33:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LargeEquipmentApplication extends BasePersistant
{
    /**
     * Description  : 年度设备计划采购申请内容id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String equipmentYearPlanApplicationId;
    
    /**
     * Description  : 大型医疗设备装备申请文件
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:text][SIZE:65535]
     */
    private String applicationFile;
    
    /**
     * Description  : 相关部门批复文件
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:text][SIZE:65535]
     */
    private String replyFile;
    
}
