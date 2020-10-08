package com.welearn.mapper;

import com.welearn.entity.po.common.WechatAppUser;
import com.welearn.entity.qo.common.WechatAppUserQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * WechatAppUser Mapper Interface : ryme_common : wechat_app_user
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/22 15:00:33
 * @see com.welearn.entity.po.common.WechatAppUser
 */
@Mapper 
public interface WechatAppUserMapper extends BaseMapper<WechatAppUser, WechatAppUserQueryCondition> {
    
}