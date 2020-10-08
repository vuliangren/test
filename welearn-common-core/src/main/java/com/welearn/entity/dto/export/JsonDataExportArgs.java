package com.welearn.entity.dto.export;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonDataExportArgs implements Serializable {
    private Map<String, JsonDataExportSheet> sheets;
}
