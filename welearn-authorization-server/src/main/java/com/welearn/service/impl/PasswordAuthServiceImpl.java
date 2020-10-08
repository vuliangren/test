package com.welearn.service.impl;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.dictionary.common.UserTypeConst;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.vo.request.common.PasswordLogin;
import com.welearn.entity.po.common.User;
import com.welearn.error.exception.UserAuthCheckFailedException;
import com.welearn.feign.common.UserFeignClient;
import com.welearn.service.PasswordAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/20.
 */
@Slf4j
@Service
public class PasswordAuthServiceImpl extends AbstractAuthServiceImpl implements PasswordAuthService {

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 登录系统
     *
     * @param passwordLogin 邮箱密码
     * @return TokenPackage
     */
    @Override
    public TokenPackage login(PasswordLogin passwordLogin, ClientTypeConst clientTypeConst) {
        User user = userFeignClient.passwordLogin(passwordLogin).getData();
        if (Objects.isNull(user))
            throw new UserAuthCheckFailedException("user password login check failed");
        return this.sign(user, UserTypeConst.ANY, clientTypeConst, null, null);
    }
}
