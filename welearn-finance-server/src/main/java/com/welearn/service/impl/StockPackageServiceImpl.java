package com.welearn.service.impl;

import com.welearn.dictionary.finance.ReBatchPackageStatusConst;
import com.welearn.dictionary.finance.StockPackageStatusConst;
import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.finance.ReBatchPackage;
import com.welearn.entity.po.finance.RePackageItem;
import com.welearn.entity.po.finance.StockPackage;
import com.welearn.entity.qo.finance.ReBatchPackageQueryCondition;
import com.welearn.entity.qo.finance.RePackageItemQueryCondition;
import com.welearn.entity.qo.finance.StockPackageQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.*;
import com.welearn.service.StockPackageService;
import com.welearn.util.UUIDGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.welearn.dictionary.finance.StockPackageStatusConst.*;

/**
 * Description : StockPackageService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class StockPackageServiceImpl extends BaseServiceImpl<StockPackage,StockPackageQueryCondition,StockPackageMapper>
        implements StockPackageService{
    
    @Autowired
    private StockPackageMapper stockPackageMapper;

    @Autowired
    private ReBatchPackageMapper reBatchPackageMapper;

    @Autowired
    private RePackageItemMapper rePackageItemMapper;

    @Override
    StockPackageMapper getMapper() {
        return stockPackageMapper;
    }

    /**
     * 批量创建 货运包裹
     *
     * @param stockPackages 货运包裹列表
     * @return 货运包裹Id列表
     */
    @Override
    public List<StockPackage> batchCreatePackages(List<StockPackage> stockPackages) {
        for (StockPackage stockPackage : stockPackages) {
            if (StringUtils.isBlank(stockPackage.getSscc())){
                stockPackage.setId(UUIDGenerator.get(StockPackage.class));
                stockPackage.setSscc(UUIDGenerator.get());
                stockPackage.setStatus(VIRTUAL.ordinal());
            } else {
                stockPackage.setStatus(REALITY.ordinal());
            }
            stockPackage.setCount(stockPackage.getAmount());
            this.create(stockPackage);
        }
        return stockPackages;
    }

    /**
     * 货运包装拆分
     * 新包装需要和当前批次进行关联
     *
     * @param stockPackageId 原包装id
     * @param count        拆分数量
     * @return 拆分出的新包装
     */
    @Override @Transactional
    public StockPackage split(String stockPackageId, Integer count, User user) {
        StockPackage stockPackage = this.select(stockPackageId);
        if (Objects.isNull(stockPackage) || !stockPackage.getIsEnable() || stockPackage.getStatus() == VIRTUAL.ordinal() || count >= stockPackage.getCount())
            throw new BusinessVerifyFailedException("找不到需要拆分的包装, 或不可用, 或其不属于现实态, 或余量不足");
        // 检查先决条件
        // 不能与物品有关联
        RePackageItemQueryCondition rpi = new RePackageItemQueryCondition();
        rpi.setIsEnable(true);
        rpi.setPackageId(stockPackage.getId());
        List<RePackageItem> rePackageItems = rePackageItemMapper.selectByCondition(rpi);
        if (Objects.nonNull(rePackageItems) && !rePackageItems.isEmpty())
            throw new BusinessVerifyFailedException("该包装关联至了物品, 无法拆分");
        // 查找当前的批次关联信息
        ReBatchPackageQueryCondition rbp = new ReBatchPackageQueryCondition();
        rbp.setIsEnable(true);
        rbp.setSscc(stockPackage.getSscc());
        List<ReBatchPackage> reBatchPackages = reBatchPackageMapper.selectByCondition(rbp);
        if (Objects.isNull(reBatchPackages) || reBatchPackages.size() != 1)
            throw new BusinessVerifyFailedException("批次关联信息异常");
        // 创建新的包装
        StockPackage np = new StockPackage();
        np.setId(UUIDGenerator.get(StockPackage.class));
        np.setSscc(np.getId());
        np.setStatus(VIRTUAL.ordinal());
        np.setStockTypeId(stockPackage.getStockTypeId());
        np.setAmount(0);
        np.setCount(count);
        np.setCreatorId(user.getId());
        np.setRePackageId(stockPackageId);
        np.setRemark(String.format("%s 从关联包装 [%s] 中拆分出 %d 份, 创建的新包装", user.getName(), stockPackageId, count));
        this.create(np);
        stockPackage.setCount(stockPackage.getCount() - count);
        this.update(stockPackage);
        // 复制原先关联
        Date now = new Date();
        ReBatchPackage currentRbp = reBatchPackages.get(0);
        currentRbp.setCreatedAt(now);
        currentRbp.setUpdatedAt(now);
        currentRbp.setId(UUIDGenerator.get(ReBatchPackage.class));
        currentRbp.setSscc(np.getSscc());
        currentRbp.setUpdateUserId(user.getId());
        reBatchPackageMapper.insert(currentRbp);
        return np;
    }

    /**
     * 货运包装合并, 仅限于同批次
     * 新包装需要和当前批次进行关联
     * 会进行 5 * stockPackageIds.size() - 1 的数据库查询, 待优化
     * @param stockPackageIds 需要合并的包装id列表
     * @param mergePackageId 包装id列表中的某一个包装的id
     * @return 合并后的某个包装, 余量为需要合并的包装余量之和, 其余包装余量值为零 并被禁用
     */
    @Override
    public StockPackage merge(List<String> stockPackageIds, String mergePackageId, User user) {
        if (stockPackageIds.size() < 2)
            throw new BusinessVerifyFailedException("包装合并至少需要两个包装项");
        HashMap<String, StockPackage> pkMap = new HashMap<>();
        HashMap<String, ReBatchPackage> rbpMap = new HashMap<>();
        String batchId = null;
        // 先决条件检查 每个包装需要查3次
        for (String stockPackageId : stockPackageIds) {
            // 获取包装信息
            StockPackage stockPackage = this.select(stockPackageId);
            if (Objects.isNull(stockPackage) || !stockPackage.getIsEnable())
                throw new BusinessVerifyFailedException("stockPackageIds 存在非法 id");
            pkMap.put(stockPackageId, stockPackage);
            // 不能与物品有关联
            RePackageItemQueryCondition rpi = new RePackageItemQueryCondition();
            rpi.setIsEnable(true);
            rpi.setPackageId(stockPackage.getId());
            List<RePackageItem> rePackageItems = rePackageItemMapper.selectByCondition(rpi);
            if (Objects.nonNull(rePackageItems) && !rePackageItems.isEmpty())
                throw new BusinessVerifyFailedException("有包装关联至物品, 无法合并");
            // 检查是否为同一批次
            ReBatchPackageQueryCondition rbp = new ReBatchPackageQueryCondition();
            rbp.setSscc(stockPackage.getSscc());
            rbp.setIsEnable(true);
            List<ReBatchPackage> reBatchPackages = reBatchPackageMapper.selectByCondition(rbp);
            if (Objects.isNull(reBatchPackages) || reBatchPackages.size() != 1)
                throw new BusinessVerifyFailedException("批次关联信息异常");
            ReBatchPackage reBatchPackage = reBatchPackages.get(0);
            if (reBatchPackage.getStatus() != ReBatchPackageStatusConst.IN.ordinal())
                throw new BusinessVerifyFailedException("存在包装不在库, 无法合并");
            if (Objects.nonNull(batchId) && !batchId.equals(reBatchPackage.getBatchId()))
                throw new BusinessVerifyFailedException("包装非同一批次, 无法合并");
            else
                batchId = reBatchPackage.getBatchId();
            rbpMap.put(stockPackageId, reBatchPackage);
        }
        // 检查 mergePackageId 是否在 map 中
        StockPackage stockPackage = pkMap.get(mergePackageId);
        if (Objects.isNull(stockPackage))
            throw new BusinessVerifyFailedException("mergePackageId 非法");
        // 统计合并的数量 并更新 被合并的包装信息
        Integer mergeCount = 0;
        for (String stockPackageId : pkMap.keySet()) {
            if (stockPackageId.equals(mergePackageId))
                continue;
            StockPackage sp = pkMap.get(mergePackageId);
            sp.setRePackageId(mergePackageId);
            // 更新备注
            sp.setRemark(this.remarkPlus(sp.getRemark(), String.format("%s 将此包装的 %d 份合并至关联包装 [%s] 中, 此包装已被禁用", user.getName(), sp.getCount(), mergePackageId)));
            // 更新余量
            mergeCount += sp.getCount();
            sp.setCount(0);
            // 禁用 包装并更新
            sp.setIsEnable(false);
            this.update(sp);
            // 禁用 批次关联
            ReBatchPackage reBatchPackage = rbpMap.get(stockPackageId);
            reBatchPackage.setUpdateUserId(user.getId());
            reBatchPackage.setIsEnable(false);
            reBatchPackageMapper.updateByPK(reBatchPackage);
        }
        // 更新合并至的包装
        stockPackage.setRemark(this.remarkPlus(stockPackage.getRemark(), String.format("%s 将其它 %d 个包装的 %d 份合并至此包装", user.getName(), stockPackageIds.size() -1, mergeCount)));
        stockPackage.setCount(stockPackage.getCount() + mergeCount);
        this.update(stockPackage);
        // 更新合并至的包装批次关联
        ReBatchPackage reBatchPackage = rbpMap.get(mergePackageId);
        reBatchPackage.setUpdateUserId(user.getId());
        reBatchPackageMapper.updateByPK(reBatchPackage);
        return stockPackage;
    }

    /**
     * 包装备注追加方法
     * @param remark 原备注
     * @param plusStr 追加内容
     * @return 新备注
     */
    private String remarkPlus(String remark, String plusStr){
        if (StringUtils.isNotBlank(remark))
            remark += '\n';
        else
            remark = "";
        remark += plusStr;
        return remark;
    }
}
