package com.welearn.service;

import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.equipment.SparePart;
import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.qo.equipment.SparePartQueryCondition;
import io.swagger.models.auth.In;
import org.springframework.validation.annotation.Validated;

/**
 * Description : SparePartService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SparePartService extends BaseService<SparePart, SparePartQueryCondition>{

    /**
     * 根据入库项创建批次
     * @param sparePartInItem 入库项
     * @param origin 入库来源
     * @return 配件批次
     */
    SparePart createFromSparePartInItem(SparePartInItem sparePartInItem, Integer origin);

    /**
     * 预定库存
     * @param sparePartId 批次id
     * @param count 数量
     */
    void inTransitOut(String sparePartId, Integer count);

    /**
     * 库存出库
     * @param sparePartId 批次id
     * @param count 数量
     */
    void stockOut(String sparePartId, Integer count);

    /**
     * 计算配件的价格类型
     * @param company 公司
     * @param price 单价
     * @return 价格类型值
     */
    Integer priceTypeCal(Company company, Double price);
}