package com.welearn.entity.vo.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2019/5/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo implements Serializable {
    private String id;
    private String departmentId;
    private String name;
    private Integer sex;
    private String telephone;
    private String email;
    private String avatar;
}
