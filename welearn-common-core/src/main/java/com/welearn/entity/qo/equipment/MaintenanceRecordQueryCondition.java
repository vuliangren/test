package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.MaintenanceRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : MaintenanceRecord Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:39
 * @see com.welearn.entity.po.equipment.MaintenanceRecord
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MaintenanceRecordQueryCondition extends MaintenanceRecord {
    // 用于关联工程师id 进行查询
    private String userId;
    private Date createdAtLessThan;
    private Date createdAtGreaterThan;
}
