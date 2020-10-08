package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.po.equipment.ReMaintenanceRecordMethod;
import com.welearn.entity.vo.request.equipment.CreateFromMethodIds;
import com.welearn.entity.vo.request.equipment.UpdateMethodRecord;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.MaintenanceRecord;
import com.welearn.entity.qo.equipment.MaintenanceRecordQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.equipment.MaintenanceRecordInfo;
import com.welearn.service.MaintenanceRecordService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/maintenanceRecord")
public class MaintenanceRecordController extends BaseController<MaintenanceRecord, MaintenanceRecordQueryCondition, MaintenanceRecordService>{

    @RequestMapping(value = "/selectInfo")
    @ApiOperation(value = "selectInfo", httpMethod = "POST")
    public CommonResponse<MaintenanceRecordInfo> selectInfo(@RequestBody String string)  {
        MaintenanceRecordInfo result = service.selectInfo(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/createFromMethodIds")
    @ApiOperation(value = "根据设备的维护方式 创建维护记录", httpMethod = "POST")
    public CommonResponse<MaintenanceRecord> createFromMethodIds(@RequestBody CreateFromMethodIds createFromMethodIds)  {
        MaintenanceRecord result = service.createFromMethodIds(createFromMethodIds.getEquipmentId(),
                createFromMethodIds.getEngineerId(), createFromMethodIds.getRemark(), createFromMethodIds.getMethodIds());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/saveMethodRecord")
    @ApiOperation(value = "保存维护方式的数据记录", httpMethod = "POST")
    public CommonResponse<ReMaintenanceRecordMethod> saveMethodRecord(@RequestBody UpdateMethodRecord updateMethodRecord)  {
        ReMaintenanceRecordMethod result = service.saveMethodRecord(updateMethodRecord.getId(), updateMethodRecord.getDataJson());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/finishMethodRecord")
    @ApiOperation(value = "完成维护方式的数据记录", httpMethod = "POST")
    public CommonResponse<ReMaintenanceRecordMethod> finishMethodRecord(@RequestBody UpdateMethodRecord updateMethodRecord)  {
        ReMaintenanceRecordMethod result = service.finishMethodRecord(updateMethodRecord.getId(), updateMethodRecord.getDataJson());
        return new CommonResponse<>(result);
    }
}