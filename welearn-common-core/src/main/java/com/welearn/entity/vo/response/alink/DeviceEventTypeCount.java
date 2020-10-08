package com.welearn.entity.vo.response.alink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEventTypeCount implements Serializable {
    private String iotId;
    private Integer type;
    private Long count;
}
