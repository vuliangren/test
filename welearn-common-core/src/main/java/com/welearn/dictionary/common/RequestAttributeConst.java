package com.welearn.dictionary.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/23.
 */
@AllArgsConstructor
public enum RequestAttributeConst {

    // 缓存分页请求的分页结果
    CACHE_PAGE_INFO("_CachePageInfo"),
    // 定位请求开始处理的时间
    PROCESS_START_TIME("_ProcessStartTime");
    @Getter
    private String name;
}
