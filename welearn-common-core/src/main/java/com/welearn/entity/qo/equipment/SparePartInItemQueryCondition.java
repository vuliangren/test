package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.SparePartInItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : SparePartInItem Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/1 10:24:29
 * @see com.welearn.entity.po.equipment.SparePartInItem
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "SparePartInItemQueryCondition", description = "SparePartInItem 查询条件")
public class SparePartInItemQueryCondition extends SparePartInItem {

}
