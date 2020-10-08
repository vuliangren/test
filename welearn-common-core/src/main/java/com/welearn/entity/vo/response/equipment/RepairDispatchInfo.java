package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.RepairDispatchInside;
import com.welearn.entity.po.equipment.RepairRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : 报修派工详情
 * Created by Setsuna Jin on 2019/1/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RepairDispatchInfo extends RepairRequest {
    // RepairDispatchInside 相关信息
    private String dispatchId;
    private String engineerId;
    private String engineerName;
    private Integer dispatchStatus;
    private Date receivedAt;
    private String workTimeJson;
    private Boolean isReDispatch;
    private String reDispatchDescription;
    private String reDispatchReason;
}
