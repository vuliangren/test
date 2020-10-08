package com.welearn.service;

import com.welearn.entity.po.common.ReUserPosition;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.ReUserPositionQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : Service Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReUserPositionService extends BaseService<ReUserPosition, ReUserPositionQueryCondition>{


}