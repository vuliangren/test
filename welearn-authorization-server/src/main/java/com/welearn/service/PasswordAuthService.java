package com.welearn.service;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.vo.request.common.PasswordLogin;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Description : 密码方式授权认证
 * Created by Setsuna Jin on 2018/4/20.
 */
@Validated
public interface PasswordAuthService extends TokenAuthService {

    /**
     * 登录系统
     * @param passwordLogin 邮箱密码
     * @param clientTypeConst 客户端类型
     * @return TokenPackage
     */
    TokenPackage login(@NotNull @Valid PasswordLogin passwordLogin, @NotNull ClientTypeConst clientTypeConst);
}
