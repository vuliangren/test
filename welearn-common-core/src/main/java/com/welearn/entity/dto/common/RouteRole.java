package com.welearn.entity.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/2.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RouteRole implements Serializable {
    private String path;
    private String role;
}
