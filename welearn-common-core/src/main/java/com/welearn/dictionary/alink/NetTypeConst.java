package com.welearn.dictionary.alink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/1.
 */
@Getter
@AllArgsConstructor
public enum NetTypeConst {
    WIFI(3), CELLULAR(6), ETHERNET(7), OTHER(8);

    private Integer number;

    public static NetTypeConst getByInt(Integer type){
        return Arrays.stream(NetTypeConst.values()).filter(t -> t.number.equals(type)).findFirst().get();
    }
}
