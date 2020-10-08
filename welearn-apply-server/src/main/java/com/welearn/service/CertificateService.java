package com.welearn.service;

import com.welearn.entity.po.apply.Certificate;
import com.welearn.entity.qo.apply.CertificateQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : CertificateService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface CertificateService extends BaseService<Certificate, CertificateQueryCondition>{

}