package com.welearn.controller;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.equipment.EquipmentTypeIndex;
import com.welearn.entity.po.equipment.EquipmentTypeItem;
import com.welearn.entity.po.equipment.SparePart;
import com.welearn.entity.qo.equipment.EquipmentTypeIndexQueryCondition;
import com.welearn.entity.vo.request.equipment.Items;
import com.welearn.entity.vo.request.equipment.Second;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.equipment.EquipmentTypeBase;
import com.welearn.entity.vo.response.equipment.EquipmentTypeInfo;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/10/19.
 */

@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentType")
public class EquipmentTypeController {

    @Autowired
    private EquipmentTypeItemService equipmentTypeItemService;

    @Autowired
    private EquipmentTypeIndexService equipmentTypeIndexService;

    @RequestMapping(value = "/select")
    @ApiOperation(value = "数据单个查询", httpMethod = "POST")
    public CommonResponse<EquipmentTypeItem> select(@RequestBody String id) {
        return new CommonResponse<>(equipmentTypeItemService.select(id));
    }

    @RequestMapping(value = "/searchInfoByNameOrId")
    @ApiOperation(value = "通过名称或编号查询设备类型", httpMethod = "POST")
    public CommonResponse<List<EquipmentTypeInfo>> searchInfoByNameOrId(@RequestBody String content) {
        return new CommonResponse<>(equipmentTypeItemService.searchInfoByNameOrId(content));
    }

    @RequestMapping(value = "/index")
    @ApiOperation(value = "设备类型目录索引", httpMethod = "POST")
    public CommonResponse<List<EquipmentTypeIndex>> index(@RequestBody EquipmentTypeIndexQueryCondition condition) {
        return new CommonResponse<>(equipmentTypeIndexService.search(condition));
    }

    @RequestMapping(value = "/first")
    @ApiOperation(value = "设备类型目录一级分类", httpMethod = "POST")
    public CommonResponse<List<EquipmentTypeBase>> first(@RequestBody String indexId) {
        return new CommonResponse<>(equipmentTypeItemService.first(indexId));
    }

    @RequestMapping(value = "/second")
    @ApiOperation(value = "设备类型目录二级分类", httpMethod = "POST")
    public CommonResponse<List<EquipmentTypeBase>> second(@RequestBody Second second) {
        return new CommonResponse<>(equipmentTypeItemService.second(second.getIndexId(), second.getFirstId()));
    }

    @RequestMapping(value = "/items")
    @ApiOperation(value = "设备类型目录子项", httpMethod = "POST")
    public CommonResponse<List<EquipmentTypeBase>> items(@RequestBody Items items) {
        return new CommonResponse<>(equipmentTypeItemService.items(items.getIndexId(), items.getFirstId(), items.getSecondId()));
    }
}