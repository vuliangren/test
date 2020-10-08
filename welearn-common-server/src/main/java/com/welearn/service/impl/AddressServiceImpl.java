package com.welearn.service.impl;

import com.welearn.entity.po.common.Address;
import com.welearn.entity.qo.common.AddressQueryCondition;
import com.welearn.mapper.AddressMapper;
import com.welearn.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : AddressService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address,AddressQueryCondition,AddressMapper>
        implements AddressService{
    
    @Autowired
    private AddressMapper addressMapper;
    
    @Override
    AddressMapper getMapper() {
        return addressMapper;
    }

}
