package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.RepairInterrupt;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : RepairInterrupt Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/6 21:29:37
 * @see com.welearn.entity.po.equipment.RepairInterrupt
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "RepairInterruptQueryCondition", description = "RepairInterrupt 查询条件")
public class RepairInterruptQueryCondition extends RepairInterrupt {

}
