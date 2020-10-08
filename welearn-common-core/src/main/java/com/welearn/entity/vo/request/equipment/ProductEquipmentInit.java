package com.welearn.entity.vo.request.equipment;

import java.lang.Integer;
import java.lang.String;
import java.util.Date;

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
public class ProductEquipmentInit {
    @NotNull private String arg0;
    @NotNull private Integer arg1;
    @NotNull private String arg2;
    @NotNull private String arg3;
    @NotNull private Date arg4;
    @NotNull private String arg5;
    @NotNull private String arg6;
    @NotNull private String arg7;
}