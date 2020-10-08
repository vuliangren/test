package com.welearn.service;

import com.welearn.entity.po.notify.SmsSendTemplate;
import com.welearn.entity.qo.notify.SmsSendTemplateQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * Description : SmsSendTemplateService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SmsSendTemplateService extends BaseService<SmsSendTemplate, SmsSendTemplateQueryCondition>{

    /**
     * 根据系统内的模板标号查询模板内容
     * @param code 系统内模板编号
     * @return 短信发送模板
     */
    SmsSendTemplate selectByCode(@NotBlank String code);
}