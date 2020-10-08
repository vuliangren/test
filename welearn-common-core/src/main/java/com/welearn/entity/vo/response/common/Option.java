package com.welearn.entity.vo.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Description : 字典的选项
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Option<V, L> implements Serializable {
    @NotNull private V value;
    @NotNull private L label;
    private List<Option<V,L>> children;
}