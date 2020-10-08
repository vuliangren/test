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
public class RepairCostStatistic implements Serializable {
    private Date createdAt;
    private String id;
    private Double sumPrice;
    private String departmentId;
    private String departmentName;

}
