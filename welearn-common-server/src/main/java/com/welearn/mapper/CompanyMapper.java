package com.welearn.mapper;

import com.welearn.entity.po.common.Company;
import com.welearn.entity.qo.common.CompanyQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * Company Mapper Interface : ryme_common : company
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/2 19:02:50
 * @see com.welearn.entity.po.common.Company
 */
@Mapper 
public interface CompanyMapper extends BaseMapper<Company, CompanyQueryCondition> {
    
}