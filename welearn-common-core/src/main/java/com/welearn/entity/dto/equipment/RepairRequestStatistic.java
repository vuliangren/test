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
public class RepairRequestStatistic implements Serializable {
    private String id;
    private Integer status;
    private Date createdAt;
    private Date finishedAt;
    private String departmentId;
    private String departmentName;
    private Boolean isHelpApply;
    private Boolean isWarranty;
}
