package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.finance.FixedAssetItemTypeConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.finance.FixedAssets;
import com.welearn.feign.equipment.EquipmentFeignClient;
import com.welearn.feign.finance.FixedAssetsFeignClient;
import com.welearn.service.FixedAssetsEnterCheckHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/15.
 */
@Slf4j
@Service
public class FixedAssetsEnterCheckHandlerServiceImpl  extends ApplicationServiceImpl<FixedAssets> implements FixedAssetsEnterCheckHandlerService {

    @Autowired
    private FixedAssetsFeignClient fixedAssetsFeignClient;

    @Autowired
    private EquipmentFeignClient equipmentFeignClient;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(FixedAssets content) {
        FixedAssets fixedAssets = fixedAssetsFeignClient.create(content).getData();
        content.setId(fixedAssets.getId());
    }

    /**
     * 创建申请内容后调用, 可覆盖用来及时记录申请信息到content中
     *
     * @param content     申请内容
     * @param application 申请信息
     */
    @Override
    public void afterCreateContent(FixedAssets content, ApprovalApplication application) {
        content.setApplyId(application.getId());
        this.updateContent(content);
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        fixedAssetsFeignClient.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(FixedAssets content) {
        fixedAssetsFeignClient.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public FixedAssets selectContent(String contentId) {
        return fixedAssetsFeignClient.select(contentId).getData();
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(FixedAssets content) {
        return String.format("固定资产:%s - %s -%s 入账确认申请", content.getItemName(), content.getItemManufacturer(), content.getItemModel());
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.FIXED_ASSETS_ENTER_CHECK;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) throws Exception {
        FixedAssets fixedAssets = this.selectContent(contentId);
        fixedAssets.setIsCheck(true);
        this.updateContent(fixedAssets);
        // 如果执行成功则更新设备的固定资产id
        log.info(JSON.toJSONString(fixedAssets));
        if (fixedAssets.getItemType() == FixedAssetItemTypeConst.EQUIPMENT.ordinal() && StringUtils.isNotBlank(fixedAssets.getItemId())){
            Equipment equipment = new Equipment();
            equipment.setId(fixedAssets.getItemId());
            equipment.setFixedAssetId(contentId);
            equipmentFeignClient.update(equipment);
            log.info("{} , {}", fixedAssets.getItemId(), contentId);
        }
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) throws Exception {
        // 如果审批失败则禁用掉此固定资产信息
        fixedAssetsFeignClient.disable(contentId);
    }
}
