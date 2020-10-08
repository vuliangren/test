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
public class CreateStockTransferTask {
    @NotNull private Integer contentType;
    @NotNull private String itemTypeId;
    @NotNull private String itemSpecification;
    @NotNull private Integer taskType;
    @NotNull private String taskDescription;
    @NotNull private Integer taskCount;
    @NotNull private String taskRemark;
    @NotNull private String userId;
}