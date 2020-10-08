package com.welearn.mapper;

import com.welearn.entity.po.apply.Certificate;
import com.welearn.entity.qo.apply.CertificateQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * Certificate Mapper Interface : ryme_apply : certificate
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/20 11:21:39
 * @see Certificate
 */
@Mapper 
public interface CertificateMapper extends BaseMapper<Certificate, CertificateQueryCondition> {
    
}