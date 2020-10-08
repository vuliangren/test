package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.InspectionRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : InspectionRecord Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/12 14:55:48
 * @see InspectionRecord
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "InspectionRecordQueryCondition", description = "InspectionRecord 查询条件")
public class InspectionRecordQueryCondition extends InspectionRecord {

}
