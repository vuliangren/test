package com.welearn.mapper;

import com.welearn.entity.po.apply.Contract;
import com.welearn.entity.qo.apply.ContractQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * Contract Mapper Interface : ryme_apply : contract
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/25 16:14:19
 * @see com.welearn.entity.po.apply.Contract
 */
@Mapper 
public interface ContractMapper extends BaseMapper<Contract, ContractQueryCondition> {
    
}