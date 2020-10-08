package com.welearn.service;

import com.welearn.entity.po.common.Building;
import com.welearn.entity.qo.common.BuildingQueryCondition;
import com.welearn.entity.vo.response.common.BuildingInfo;
import com.welearn.entity.vo.response.common.Option;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : Service Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface BuildingService extends BaseService<Building, BuildingQueryCondition>{

    /**
     * 分页查询建筑信息
     * @param companyId 公司id
     * @return 建筑信息列表
     */
    List<BuildingInfo> searchInfo(String companyId);

    /**
     * 查询建筑信息 用于 Option 选择使用
     * @param companyId 公司id
     * @return 建筑信息列表
     */
    List<Option<String, String>> searchOptionInfo(String companyId);
}