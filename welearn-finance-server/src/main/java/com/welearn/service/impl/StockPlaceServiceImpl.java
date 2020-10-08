package com.welearn.service.impl;

import com.welearn.dictionary.finance.StockPlaceTypeConst;
import com.welearn.entity.po.finance.StockAllocation;
import com.welearn.entity.po.finance.StockPlace;
import com.welearn.entity.qo.finance.StockPlaceQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.StockAllocationMapper;
import com.welearn.mapper.StockPlaceMapper;
import com.welearn.service.StockPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : StockPlaceService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class StockPlaceServiceImpl extends BaseServiceImpl<StockPlace,StockPlaceQueryCondition,StockPlaceMapper>
        implements StockPlaceService{
    
    @Autowired
    private StockPlaceMapper stockPlaceMapper;

    @Autowired
    private StockAllocationMapper stockAllocationMapper;

    @Override
    StockPlaceMapper getMapper() {
        return stockPlaceMapper;
    }

    /**
     * 部门库存仓库初始化
     * TODO: 在部门创建时调用该接口初始化部门库存
     * 并创建 默认的 库存货位
     *
     * @param companyId      公司id
     * @param departmentId   部门id
     * @param departmentName 部门名称
     * @param buildingId     建筑id
     * @param floorId        楼层id
     */
    @Override
    public void departmentStockInit(String companyId, String departmentId, String departmentName, String buildingId, String floorId) {
        StockPlaceQueryCondition condition = new StockPlaceQueryCondition();
        condition.setDepartmentId(departmentId);
        condition.setIsEnable(true);
        List<StockPlace> search = this.search(condition);
        if (!search.isEmpty())
            throw new BusinessVerifyFailedException("部门库存仓库已创建");
        // 创建库存仓库
        StockPlace stockPlace = new StockPlace();
        stockPlace.setCompanyId(companyId);
        stockPlace.setDepartmentId(departmentId);
        stockPlace.setBuildingId(buildingId);
        stockPlace.setFloorId(floorId);
        stockPlace.setType(StockPlaceTypeConst.DEPARTMENT_STOCK.ordinal());
        stockPlace.setName(String.format("%s-部门库存", departmentName));
        stockPlace.setDescription(String.format("%s 部门创建时, 系统默认创建的部门库存仓库", departmentName));
        this.create(stockPlace);
        // 创建库存货位
        StockAllocation stockAllocation = new StockAllocation();
        stockAllocation.setX(0);
        stockAllocation.setY(0);
        stockAllocation.setZ(0);
        stockAllocation.setStockPlaceId(stockPlace.getId());
        stockAllocation.setName("默认货位");
        stockAllocation.setDescription(String.format("%s 部门创建时, 系统默认创建的部门库存货位", departmentName));
        stockAllocationMapper.insert(stockAllocation);
    }
}
