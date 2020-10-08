package com.welearn.dictionary.common;

/**
 * Description : 公司类型
 * Created by Setsuna Jin on 2018/9/14.
 */
public enum CompanyTypeConst {
    HOSPITAL, SUPPLIER, PLATFORM;

    public static CompanyTypeConst get(int index){
        return values()[index];
    }
}
