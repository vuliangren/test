package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.common.ReTagItem;
import com.welearn.generator.ControllerGenerator;
import com.welearn.service.ReTagItemService;
import com.welearn.service.TagService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.Room;
import com.welearn.entity.qo.common.RoomQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RoomService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/room")
@Api(value = "room 接口")
public class RoomController extends BaseController<Room, RoomQueryCondition, RoomService>{

}