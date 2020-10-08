package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.SparePartInBill;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : SparePartInBill Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/1 10:24:28
 * @see com.welearn.entity.po.equipment.SparePartInBill
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "SparePartInBillQueryCondition", description = "SparePartInBill 查询条件")
public class SparePartInBillQueryCondition extends SparePartInBill {

}
