package com.welearn.entity.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description : 分页请求
 * Created by Setsuna Jin on 2018/9/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Paginate implements Serializable {
    // 请求的页码
    private Integer page;
    // 请求的数量
    private Integer size;
    // 全部的数量
    private Integer total;

    /**
     * 获取默认分页 10 与 AntDesign 保持一致
     * @return Paginate
     */
    public static Paginate getDefaultPaginate(){
        return new Paginate(1, 10, null);
    }
}
