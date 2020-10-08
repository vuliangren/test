package com.welearn.service;

import com.welearn.entity.po.export.ExportTemplate;
import com.welearn.entity.qo.export.ExportTemplateQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ExportTemplateService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ExportTemplateService extends BaseService<ExportTemplate, ExportTemplateQueryCondition>{

}