package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.MaintenanceMethod;
import com.welearn.entity.po.equipment.ReTaskAssignmentMethod;
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
public class TaskAssignmentDetail extends ReTaskAssignmentMethod {
    private Integer serveType;
    private String serveId;
    private Integer minInterval;
    private Integer score;
    private String name;
    private String description;
    private String stepInfoId;
    private String warningInfoId;
    private String dataTypeJson;
    private String companyId;
}
