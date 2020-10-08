package com.welearn.entity.vo.response.alink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticConfigJsonStr implements Serializable {
    private String iotId;
    private String data;
}
