package com.welearn.cache.setter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/13.
 */
@Slf4j
@Component
public class EquipmentCacheSetter {

    @CachePut(value = "equipmentsStatistics", key = "#companyId")
    public Map<String, Object> setEquipmentsStatistics(String companyId, Map<String, Object> content){
        return content;
    }

    @CachePut(value = "equipmentStatistics", key = "#equipmentId")
    public Map<String, Object> setEquipmentStatistics(String equipmentId, Map<String, Object> content){
        return content;
    }

    @CachePut(value = "engineerStatistics", key = "#engineerId")
    public Map<String, Object> setEngineerStatistics(String engineerId, Map<String, Object> content){
        return content;
    }
}
