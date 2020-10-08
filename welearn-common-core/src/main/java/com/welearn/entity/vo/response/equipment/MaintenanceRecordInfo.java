package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.MaintenanceRecord;
import com.welearn.entity.po.equipment.MaintenanceTask;
import com.welearn.entity.po.equipment.ReTaskAssignmentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2019/1/3.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MaintenanceRecordInfo extends MaintenanceRecord {
    private List<MaintenanceRecordDetail> maintenanceRecordDetails;
    private Equipment equipment;

    private TaskAssignmentInfo taskAssignmentInfo;
    private List<ReTaskAssignmentMethod> taskAssignmentDetails;
}
