package com.welearn.entity.vo.request.finance;

import java.lang.String;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
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
public class DepartmentStockInit {
    @NotNull private String companyId;
    @NotNull private String departmentId;
    @NotNull private String departmentName;
    @NotNull private String buildingId;
    @NotNull private String floorId;
}