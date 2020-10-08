package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.EquipmentBorrowAccessory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : EquipmentBorrowAccessory Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 16:20:19
 * @see com.welearn.entity.po.equipment.EquipmentBorrowAccessory
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "EquipmentBorrowAccessoryQueryCondition", description = "EquipmentBorrowAccessory 查询条件")
public class EquipmentBorrowAccessoryQueryCondition extends EquipmentBorrowAccessory {

}
