package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.TaskAssignment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description :
 * Created by Setsuna Jin on 2019/1/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskAssignmentInfo extends TaskAssignment {
    private String taskName;
    private String taskDescription;
    private Integer orderType;
    private Integer serveType;
    private String serveId;
}
