package com.welearn.entity.qo.equipment;

import com.welearn.entity.po.equipment.MaintenanceTask;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : MaintenanceTask Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:39
 * @see com.welearn.entity.po.equipment.MaintenanceTask
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MaintenanceTaskQueryCondition extends MaintenanceTask {
    private Date startAtFrom;
    private Date startAtEnd;
}
