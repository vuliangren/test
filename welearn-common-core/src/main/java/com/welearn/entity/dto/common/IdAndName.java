package com.welearn.entity.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2019/5/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdAndName implements Serializable {
    private String id;
    private String name;
}
