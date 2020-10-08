package com.welearn.service.impl;

import com.welearn.entity.po.common.WechatAppUser;
import com.welearn.entity.qo.common.WechatAppUserQueryCondition;
import com.welearn.mapper.WechatAppUserMapper;
import com.welearn.service.WechatAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : WechatAppUserService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class WechatAppUserServiceImpl extends BaseServiceImpl<WechatAppUser,WechatAppUserQueryCondition,WechatAppUserMapper>
        implements WechatAppUserService{
    
    @Autowired
    private WechatAppUserMapper wechatAppUserMapper;
    
    @Override
    WechatAppUserMapper getMapper() {
        return wechatAppUserMapper;
    }

}
