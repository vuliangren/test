package com.welearn.entity.vo.response.notify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description : 阿里云 SendReport 接口回调时返回的响应
 * Created by Setsuna Jin on 2019/3/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendReportResponse implements Serializable {
    private Integer code;
    private String msg;
}
