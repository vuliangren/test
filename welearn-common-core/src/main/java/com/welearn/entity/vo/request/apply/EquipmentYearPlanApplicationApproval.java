package com.welearn.entity.vo.request.apply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentYearPlanApplicationApproval {
    @NotNull private String equipmentYearPlanApplicationId;
    @NotNull private Boolean isPassed;
    private String resultJson;
}