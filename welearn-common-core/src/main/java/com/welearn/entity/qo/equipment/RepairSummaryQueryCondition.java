package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.RepairSummary;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : RepairSummary Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:14:04
 * @see RepairSummary
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "RepairSummaryQueryCondition", description = "RepairSummary 查询条件")
public class RepairSummaryQueryCondition extends RepairSummary {

}
