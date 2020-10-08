package com.welearn.cache.evict;

import com.welearn.entity.vo.response.common.LocationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/25.
 */
@Slf4j
@Component
public class CommonCacheEvictor {
    /**
     * 清除 common 服务中对 Antd 前端路由权限信息的 缓存
     */
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    public void deleteRouteInfos(){
        log.debug("deleteRouteInfos");
    }

    /**
     * 清除 common 服务中国家信息的 缓存
     */
    @CacheEvict(value = "country", allEntries=true)
    public void deleteCountry(){
        log.debug("deleteCountry");
    }

    /**
     * 删除位置数据 缓存
     * @param locationId 建筑id / 楼层id / 房间id
     */
    @CacheEvict(value = "location", key = "'location:'+#locationId")
    public void deleteLocation(String locationId) {
        log.debug("deleteLocation");
    }
}
