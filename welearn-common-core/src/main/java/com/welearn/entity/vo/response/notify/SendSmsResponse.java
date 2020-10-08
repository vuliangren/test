package com.welearn.entity.vo.response.notify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description : 阿里云 SendSms 接口发送短信响应
 * Created by Setsuna Jin on 2019/3/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendSmsResponse implements Serializable {
    @JsonProperty("Message")
    private String message;

    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("BizId")
    private String bizId;

    @JsonProperty("Code")
    private String code;
}
