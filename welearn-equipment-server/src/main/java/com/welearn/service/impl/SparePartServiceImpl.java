package com.welearn.service.impl;

import com.welearn.dictionary.common.CompanyConfigConst;
import com.welearn.dictionary.equipment.SparePartOriginConst;
import com.welearn.dictionary.equipment.SparePartPriceType;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.equipment.SparePart;
import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.po.equipment.SparePartType;
import com.welearn.entity.po.procurement.Procurement;
import com.welearn.entity.qo.equipment.SparePartQueryCondition;
import com.welearn.entity.qo.equipment.SparePartTypeQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.error.exception.DbOperationFailedException;
import com.welearn.feign.procurement.ProcurementFeignClient;
import com.welearn.mapper.SparePartMapper;
import com.welearn.service.SparePartService;
import com.welearn.service.SparePartTypeService;
import com.welearn.util.UUIDGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Description : SparePartService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SparePartServiceImpl extends BaseServiceImpl<SparePart,SparePartQueryCondition,SparePartMapper>
        implements SparePartService{
    
    @Autowired
    private SparePartMapper sparePartMapper;

    @Autowired
    private SparePartTypeService sparePartTypeService;

    @Autowired
    private ProcurementFeignClient procurementFeignClient;

    @Override
    SparePartMapper getMapper() {
        return sparePartMapper;
    }

    @Override
    @Transactional
    public SparePart create(SparePart entity) throws DbOperationFailedException {
        // 更新类型的进货量
        SparePartType type = sparePartTypeService.select(entity.getTypeId());
        type.setRetailPurchases(type.getRetailPurchases() + entity.getCount());
        sparePartTypeService.update(type);
        // 设置默认余量
        entity.setRemain(entity.getCount());
        super.create(entity);
        return entity;
    }

    private SparePartType selectOrCreateSparePartType(String name, String specification, Integer priceType){
        SparePartTypeQueryCondition condition = new SparePartTypeQueryCondition();
        condition.setIsEnable(true);
        condition.setName(name);
        condition.setSpecification(specification);
        condition.setPriceType(priceType);
        List<SparePartType> sparePartTypes = sparePartTypeService.search(condition);
        if (Objects.isNull(sparePartTypes) || sparePartTypes.isEmpty()){
            SparePartType sparePartType = new SparePartType();
            sparePartType.setName(name);
            sparePartType.setSpecification(specification);
            sparePartType.setPriceType(priceType);
            sparePartType.setInTransitOutCount(0);
            sparePartType.setInTransitInCount(0);
            sparePartType.setConsumption(0);
            sparePartType.setRetailPurchases(0);
            return sparePartTypeService.create(sparePartType);
        }
        else
            return sparePartTypes.get(0);
    }

    /**
     * 根据入库项创建批次
     *
     * @param sparePartInItem 入库项
     * @param origin 入库来源
     * @return 配件批次
     */
    @Override
    public SparePart createFromSparePartInItem(SparePartInItem sparePartInItem, Integer origin) {
        // 查询配件类型 没有则创建
        SparePartType sparePartType = this.selectOrCreateSparePartType(sparePartInItem.getName(),
                sparePartInItem.getSpecification(), sparePartInItem.getPriceType());
        // 创建批次
        SparePart sparePart = new SparePart();
        sparePart.setTypeId(sparePartType.getId());
        sparePart.setOrigin(origin);
        sparePart.setPrice(sparePartInItem.getPrice());
        sparePart.setCount(sparePartInItem.getCount());
        sparePart.setRemain(sparePartInItem.getCount());
        sparePart.setManufacturerName(sparePartInItem.getManufacturerName());
        sparePart.setModel(sparePartInItem.getModel());
        if (StringUtils.isNotBlank(sparePart.getProcurementId())){
            Procurement data = procurementFeignClient.select(sparePart.getProcurementId()).getData();
            if (Objects.nonNull(data)) {
                sparePart.setTimeCost(data.costHours());
                sparePart.setProcurementId(sparePart.getProcurementId());
            }
        }
        // 更新类型的余量
        sparePartType.setRetailPurchases(sparePartType.getRetailPurchases() + sparePart.getCount());
        sparePartTypeService.update(sparePartType);
        return super.create(sparePart);
    }

    /**
     * 在途出库
     *
     * @param sparePartId 批次id
     * @param count       数量
     */
    @Override @Transactional
    public void inTransitOut(String sparePartId, Integer count) {
        // 更新批次的
        SparePart sparePart = this.select(sparePartId);
        if (Objects.isNull(sparePart) || !sparePart.getIsEnable() || sparePart.getRemain() - sparePart.getInTransitOutCount() < count)
            throw new BusinessVerifyFailedException("sparePartId 非法 或 余量不足");
        sparePart.setInTransitOutCount(sparePart.getInTransitOutCount() + count);
        // 更新类型的
        SparePartType sparePartType = sparePartTypeService.select(sparePart.getTypeId());
        if (Objects.isNull(sparePartType) || !sparePartType.getIsEnable() || sparePartType.calLogicBalance() < count)
            throw new BusinessVerifyFailedException("sparePartId 对应的类型 非法 或 余量不足");
        sparePartType.setInTransitOutCount(sparePartType.getInTransitOutCount() + count);
        sparePartTypeService.update(sparePartType);
        this.update(sparePart);
    }

    /**
     * 库存出库
     *
     * @param sparePartId 批次id
     * @param count       数量
     */
    @Override @Transactional
    public void stockOut(String sparePartId, Integer count) {
        // 更新批次的
        SparePart sparePart = this.select(sparePartId);
        if (Objects.isNull(sparePart) || !sparePart.getIsEnable() || sparePart.getRemain() < count)
            throw new BusinessVerifyFailedException("sparePartId 非法 或 余量不足");
        sparePart.setRemain(sparePart.getRemain() - count);
        if (sparePart.getInTransitOutCount() <= count)
            sparePart.setInTransitOutCount(0);
        else
            sparePart.setInTransitOutCount(sparePart.getInTransitOutCount() - count);
        // 更新类型的
        SparePartType sparePartType = sparePartTypeService.select(sparePart.getTypeId());
        if (Objects.isNull(sparePartType) || !sparePartType.getIsEnable() || sparePartType.getRetailPurchases() - sparePartType.getConsumption() < count)
            throw new BusinessVerifyFailedException("sparePartId 对应的类型 非法 或 余量不足");
        sparePartType.setConsumption(sparePartType.getConsumption() + count);
        if (sparePartType.getInTransitOutCount() <= count)
            sparePartType.setInTransitOutCount(0);
        else
            sparePartType.setInTransitOutCount(sparePartType.getInTransitOutCount() - count);

        sparePartTypeService.update(sparePartType);
        this.update(sparePart);
    }

    /**
     * 计算配件的价格类型
     *
     * @param company 公司
     * @param price   单价
     * @return 价格类型值
     */
    @Override
    public Integer priceTypeCal(Company company, Double price) {
        Integer bargainPrice = company.getConfig(CompanyConfigConst.PRICE_BARGAIN_SPARE_PART);
        Integer lowPrice = company.getConfig(CompanyConfigConst.PRICE_LOW_SPARE_PART);
        if (price >= bargainPrice && price < lowPrice)
            return SparePartPriceType.BARGAIN.ordinal();
        Integer highPrice = company.getConfig(CompanyConfigConst.PRICE_HIGH_SPARE_PART);
        if (price >= lowPrice && price < highPrice)
            return SparePartPriceType.LOW.ordinal();
        Integer overPrice = company.getConfig(CompanyConfigConst.PRICE_OVER_SPARE_PART);
        if (price >= highPrice && price < overPrice)
            return SparePartPriceType.HIGH.ordinal();
        else
            return SparePartPriceType.OVER.ordinal();
    }
}
