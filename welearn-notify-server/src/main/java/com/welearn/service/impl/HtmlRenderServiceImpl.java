package com.welearn.service.impl;

import com.welearn.entity.po.notify.EmailHtmlTemplate;
import com.welearn.entity.qo.notify.EmailHtmlTemplateQueryCondition;
import com.welearn.service.EmailHtmlTemplateService;
import com.welearn.service.HtmlRenderService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2018/11/27.
 */
@Service
public class HtmlRenderServiceImpl implements HtmlRenderService {

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private EmailHtmlTemplateService emailHtmlTemplateService;

    /**
     * 更加模板类型 查找模板 根据参数渲染该模板
     *
     * @param code 模板类型
     * @param args 参数
     * @return 渲染后的 html
     */
    @Override
    public String renderHtml(String code, Object args) {
        EmailHtmlTemplateQueryCondition condition = new EmailHtmlTemplateQueryCondition();
        condition.setCode(code);
        condition.setIsEnable(true);
        List<EmailHtmlTemplate> emailHtmlTemplates = emailHtmlTemplateService.search(condition);
        if (Objects.nonNull(emailHtmlTemplates) && !emailHtmlTemplates.isEmpty()){
            String template = emailHtmlTemplates.get(0).getHtml();
            VelocityContext context = new VelocityContext();
            context.put("data", args);
            context.put("template", template);
            StringWriter writer = new StringWriter();
            velocityEngine.evaluate(context, writer, code, template);
            return writer.toString();
        }
        return null;
    }
}
