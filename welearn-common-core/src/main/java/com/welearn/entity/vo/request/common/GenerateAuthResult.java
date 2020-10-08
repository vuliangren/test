package com.welearn.entity.vo.request.common;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.dto.authorization.Token;

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
public class GenerateAuthResult {
    @NotNull private User user;
    @NotNull private Integer clientType;
    @NotNull private Token accessToken;
}