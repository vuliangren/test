package com.welearn.service;

import com.welearn.entity.po.common.ReTagItem;
import com.welearn.entity.qo.common.ReTagItemQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ReTagItemService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReTagItemService extends BaseService<ReTagItem, ReTagItemQueryCondition>{

}