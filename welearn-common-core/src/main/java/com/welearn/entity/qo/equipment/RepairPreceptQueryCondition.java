package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.RepairPrecept;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : RepairPrecept Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:14:01
 * @see RepairPrecept
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "RepairPreceptQueryCondition", description = "RepairPrecept 查询条件")
public class RepairPreceptQueryCondition extends RepairPrecept {

}
