package com.welearn.service.impl;

import com.welearn.entity.po.finance.FixedAssets;
import com.welearn.entity.qo.finance.FixedAssetsQueryCondition;
import com.welearn.feign.common.DepartmentFeignClient;
import com.welearn.mapper.FixedAssetsMapper;
import com.welearn.service.FixedAssetsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : FixedAssetsService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class FixedAssetsServiceImpl extends BaseServiceImpl<FixedAssets,FixedAssetsQueryCondition,FixedAssetsMapper>
        implements FixedAssetsService{

    @Autowired
    private FixedAssetsMapper fixedAssetsMapper;

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    @Override
    FixedAssetsMapper getMapper() {
        return fixedAssetsMapper;
    }

    @Override
    public FixedAssets create(FixedAssets fixedAssets){
        // 处理默认地址
        if (StringUtils.isBlank(fixedAssets.getAddress())){
            String departmentAddress = departmentFeignClient.position(fixedAssets.getDepartmentId()).getData();
            fixedAssets.setAddress(departmentAddress);
        }
        return super.create(fixedAssets);
    }

    /**
     * 设备类型固定资产统计
     *
     * @param companyId 公司id
     * @return 统计用数据
     */
    @Override
    public List<FixedAssets> equipmentValueStatistic(String companyId) {
        return fixedAssetsMapper.equipmentValueStatistic(companyId);
    }
}
