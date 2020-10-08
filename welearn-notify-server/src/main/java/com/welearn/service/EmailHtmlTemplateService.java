package com.welearn.service;

import com.welearn.entity.po.notify.EmailHtmlTemplate;
import com.welearn.entity.qo.notify.EmailHtmlTemplateQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : EmailHtmlTemplateService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EmailHtmlTemplateService extends BaseService<EmailHtmlTemplate, EmailHtmlTemplateQueryCondition>{

}