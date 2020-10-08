package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.procurement.ProcurementDetailQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.ProcurementDetailService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/procurementDetail")
public class ProcurementDetailController extends BaseController<ProcurementDetail, ProcurementDetailQueryCondition, ProcurementDetailService>{
}