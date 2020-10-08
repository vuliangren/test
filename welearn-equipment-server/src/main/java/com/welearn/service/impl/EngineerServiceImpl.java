package com.welearn.service.impl;

import com.welearn.dictionary.common.SystemRoleConst;
import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.qo.equipment.EngineerQueryCondition;
import com.welearn.entity.vo.request.common.BindUserWithCode;
import com.welearn.entity.vo.request.common.UnbindUserWithCode;
import com.welearn.feign.common.RoleFeignClient;
import com.welearn.mapper.EngineerMapper;
import com.welearn.service.EngineerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Description : EngineerService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EngineerServiceImpl extends BaseServiceImpl<Engineer,EngineerQueryCondition,EngineerMapper>
        implements EngineerService{
    
    @Autowired
    private EngineerMapper engineerMapper;

    @Autowired
    private RoleFeignClient roleFeignClient;

    @Override
    EngineerMapper getMapper() {
        return engineerMapper;
    }


    @Override
    public Engineer create(Engineer engineer){
        // 自动赋予 工程师 角色
        if (StringUtils.isNotBlank(engineer.getUserId()))
            roleFeignClient.bindUserWithCode(new BindUserWithCode(engineer.getUserId(), SystemRoleConst.PLATFORM_ENGINEER.getCode()));
        return super.create(engineer);
    }

    @Override
    public void disable(Engineer engineer) {
        // 自动撤回 工程师 角色
        if (StringUtils.isNotBlank(engineer.getUserId()))
            roleFeignClient.unbindUserWithCode(new UnbindUserWithCode(engineer.getUserId(), SystemRoleConst.PLATFORM_ENGINEER.getCode()));
        super.disable(engineer.getId());
    }

    @Override
    public void disable(String engineerId) {
        Engineer engineer = this.select(engineerId);
        if (Objects.nonNull(engineer) && engineer.getIsEnable()) {
            this.disable(engineer);
        }
    }
}
