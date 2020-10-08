package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.SparePartOutItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : SparePartOutItem Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/28 15:28:00
 * @see com.welearn.entity.po.equipment.SparePartOutItem
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "SparePartOutItemQueryCondition", description = "SparePartOutItem 查询条件")
public class SparePartOutItemQueryCondition extends SparePartOutItem {

}
