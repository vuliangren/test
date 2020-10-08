package com.welearn.entity.vo.response.apply;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description :
 * Created by Setsuna Jin on 2018/10/24.
 */
@Data
@AllArgsConstructor
public class CertificateType {
    private String name;
    private String code;
    private String description;
}
