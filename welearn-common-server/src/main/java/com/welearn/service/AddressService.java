package com.welearn.service;

import com.welearn.entity.po.common.Address;
import com.welearn.entity.qo.common.AddressQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : AddressService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface AddressService extends BaseService<Address, AddressQueryCondition>{

}