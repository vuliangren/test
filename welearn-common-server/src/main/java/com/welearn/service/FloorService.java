package com.welearn.service;

import com.welearn.entity.po.common.Floor;
import com.welearn.entity.qo.common.FloorQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : Service Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface FloorService extends BaseService<Floor, FloorQueryCondition>{

}