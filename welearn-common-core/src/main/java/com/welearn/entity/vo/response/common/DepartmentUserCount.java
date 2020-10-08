package com.welearn.entity.vo.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2019/5/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentUserCount implements Serializable {
    private String id;
    private String name;
    private Integer count;
}
