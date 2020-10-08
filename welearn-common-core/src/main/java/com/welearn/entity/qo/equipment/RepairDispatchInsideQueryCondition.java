package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.RepairDispatchInside;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * Description : RepairDispatchInside Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:13:58
 * @see RepairDispatchInside
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "RepairDispatchInsideQueryCondition", description = "RepairDispatchInside 查询条件")
public class RepairDispatchInsideQueryCondition extends RepairDispatchInside {
    private String equipmentId;
    private String equipmentTypeId;
    private String reporterDepartmentId;
    private String requestLever;
    private String requestStatus;
    private Date requestCreatedAtGreaterThan;
    private Date requestCreatedAtLessThan;
    private String engineerUserId;
    // 2019-05-30 添加
    private Date createdAtStart;
    private Date createdAtEnd;
}
