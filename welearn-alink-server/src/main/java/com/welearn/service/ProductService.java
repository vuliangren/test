package com.welearn.service;

import com.alibaba.fastjson.JSONObject;
import com.welearn.entity.po.alink.Product;
import com.welearn.entity.qo.alink.ProductQueryCondition;
import com.welearn.entity.vo.response.alink.StatisticConfigJsonStr;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Description : ProductService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ProductService extends BaseService<Product, ProductQueryCondition>{
    /**
     * 获取所有设备的统计分析相关配置信息
     * @return 设备iot 与 对应配置JSON对象
     */
    Map<String, JSONObject> getStatisticConfigJson();

    Product selectByProductKey(String productKey);
}