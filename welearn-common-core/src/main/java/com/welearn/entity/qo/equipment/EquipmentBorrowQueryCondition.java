package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.EquipmentBorrow;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : EquipmentBorrow Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 16:20:18
 * @see com.welearn.entity.po.equipment.EquipmentBorrow
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "EquipmentBorrowQueryCondition", description = "EquipmentBorrow 查询条件")
public class EquipmentBorrowQueryCondition extends EquipmentBorrow {

}
