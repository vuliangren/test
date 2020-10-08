package com.welearn.dictionary.notify;

import com.welearn.error.exception.BusinessVerifyFailedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description : 短信模板编码常量
 * Created by Setsuna Jin on 2019/3/18.
 */
@AllArgsConstructor
public enum SmsTemplateConst {
    // 账户锁定
    LOCK_ACCOUNT("LOCK_ACCOUNT", new String[]{"count"}),
    // 密码重置
    RESET_PASSWORD("RESET_PASSWORD", new String[]{"password"}),
    // 忘记密码
    FORGET_PASSWORD("FORGET_PASSWORD", new String[]{"code"}),
    // 员工入职
    EMPLOYEE_ENTRY("EMPLOYEE_ENTRY", new String[]{"departmentAndUserName", "password"}),
    // 用户注册
    USER_REGISTER("USER_REGISTER", new String[]{"code"}),
    // 注册成功
    REGISTER_SUCCESS("REGISTER_SUCCESS", new String[]{"departmentAndUserName", "password"}),
    // 设备下线
    DEVICE_OFFLINE("DEVICE_OFFLINE", new String[]{"hospitalName","equipmentName"}),
    ;
    @Getter
    private String code;
    @Getter
    private String[] paramsName;

    /**
     * 获取模板参数内容
     * @param paramsValue 参数值
     * @return Map<String, String>
     */
    public Map<String, String> getParams(String ...paramsValue){
        if (paramsName.length != paramsValue.length )
            throw new BusinessVerifyFailedException("params 参数错误");
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < paramsName.length; i++) {
            result.put(paramsName[i], paramsValue[i]);
        }
        return result;
    }
}
