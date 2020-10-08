package com.welearn.service;

import com.welearn.entity.po.common.User;
import com.welearn.entity.po.finance.StockPackage;
import com.welearn.entity.po.finance.StockTask;
import com.welearn.entity.po.finance.StockType;
import com.welearn.entity.qo.finance.StockTaskQueryCondition;
import com.welearn.entity.vo.response.finance.StockTaskInfo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : StockTaskService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface StockTaskService extends BaseService<StockTask, StockTaskQueryCondition>{

    /**
     * 创建 外部 入库 库存作业
     *
     * @param stockId             库存id
     * @param taskDescription     入库类型描述
     * @param taskRemark          入库备注
     * @param stockPackages       库存包装内容(未创建)
     * @param userId              创建人id
     * @return 库存作业
     */
    StockTask createStockInTask(@NotBlank String stockId, @NotBlank String taskDescription, String taskRemark,
                                @NotNull List<StockPackage> stockPackages, @NotBlank String userId);


    /**
     * 创建 外部 入库 库存作业
     * @param stockType 库存类型信息
     * @param taskDescription 任务类型描述
     * @param stockPackages 入库包装列表
     * @param taskRemark 任务备注
     * @param userId 创建人id
     * @return 库存任务
     */
    StockTask createStockInTask0(@NotNull @Valid StockType stockType, @NotBlank String taskDescription, String taskRemark,
                                 @NotNull List<StockPackage> stockPackages, @NotBlank String userId);

    /**
     * 创建 外部 出库 库存作业
     *
     * @param stockId 库存id
     * @param taskDescription 出库描述
     * @param taskRemark 作业备注
     * @param taskCount 出库数量
     * @param userId 创建人
     * @return 库存作业
     */
    StockTask createStockOutTask(@NotBlank String stockId, @NotBlank String taskDescription, String taskRemark,
                                 @NotNull @Min(value = 1) Integer taskCount, @NotBlank String userId);

    /**
     * 创建 内部 消耗 库存记录
     * 创建一个已完成的任务, 仅用于记录消耗
     * 更新公司库存的实际消耗值, 对操作批次进行拆分 设置状态为 消耗, 关联至任务
     *
     * @param packageId 消耗的包装id
     * @param count 消耗的数量
     * @param description 消耗的描述
     * @param userId 创建人
     * @return 库存作业
     */
    StockTask createStockConsumeTask(@NotBlank String packageId, @NotNull @Min(value = 1) Integer count,
                                     @NotBlank String description, @NotBlank String userId);

    /**
     * 取消库存任务
     * @param taskId 库存任务id
     * @param userId 取消人
     */
    void cancelStockTask(@NotBlank String taskId, @NotBlank String reason, @NotBlank String userId);

    /**
     * 创建 内部 转移 库存任务
     * 同时创建两个任务, 一个是入库, 一个出库, 同时对两个任务进行任务关联, 从任务可以只有部门无具体人员信息
     * 出库任务为主任务 入库任务为从任务, 主任务关联从任务, 从任务的 reTaskId为空, 任务取消时, 对任意一个任务进行取消都会取消两个任务
     * @param contentType 类型: 医疗设备/备件/耗材
     * @param itemTypeId 物品类型id
     * @param itemSpecification 物品规格
     * @param taskType 任务类型: 出/入
     * @param taskDescription 任务类型描述
     * @param taskCount 任务数量
     * @param taskRemark 任务备注
     * @param userId 创建人id
     * @return 库存作业
     */
    StockTask createStockTransferTask(@NotNull Integer contentType, @NotBlank String itemTypeId, @NotBlank String itemSpecification,
                                      @NotNull @Min(value = 0) @Max(value = 1) Integer taskType, @NotBlank String taskDescription,
                                      @NotNull @Min(value = 1) Integer taskCount, String taskRemark, @NotBlank String userId) ;

    /**
     * 入库 扫码登记 确认
     * 无条码的包装需要先打印条码并贴好, 再进行扫码
     * 需要先创建批次, 然后和批次关联, 任务完成后解除原批次离库状态关联
     * 注意 任务关联id
     */
    StockTaskInfo stockInScanCode(@NotBlank String code, @NotBlank String userId);


    /**
     * 出库 扫码登记 确认
     * 有任务才可以出库, 且出库量需要和任务量一致, 出库前可进行包装的拆分, 合并
     * 无条码的包装需要先打印条码并贴好, 再进行扫码
     * 未被入库扫码登记确认前, 货运包装与现有批次属于离库状态, 记在出库扫码登记人名下
     * 注意 任务关联id
     */
    StockTaskInfo stockOutScanCode(@NotBlank String code, @NotBlank String userId);

    /**
     * 离库 扫码
     *
     * @param code 条码内容
     * @param userId 扫码人
     */
    StockTaskInfo stockLeaveScanCode(@NotBlank String code, @NotBlank String userId);

    /**
     * 回库 扫码
     *
     * @param code 条码内容
     * @param userId 扫码人
     */
    StockTaskInfo stockBackScanCode(@NotBlank String code, @NotBlank String userId);
}