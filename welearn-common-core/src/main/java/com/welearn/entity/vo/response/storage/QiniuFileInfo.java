package com.welearn.entity.vo.response.storage;

import com.welearn.entity.po.storage.UploadRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2018/11/19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiniuFileInfo implements Serializable {
    private String downloadUrl;
    private UploadRecord fileInfo;
}
