package com.welearn.hystrix;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/6.
 */
//@Component
//@FeignClient(value = "welearn-authorization-server", fallback = AuthorizationHystrix.class)
public interface AuthorizationService {

    //@RequestMapping(value = "/token")
    String test();
}
