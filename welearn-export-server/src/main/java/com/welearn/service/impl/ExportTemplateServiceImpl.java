package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.export.ExportTaskStatusConst;
import com.welearn.entity.po.export.ExportTask;
import com.welearn.entity.po.export.ExportTemplate;
import com.welearn.entity.qo.export.ExportTemplateQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.export.ExportTaskConsumerHelper;
import com.welearn.mapper.ExportTemplateMapper;
import com.welearn.service.ExportTemplateService;
import com.welearn.xdoc.XDocService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description : ExportTemplateService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ExportTemplateServiceImpl extends BaseServiceImpl<ExportTemplate,ExportTemplateQueryCondition,ExportTemplateMapper>
        implements ExportTemplateService{
    
    @Autowired
    private ExportTemplateMapper exportTemplateMapper;

    @Autowired
    private ExportTaskConsumerHelper consumerHelper;

    @Autowired
    private XDocService xDocService;

    @Override
    ExportTemplateMapper getMapper() {
        return exportTemplateMapper;
    }

    @Override
    @Transactional
    public ExportTemplate create(ExportTemplate exportTemplate) {
        if (StringUtils.isBlank(exportTemplate.getFile()))
            throw new BusinessVerifyFailedException("file 不能为空");
        String fileUrl = consumerHelper.getFirstFileUrl(exportTemplate.getFile());
        if (StringUtils.isBlank(fileUrl))
            throw new BusinessVerifyFailedException("无法获取 file 的访问url");
        try {
            String responseJson = xDocService.params(fileUrl);
            JSONObject response = JSON.parseObject(responseJson);
            if (response.getBoolean("success")) {
                JSONArray values = response.getJSONObject("result").getJSONArray("value");
                List<Map<String, String>> params = new ArrayList<>();
                for (int i = 0; i < values.size(); i++) {
                    JSONObject valueObj = values.getJSONObject(i);
                    if ("_format".equals(valueObj.getString("NAME")))
                        continue;
                    Map<String, String> value = new HashMap<>();
                    value.put("key",valueObj.getString("NAME"));
                    value.put("type",valueObj.getString("INPUT_TYPE"));
                    value.put("option",valueObj.getString("OPTION"));
                    value.put("description",valueObj.getString("COMMENT"));
                    value.put("name",valueObj.getString("CAPTION"));
                    params.add(value);
                }
                exportTemplate.setArgsFormatJson(JSON.toJSONString(params));
            } else {
                throw new BusinessVerifyFailedException("无法获取模板文件的参数格式: {}", responseJson);
            }
        } catch (IOException e) {
            throw new BusinessVerifyFailedException(e, "读取模板文件参数格式时异常");
        }
        return super.create(exportTemplate);
    }
}
