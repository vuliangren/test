package com.welearn.entity.vo.request.notify;

import com.welearn.dictionary.notify.SmsSignatureConst;
import java.lang.String;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import io.swagger.annotations.Api;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SendForPhone {
    @NotNull private String phoneNumber;
    @NotNull private SmsSignatureConst signature;
    @NotNull private String code;
    private Map<String, String> params;
    private String companyId;
    private String departmentId;
    private String userId;
    private String noticeId;
}