package com.welearn.dictionary.alink;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/1.
 */
public enum ProductStatusConst {
    DEVELOPMENT_STATUS, RELEASE_STATUS;

    public static ProductStatusConst getByStr(String productStatus){
        return ProductStatusConst.valueOf(productStatus.toUpperCase());
    }
}
