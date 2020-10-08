package com.welearn.service.impl;

import com.welearn.dictionary.notify.SmsSendTemplateStatusConst;
import com.welearn.entity.po.notify.SmsSendTemplate;
import com.welearn.entity.qo.notify.SmsSendTemplateQueryCondition;
import com.welearn.mapper.SmsSendTemplateMapper;
import com.welearn.service.SmsSendTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description : SmsSendTemplateService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SmsSendTemplateServiceImpl extends BaseServiceImpl<SmsSendTemplate,SmsSendTemplateQueryCondition,SmsSendTemplateMapper>
        implements SmsSendTemplateService{
    
    @Autowired
    private SmsSendTemplateMapper smsSendTemplateMapper;
    
    @Override
    SmsSendTemplateMapper getMapper() {
        return smsSendTemplateMapper;
    }

    /**
     * 根据系统内的模板标号查询模板内容
     *
     * @param code 系统内模板编号
     * @return 短信发送模板
     */
    @Override
    public SmsSendTemplate selectByCode(String code) {
        SmsSendTemplateQueryCondition condition = new SmsSendTemplateQueryCondition();
        condition.setCode(code);
        condition.setIsEnable(true);
        condition.setStatus(SmsSendTemplateStatusConst.SUCCESS.ordinal());
        List<SmsSendTemplate> templates = this.search(condition);
        if (Objects.isNull(templates) || templates.isEmpty())
            return null;
        else
            return templates.get(0);
    }
}
