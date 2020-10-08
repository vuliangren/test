package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.EquipmentTypeItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description : 设备类型查询结果
 * Created by Setsuna Jin on 2018/10/19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentTypeInfo implements Serializable {
    // id
    private String v;
    // name
    private String n;
    // indexName
    private String i;
    // firstName
    private String f;
    // secondName
    private String s;
    // description
    private String d;
    // usage
    private String u;
    // management
    private String m;
}
