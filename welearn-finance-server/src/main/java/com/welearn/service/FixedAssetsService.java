package com.welearn.service;

import com.welearn.entity.po.finance.FixedAssets;
import com.welearn.entity.qo.finance.FixedAssetsQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : FixedAssetsService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface FixedAssetsService extends BaseService<FixedAssets, FixedAssetsQueryCondition>{

    /**
     * 设备类型固定资产统计
     * @param companyId 公司id
     * @return 统计用数据
     */
    List<FixedAssets> equipmentValueStatistic(@NotBlank String companyId);
}