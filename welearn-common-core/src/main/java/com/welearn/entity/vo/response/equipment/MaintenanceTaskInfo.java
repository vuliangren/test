package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.MaintenanceMethod;
import com.welearn.entity.po.equipment.MaintenanceTask;
import com.welearn.entity.po.equipment.ReMaintenanceTaskUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2019/1/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MaintenanceTaskInfo implements Serializable {
    private MaintenanceTask maintenanceTask;
    private List<ReMaintenanceTaskUser> users;

}
