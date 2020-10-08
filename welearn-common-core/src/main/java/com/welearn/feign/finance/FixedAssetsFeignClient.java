package com.welearn.feign.finance;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.finance.FixedAssets;
import com.welearn.entity.qo.finance.FixedAssetsQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-finance-service / com.welearn.controller.FixedAssetsController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-finance-server", configuration = FeignConfiguration.class)
public interface FixedAssetsFeignClient {

    @RequestMapping(value = "/fixedAssets/equipmentValueStatistic")
    CommonResponse<List<FixedAssets>> equipmentValueStatistic(String string);

    @RequestMapping(value = "/fixedAssets/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/fixedAssets/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/fixedAssets/update")
    CommonResponse update(FixedAssets entity);

    @RequestMapping(value = "/fixedAssets/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/fixedAssets/create")
    CommonResponse<FixedAssets> create(FixedAssets entity);

    @RequestMapping(value = "/fixedAssets/search")
    CommonResponse<List<FixedAssets>> search(FixedAssetsQueryCondition queryCondition);

    @RequestMapping(value = "/fixedAssets/select")
    CommonResponse<FixedAssets> select(String id);
}