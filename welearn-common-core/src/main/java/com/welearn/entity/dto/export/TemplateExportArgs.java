package com.welearn.entity.dto.export;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateExportArgs implements Serializable {
    private String templateId;
    private Map<String, Object> params;
    private Boolean isPdfExport;
}
