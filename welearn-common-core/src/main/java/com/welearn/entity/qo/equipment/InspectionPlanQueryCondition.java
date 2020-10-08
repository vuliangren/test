package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.InspectionPlan;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : InspectionPlan Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/12 14:55:47
 * @see InspectionPlan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "InspectionPlanQueryCondition", description = "InspectionPlan 查询条件")
public class InspectionPlanQueryCondition extends InspectionPlan {

}
