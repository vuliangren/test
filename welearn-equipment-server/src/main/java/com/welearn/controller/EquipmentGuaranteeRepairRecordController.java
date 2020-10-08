package com.welearn.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.EquipmentGuaranteeRepairRecord;
import com.welearn.entity.qo.equipment.EquipmentGuaranteeRepairRecordQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.EquipmentGuaranteeRepairRecordService;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentGuaranteeRepairRecord")
@Api(value = "equipmentGuaranteeRepairRecord 接口")
public class EquipmentGuaranteeRepairRecordController extends BaseController<EquipmentGuaranteeRepairRecord, EquipmentGuaranteeRepairRecordQueryCondition, EquipmentGuaranteeRepairRecordService>{

    @RequestMapping(value = "/batchCreate")
    @ApiOperation(value = "批量创建设备保修记录", httpMethod = "POST")
    public CommonResponse<List<EquipmentGuaranteeRepairRecord>> batchCreate(@RequestBody List<EquipmentGuaranteeRepairRecord> list)  {
        List<EquipmentGuaranteeRepairRecord> result = service.batchCreate(list);
        return new CommonResponse<>(result);
    }

}