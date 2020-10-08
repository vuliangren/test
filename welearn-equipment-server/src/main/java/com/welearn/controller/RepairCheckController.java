package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.RepairCheck;
import com.welearn.entity.qo.equipment.RepairCheckQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RepairCheckService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairCheck")
@Api(value = "repairCheck 接口")
public class RepairCheckController extends BaseController<RepairCheck, RepairCheckQueryCondition, RepairCheckService>{

}