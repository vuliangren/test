package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.RepairRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * Description : RepairRequest Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:14:03
 * @see RepairRequest
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "RepairRequestQueryCondition", description = "RepairRequest 查询条件")
public class RepairRequestQueryCondition extends RepairRequest {
    private Date createdAtGreaterThan;
    private Date createdAtLessThan;
}
