package com.welearn.cache.getter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/13.
 */
@Slf4j
@Component
public class EquipmentCacheGetter {

    @Cacheable(value = "equipmentsStatistics", key = "#companyId", unless = "#result == null")
    public Map<String, Object> getEquipmentsStatistics(String companyId){
        return null;
    }

    @Cacheable(value = "equipmentStatistics", key = "#equipmentId", unless = "#result == null")
    public Map<String, Object> getEquipmentStatistics(String equipmentId){
        return null;
    }

    @Cacheable(value = "engineerStatistics", key = "#engineerId", unless = "#result == null")
    public Map<String, Object> getEngineerStatistics(String engineerId){
        return null;
    }
}
