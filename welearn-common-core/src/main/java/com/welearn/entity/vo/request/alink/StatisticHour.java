package com.welearn.entity.vo.request.alink;

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
public class StatisticHour {
    private String iotId;
    private Integer type;
    private Date reportedAtStart;
    private Date reportedAtEnd;
}