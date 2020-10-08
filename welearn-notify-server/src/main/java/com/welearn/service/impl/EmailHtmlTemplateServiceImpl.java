package com.welearn.service.impl;

import com.welearn.entity.po.notify.EmailHtmlTemplate;
import com.welearn.entity.qo.notify.EmailHtmlTemplateQueryCondition;
import com.welearn.mapper.EmailHtmlTemplateMapper;
import com.welearn.service.EmailHtmlTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : EmailHtmlTemplateService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EmailHtmlTemplateServiceImpl extends BaseServiceImpl<EmailHtmlTemplate,EmailHtmlTemplateQueryCondition,EmailHtmlTemplateMapper>
        implements EmailHtmlTemplateService{
    
    @Autowired
    private EmailHtmlTemplateMapper emailHtmlTemplateMapper;
    
    @Override
    EmailHtmlTemplateMapper getMapper() {
        return emailHtmlTemplateMapper;
    }

}
