package com.welearn.service;

import com.welearn.entity.po.apply.Contract;
import com.welearn.entity.qo.apply.ContractQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ContractService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ContractService extends BaseService<Contract, ContractQueryCondition>{

}