package com.welearn.entity.vo.request.alink;

import java.lang.Boolean;
import java.lang.Integer;

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
public class EventCount {
    private Integer typeGreaterThan;
    private Boolean shouldHandle;
    private Integer handleStatus;
    private String iotId;
}