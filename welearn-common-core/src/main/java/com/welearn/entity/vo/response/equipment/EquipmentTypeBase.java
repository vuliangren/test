package com.welearn.entity.vo.response.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2018/11/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentTypeBase implements Serializable {
    private String id;
    private String name;
    private String remark;
}
