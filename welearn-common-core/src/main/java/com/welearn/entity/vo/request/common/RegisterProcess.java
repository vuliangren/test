package com.welearn.entity.vo.request.common;

import java.lang.String;
import java.util.List;

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
public class RegisterProcess {
    @NotNull private String telephone;
    @NotNull private String authCode;
    @NotNull private List<String> roleIds;
    @NotNull private List<String> positionIds;
}