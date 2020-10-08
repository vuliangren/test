package com.welearn.service;

import com.welearn.entity.po.common.User;
import com.welearn.entity.po.finance.StockPackage;
import com.welearn.entity.qo.finance.StockPackageQueryCondition;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : StockPackageService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface StockPackageService extends BaseService<StockPackage, StockPackageQueryCondition>{

    /**
     * 批量创建 货运包装
     * @param stockPackages 货运包装列表
     * @return 货运包装Id列表
     */
    List<StockPackage> batchCreatePackages(List<StockPackage> stockPackages);

    /**
     * 货运包装拆分
     * 新包装需要和当前批次进行关联
     *
     * @param stockPackageId 原包装id
     * @param count        拆分数量
     * @return 拆分出的新包装
     */
    StockPackage split(String stockPackageId, Integer count, User user) ;

    /**
     * 货运包装合并, 仅限于同批次
     * 新包装需要和当前批次进行关联
     *
     * @param stockPackageIds 需要合并的包装id列表
     * @param mergePackageId 包装id列表中的某一个包装的id
     * @return 合并后的某个包装, 余量为需要合并的包装余量之和, 其余包装余量值为零
     */
    StockPackage merge(List<String> stockPackageIds, String mergePackageId, User user);
}