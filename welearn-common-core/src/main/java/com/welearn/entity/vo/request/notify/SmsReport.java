package com.welearn.entity.vo.request.notify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description : 阿里云短信状态报告
 * Created by Setsuna Jin on 2019/3/18.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SmsReport implements Serializable {

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("send_time")
    private Date sendTime;

    @JsonProperty("report_time")
    private Date reportTime;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("err_code")
    private String errCode;

    @JsonProperty("err_msg")
    private String errMsg;

    @JsonProperty("sms_size")
    private Integer smsSize;

    @JsonProperty("biz_id")
    private String bizId;

    @JsonProperty("out_id")
    private String outId;
}
