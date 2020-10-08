package com.welearn.service;

import com.welearn.entity.po.common.WechatAppUser;
import com.welearn.entity.qo.common.WechatAppUserQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : WechatAppUserService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface WechatAppUserService extends BaseService<WechatAppUser, WechatAppUserQueryCondition>{

}