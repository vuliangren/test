package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.SparePartInBillService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.qo.equipment.SparePartInItemQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.SparePartInItemService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/sparePartInItem")
@Api(value = "sparePartInItem 接口")
public class SparePartInItemController extends BaseController<SparePartInItem, SparePartInItemQueryCondition, SparePartInItemService>{

}