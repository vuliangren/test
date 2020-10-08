package com.welearn.entity.vo.request.equipment;

import java.lang.String;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import io.swagger.annotations.Api;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserSearch {
    @NotNull private String companyId;
    @NotNull private String departmentId;
    @NotNull private String positionId;
    @NotNull private String code;
}