package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.RepairCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : RepairCheck Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/6 16:12:41
 * @see RepairCheck
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "RepairCheckQueryCondition", description = "RepairCheck 查询条件")
public class RepairCheckQueryCondition extends RepairCheck {

}
