package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.po.equipment.RepairDispatchOutside;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description :
 * Created by Setsuna Jin on 2019/1/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RepairDispatchOutsideDetailInfo extends Engineer {
    private String repairDispatchOutsideDetailId;
}
