package com.welearn.service;

import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.procurement.ProcurementDetailQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ProcurementDetailService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ProcurementDetailService extends BaseService<ProcurementDetail, ProcurementDetailQueryCondition>{

}