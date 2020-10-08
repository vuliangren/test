package com.welearn.service.impl;

import com.welearn.entity.po.apply.Contract;
import com.welearn.entity.qo.apply.ContractQueryCondition;
import com.welearn.mapper.ContractMapper;
import com.welearn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ContractService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ContractServiceImpl extends BaseServiceImpl<Contract,ContractQueryCondition,ContractMapper>
        implements ContractService{
    
    @Autowired
    private ContractMapper contractMapper;
    
    @Override
    ContractMapper getMapper() {
        return contractMapper;
    }


    @Override
    public void update(Contract entity){
        contractMapper.updateByPK(entity);
    }
}
