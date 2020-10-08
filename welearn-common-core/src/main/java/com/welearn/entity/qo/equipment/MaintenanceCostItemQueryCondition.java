package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.MaintenanceCostItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : MaintenanceCostItem Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 16:20:34
 * @see com.welearn.entity.po.equipment.MaintenanceCostItem
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "MaintenanceCostItemQueryCondition", description = "MaintenanceCostItem 查询条件")
public class MaintenanceCostItemQueryCondition extends MaintenanceCostItem {

}
