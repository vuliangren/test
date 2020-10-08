package com.welearn.entity.dto.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssignmentStatistic implements Serializable {
    private String id;
    private Date createdAt;
    private Integer status;
    private Integer priority;
    private Integer taskPriority;
}
