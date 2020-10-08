package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.TaskAssignment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : TaskAssignment Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:40
 * @see com.welearn.entity.po.equipment.TaskAssignment
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TaskAssignmentQueryCondition extends TaskAssignment {
    private String serveId;
    private Integer serveType;
    private Integer orderType;
    private Integer statusLessThan;
    private Integer statusGreaterThan;
    // 用于关联工程师id 进行查询
    private String userId;
    // 2019-05-30 添加
    private Date createdAtStart;
    private Date createdAtEnd;
}
