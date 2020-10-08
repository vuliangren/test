package com.welearn.service.impl;

import com.welearn.controller.ProcurementController;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.procurement.ProcurementDetailQueryCondition;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.mapper.ProcurementDetailMapper;
import com.welearn.service.ProcurementDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ProcurementDetailService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ProcurementDetailServiceImpl extends BaseServiceImpl<ProcurementDetail,ProcurementDetailQueryCondition,ProcurementDetailMapper>
        implements ProcurementDetailService{
    
    @Autowired
    private ProcurementDetailMapper procurementDetailMapper;
    
    @Override
    ProcurementDetailMapper getMapper() {
        return procurementDetailMapper;
    }

}
