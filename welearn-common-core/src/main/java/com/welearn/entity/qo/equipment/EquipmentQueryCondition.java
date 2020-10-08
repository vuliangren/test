package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.Equipment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : Equipment Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/10 10:22:57
 * @see com.welearn.entity.po.equipment.Equipment
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EquipmentQueryCondition extends Equipment {
    private String companyId;
    // 设备权限 相关参数
    private Integer permissionCode;
    private Integer type;
    private String typeId;
    // 多设备状态查询
    private List<Integer> equipmentStatusList;
    // 多标签查询 -> equipmentIds -> 查询
    private List<String> tagIds;
    // id列表范围内查询
    private List<String> equipmentIds;
}
