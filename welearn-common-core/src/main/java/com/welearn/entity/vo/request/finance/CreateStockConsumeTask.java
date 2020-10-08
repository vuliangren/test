package com.welearn.entity.vo.request.finance;

import java.lang.Integer;
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
public class CreateStockConsumeTask {
    @NotNull private String packageId;
    @NotNull private Integer count;
    @NotNull private String description;
    @NotNull private String userId;
}