package com.welearn.service;

import com.welearn.entity.po.finance.StockPlace;
import com.welearn.entity.qo.finance.StockPlaceQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : StockPlaceService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface StockPlaceService extends BaseService<StockPlace, StockPlaceQueryCondition>{

    /**
     * 部门库存仓库初始化
     * 在部门创建时调用该接口初始化部门库存
     * 并创建 默认的 库存货位
     *
     * @param companyId      公司id
     * @param departmentId   部门id
     * @param departmentName 部门名称
     * @param buildingId     建筑id
     * @param floorId        楼层id
     */
    void departmentStockInit(String companyId, String departmentId, String departmentName, String buildingId, String floorId);
}