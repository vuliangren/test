package com.welearn.entity.dto.export;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonDataExportSheet implements Serializable {
    private String title;
    private List<String> header;
    private List<List<String>> data;
}
