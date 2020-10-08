package com.welearn.hystrix.authorization;

import com.welearn.hystrix.AuthorizationService;

/**
 * Description : 授权认证服务断路器
 * Created by Setsuna Jin on 2018/1/4.
 */
//@Component
public class AuthorizationHystrix implements AuthorizationService {

    public String test() {
        return "service not available";
    }
}
