package com.welearn.annotation;

import com.welearn.interceptor.RequestUserHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description : 获取当前请求用户的部门注解
 * Created by Setsuna Jin on 2018/4/4.
 * @see RequestUserHandler
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestDepartment {

}
