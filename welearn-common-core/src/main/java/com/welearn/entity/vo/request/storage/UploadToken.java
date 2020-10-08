package com.welearn.entity.vo.request.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2019/5/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadToken implements Serializable {
    private Boolean isPublic;
    private Boolean isSystemUser;
    private String userId;
    // 带点 如 .docx
    private String suffix;
}
