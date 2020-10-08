package com.welearn.entity.vo.request.procurement;

import java.lang.Boolean;
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
public class Distribution {
    @NotNull private String contentJson;
    @NotNull private String packageJson;
    @NotNull private String procurementId;
    @NotNull private Boolean isNextStep;
}