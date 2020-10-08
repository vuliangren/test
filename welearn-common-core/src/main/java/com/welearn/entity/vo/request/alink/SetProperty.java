package com.welearn.entity.vo.request.alink;

import java.lang.Object;
import java.lang.String;
import java.util.Map;

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
public class SetProperty {
    @NotNull private String iotId;
    @NotNull private Map<String, Object> properties;
}