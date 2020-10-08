package com.welearn.entity.qo.apply;

import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description : EquipmentYearPlanApplication Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/28 11:12:52
 * @see EquipmentYearPlanApplication
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EquipmentYearPlanApplicationQueryCondition extends EquipmentYearPlanApplication {
    private String companyId;
    private String departmentId;
    private String applicantId;
    // 处理 大于某个状态值 sql: status > statusAfter
    private Integer statusAfter;
}
