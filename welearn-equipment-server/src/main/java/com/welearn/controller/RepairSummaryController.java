package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.RepairSummary;
import com.welearn.entity.qo.equipment.RepairSummaryQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RepairSummaryService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairSummary")
@Api(value = "repairSummary 接口")
public class RepairSummaryController extends BaseController<RepairSummary, RepairSummaryQueryCondition, RepairSummaryService>{
}