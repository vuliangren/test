package com.welearn.entity.vo.request.notify;

import com.welearn.dictionary.notify.SmsSignatureConst;
import java.lang.String;
import java.util.Map;

import com.welearn.entity.po.common.User;
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
public class SendForUser {
    @NotNull private User user;
    @NotNull private SmsSignatureConst signature;
    @NotNull private String code;
    private Map<String, String> params;
    private String noticeId;
}