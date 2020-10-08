package com.welearn.cache.evict;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2019/1/10.
 */
@Slf4j
@Component
public class EquipmentCacheEvictor {
    /**
     * 清除 equipment 服务中的 设备位置查询 缓存
     * @param equipmentId 设备id
     */
    @CacheEvict(value = "equipmentLocation", key = "'equipmentLocation:'+#equipmentId")
    public void deleteEquipmentLocation(String equipmentId){
        log.debug("deleteEquipmentLocation:{}", equipmentId);
    }

    @CacheEvict(value = "equipmentsStatistics", key = "#companyId")
    public void deleteEquipmentsStatistics(String companyId){
        log.debug("deleteEquipmentStatistics:{}", companyId);
    }

    @CacheEvict(value = "equipmentStatistics", key = "#equipmentId")
    public void deleteEquipmentStatistics(String equipmentId){
        log.debug("deleteEquipmentStatistics:{}", equipmentId);
    }

    @CacheEvict(value = "engineerStatistics", key = "#engineerId")
    public void deleteEngineerStatistics(String engineerId){
        log.debug("deleteEngineerStatistics:{}", engineerId);
    }
}
