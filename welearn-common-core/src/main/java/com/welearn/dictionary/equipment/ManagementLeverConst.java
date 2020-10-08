package com.welearn.dictionary.equipment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description : 医疗设备管理级别
 * Created by Setsuna Jin on 2018/11/12.
 */
@Getter
@AllArgsConstructor
public enum ManagementLeverConst {
    // 0-Ⅰ 1-Ⅱ 2-Ⅲ
    FIRST("Ⅰ"), SECOND("Ⅱ"), THIRD("Ⅲ")
    ;
    private String leverName;
}
