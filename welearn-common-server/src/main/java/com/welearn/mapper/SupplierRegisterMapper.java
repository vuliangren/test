package com.welearn.mapper;

import com.welearn.entity.po.common.SupplierRegister;
import com.welearn.entity.qo.common.SupplierRegisterQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SupplierRegister Mapper Interface : ryme_common : supplier_register
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/23 17:15:23
 * @see com.welearn.entity.po.common.SupplierRegister
 */
@Mapper 
public interface SupplierRegisterMapper extends BaseMapper<SupplierRegister, SupplierRegisterQueryCondition> {
    
}