package com.welearn.entity.dto.notify;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/20.
 */
@Data
@EqualsAndHashCode
public class WebSocketClient {
    private String id;
    private String companyId;
    private String departmentId;
    private Integer type;
    private Integer detail;
}
