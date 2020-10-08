package com.welearn.entity.dto.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentStatisticInfo<T> implements Serializable {
    private String id;
    private String name;
    private T value;
}
