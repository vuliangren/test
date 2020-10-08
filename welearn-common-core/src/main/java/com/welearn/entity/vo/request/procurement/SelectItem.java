package com.welearn.entity.vo.request.procurement;

import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

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
public class SelectItem {
    @NotNull private String name;
    @NotNull private Integer type;
    @NotNull private Double budget;
    @NotNull private Integer method;
    @NotNull private List<String> detailIds;
    @NotNull private String userId;
}