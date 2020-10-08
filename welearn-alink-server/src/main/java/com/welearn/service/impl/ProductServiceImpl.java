package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;
import com.aliyuncs.utils.ParameterHelper;
import com.welearn.dictionary.alink.DeviceStatusConst;
import com.welearn.dictionary.alink.NetTypeConst;
import com.welearn.dictionary.alink.PlatformTypeConst;
import com.welearn.dictionary.alink.ProductStatusConst;
import com.welearn.entity.po.alink.Device;
import com.welearn.entity.po.alink.Product;
import com.welearn.entity.qo.alink.ProductQueryCondition;
import com.welearn.entity.vo.response.alink.StatisticConfigJsonStr;
import com.welearn.mapper.ProductMapper;
import com.welearn.service.DeviceService;
import com.welearn.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description : ProductService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product,ProductQueryCondition,ProductMapper>
        implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DefaultAcsClient client;

    @Override
    ProductMapper getMapper() {
        return productMapper;
    }

    private QueryProductListResponse.Data queryProducts(int page, int size) throws ClientException {
        QueryProductListRequest request = new QueryProductListRequest();
        request.setCurrentPage(page);
        request.setPageSize(size);
        return client.getAcsResponse(request).getData();
    }

    private QueryProductResponse.Data queryProductDetail(String productKey) throws ClientException {
        QueryProductRequest request = new QueryProductRequest();
        request.setProductKey(productKey);
        return client.getAcsResponse(request).getData();
    }

    private List<QueryDeviceResponse.DeviceInfo> queryProductDevices(String productKey, int page, int size) throws ClientException {
        QueryDeviceRequest request = new QueryDeviceRequest();
        request.setProductKey(productKey);
        request.setCurrentPage(page);
        request.setPageSize(size);
        return client.getAcsResponse(request).getData();
    }

    private Product updateFromProductDetail(QueryProductResponse.Data productDetail) {
        ProductQueryCondition condition = new ProductQueryCondition();
        condition.setProductKey(productDetail.getProductKey());
        List<Product> search = this.search(condition);
        // 将 productDetail 转换为 product
        Product product = new Product();
        product.setCategoryKey(productDetail.getCategoryKey());
        product.setCategoryName(productDetail.getCategoryName());
        product.setDataFormat(productDetail.getDataFormat());
        product.setDescription(productDetail.getDescription());
        product.setDeviceCount(productDetail.getDeviceCount());
        product.setId2(productDetail.getId2());
        product.setNetType(productDetail.getNetType());
        product.setNodeType(productDetail.getNodeType());
        product.setPlatformType(PlatformTypeConst.getByStr(productDetail.getAliyunCommodityCode()).ordinal());
        product.setProductKey(productDetail.getProductKey());
        product.setProductName(productDetail.getProductName());
        product.setProductStatus(ProductStatusConst.getByStr(productDetail.getProductStatus()).ordinal());
        product.setPrpoductSecret(productDetail.getProductSecret());
        if (Objects.nonNull(search) && !search.isEmpty()) {
            product.setId(search.get(0).getId());
            productMapper.updateByPKSelective(product);
            return product;
        } else {
            product.setTslJson("{}");
            product.setShowConfigJson("{}");
            product.setStatisitcsConfigJson("{}");
            log.info("新增产品: {}", product.getProductName());
            return this.create(product);
        }
    }

    @Transactional
    @Scheduled(initialDelay = 1000*15, fixedRate = 86400000L)
    public void autoUpdateFromAlink() {
        try {
            log.info("开始执行 - 物联设备 自动更新任务");
            // 定时查询阿里云的产品列表 TODO: 待处理分页问题
            QueryProductListResponse.Data data = queryProducts(1, 200);
            for (QueryProductListResponse.Data.ProductInfo productInfo : data.getList()) {
                // 查询每个产品的详情信息
                QueryProductResponse.Data productDetail = queryProductDetail(productInfo.getProductKey());
                this.updateFromProductDetail(productDetail);
                // 查询每个产品下的设备列表 TODO: 待处理分页问题
                List<QueryDeviceResponse.DeviceInfo> deviceInfos = queryProductDevices(productInfo.getProductKey(), 1, 50);
                for (QueryDeviceResponse.DeviceInfo deviceInfo : deviceInfos) {
                    deviceService.updateDeviceFromAlink(deviceInfo.getIotId());
                }
            }
            log.info("执行完成 - 物联设备 自动更新任务");
        } catch (ClientException e) {
            log.error("自动更新物联设备信息失败, 事务回滚, 所有修改添加均无效", e);
        }
    }

    /**
     * 获取所有设备的统计分析相关配置信息
     *
     * @return 设备iot 与 对应配置JSON字符串
     */
    @Override
    public Map<String, JSONObject> getStatisticConfigJson() {
        List<StatisticConfigJsonStr> statisticConfigJsonStr =  productMapper.getStatisticConfigJsonStr();
        Map<String, JSONObject> configurations = new HashMap<>();
        statisticConfigJsonStr.forEach(jsonStr -> {
            configurations.put(jsonStr.getIotId(), JSON.parseObject(jsonStr.getData()));
        });
        return configurations;
    }


    @Override
    public Product selectByProductKey(String productKey) {
        ProductQueryCondition condition = new ProductQueryCondition();
        condition.setIsEnable(true);
        condition.setProductKey(productKey);
        List<Product> search = this.search(condition);
        if (Objects.isNull(search) || search.isEmpty())
            return null;
        else
            return search.get(0);
    }
}
