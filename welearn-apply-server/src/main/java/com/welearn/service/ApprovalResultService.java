package com.welearn.service;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.apply.ApprovalResult;
import com.welearn.entity.qo.apply.ApprovalResultQueryCondition;
import com.welearn.generator.ControllerGenerator;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ApprovalResultService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ApprovalResultService extends BaseService<ApprovalResult, ApprovalResultQueryCondition>{

}