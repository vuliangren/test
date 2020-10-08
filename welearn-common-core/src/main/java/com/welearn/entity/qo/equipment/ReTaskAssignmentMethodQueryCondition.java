package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.ReTaskAssignmentMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description : ReTaskAssignmentMethod Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:40
 * @see com.welearn.entity.po.equipment.ReTaskAssignmentMethod
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ReTaskAssignmentMethodQueryCondition extends ReTaskAssignmentMethod {
    private String equipmentId;
}
