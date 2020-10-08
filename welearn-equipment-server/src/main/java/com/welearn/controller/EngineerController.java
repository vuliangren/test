package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.qo.equipment.EngineerQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/engineer")
public class EngineerController extends BaseController<Engineer, EngineerQueryCondition, EngineerService>{

}