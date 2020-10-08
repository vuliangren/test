package com.welearn.entity.vo.request.authorization;

import java.lang.Integer;
import java.lang.String;

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
public class UserWeChatAppProcess {
    @NotNull private String telephone;
    @NotNull private String authCode;
    @NotNull private String openId;
    @NotNull private Integer type;
}