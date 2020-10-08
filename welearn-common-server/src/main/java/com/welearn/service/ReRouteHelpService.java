package com.welearn.service;

import com.welearn.entity.po.common.ReRouteHelp;
import com.welearn.entity.qo.common.ReRouteHelpQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ReRouteHelpService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReRouteHelpService extends BaseService<ReRouteHelp, ReRouteHelpQueryCondition>{

}