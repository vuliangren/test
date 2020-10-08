package com.welearn.entity.vo.request.common;

import java.lang.Long;
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
public class BatchUpdateCompanyId {
    @NotNull private Long startNumber;
    @NotNull private Long endNumber;
    @NotNull private String companyId;
}