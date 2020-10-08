package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.RepairCostItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : RepairCostItem Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:13:57
 * @see RepairCostItem
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "RepairCostItemQueryCondition", description = "RepairCostItem 查询条件")
public class RepairCostItemQueryCondition extends RepairCostItem {

}
