package com.welearn.service.impl;

import com.welearn.dictionary.finance.StockPlaceTypeConst;
import com.welearn.dictionary.finance.StockTaskStatusConst;
import com.welearn.dictionary.finance.StockTaskTypeConst;
import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.finance.*;
import com.welearn.entity.qo.finance.StockTaskQueryCondition;
import com.welearn.entity.vo.response.finance.StockTaskInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.DepartmentFeignClient;
import com.welearn.feign.common.UserFeignClient;
import com.welearn.mapper.ReTaskPackageMapper;
import com.welearn.mapper.StockTaskMapper;
import com.welearn.service.StockPackageService;
import com.welearn.service.StockService;
import com.welearn.service.StockTaskService;
import com.welearn.service.StockTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.finance.StockTaskStatusConst.*;
import static com.welearn.dictionary.finance.StockTaskTypeConst.*;

/**
 * Description : StockTaskService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class StockTaskServiceImpl extends BaseServiceImpl<StockTask,StockTaskQueryCondition,StockTaskMapper>
        implements StockTaskService{

    @Autowired
    private StockTypeService stockTypeService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockTaskMapper stockTaskMapper;

    @Autowired
    private StockPackageService stockPackageService;

    @Autowired
    private ReTaskPackageMapper reTaskPackageMapper;

    @Override
    StockTaskMapper getMapper() {
        return stockTaskMapper;
    }


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
    @Override @Transactional
    public StockTask createStockInTask(String stockId, String taskDescription, String taskRemark, List<StockPackage> stockPackages, String userId) {
        User user = userFeignClient.select(userId).getData();
        if (Objects.isNull(user) || Objects.isNull(user.getDepartmentId()))
            throw new BusinessVerifyFailedException("userId 非法");
        Department department = departmentFeignClient.select(user.getDepartmentId()).getData();
        if (Objects.isNull(department))
            throw new BusinessVerifyFailedException("departmentId 非法");
        // 获取库存
        Stock stock = stockService.select(stockId);
        if (Objects.isNull(stock))
            throw new BusinessVerifyFailedException("stockTask.stockId 非法");
        StockTask stockTask = new StockTask();
        // 构建任务
        stockTask.setStockId(stockId);
        stockTask.setTypeDescription(taskDescription);
        stockTask.setCommanderRemark(taskRemark);
        stockTask.setType(IN.ordinal());
        stockTask.setStatus(UN_PROCESS.ordinal());
        stockTask.setCommanderId(userId);
        stockTask.setCommanderName(user.getName());
        stockTask.setCommanderDepartmentId(user.getDepartmentId());
        stockTask.setCommanderDepartmentName(department.getName());
        // 登记总数
        Integer taskCount = 0;
        for (StockPackage stockPackage : stockPackages) {
            if (Objects.isNull(stockPackage.getStockTypeId()) || !stockPackage.getStockTypeId().equals(stock.getStockTypeId()))
                throw new BusinessVerifyFailedException("存在包装的库存类型id非法");
            stockPackage.setCount(stockPackage.getAmount());
            taskCount += stockPackage.getCount();
        }
        stockTask.setTaskCount(taskCount);
        stockTask.setFinishCount(0);
        // 创建任务
        this.create(stockTask);
        // 批量创建包装
        List<StockPackage> packages = stockPackageService.batchCreatePackages(stockPackages);
        // 创建任务包装关联
        for (StockPackage stockPackage : packages) {
            ReTaskPackage reTaskPackage = new ReTaskPackage(stockTask.getId(), stockPackage.getId(), stockPackage.getAmount());
            reTaskPackageMapper.insert(reTaskPackage);
        }
        // 更新在途入库数量
        stock.setInTransitInCount(stock.getInTransitInCount() + stockTask.getTaskCount());
        stockService.update(stock);
        // TODO: 调用通知服务
        return stockTask;
    }

    /**
     * 创建 外部 入库 库存作业
     *
     * @param stockType       库存类型信息
     * @param taskDescription 任务类型描述
     * @param taskRemark      任务备注
     * @param stockPackages   入库包装列表
     * @param userId          创建人id
     * @return 库存任务
     */
    @Override @Transactional
    public StockTask createStockInTask0(StockType stockType, String taskDescription, String taskRemark, List<StockPackage> stockPackages, String userId) {
        Stock stock;
        // 库存类型为未创建的新库存类型
        if (Objects.isNull(stockType.getId())){
            // 创建库存类型
            stockTypeService.create(stockType);
            // 创建公司库存
            stock = new Stock();
            stock.setCompanyId(stockType.getCompanyId());
            stock.setStockTypeId(stockType.getId());
            stock.setType(StockPlaceTypeConst.COMPANY_STOCK.ordinal());
            stockService.create(stock);
        } else {
            stock = stockService.select(stockType.getId());
        }
        // 更新包裹的库存类型id
        stockPackages.forEach(sp -> sp.setStockTypeId(stockType.getId()));
        return this.createStockInTask(stock.getId(), taskDescription, taskRemark, stockPackages, userId);
    }


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
    @Override @Transactional
    public StockTask createStockOutTask(String stockId, String taskDescription, String taskRemark, Integer taskCount, String userId) {
        User user = userFeignClient.select(userId).getData();
        if (Objects.isNull(user) || Objects.isNull(user.getDepartmentId()))
            throw new BusinessVerifyFailedException("userId 非法");
        Department department = departmentFeignClient.select(user.getDepartmentId()).getData();
        if (Objects.isNull(department))
            throw new BusinessVerifyFailedException("departmentId 非法");
        Stock stock = stockService.select(stockId);
        if (Objects.isNull(stock))
            throw new BusinessVerifyFailedException("stockTask.stockId 非法");
        // 创建任务
        StockTask stockTask = new StockTask();
        // 构建任务
        stockTask.setStockId(stockId);
        stockTask.setTypeDescription(taskDescription);
        stockTask.setCommanderRemark(taskRemark);
        stockTask.setType(OUT.ordinal());
        stockTask.setStatus(UN_PROCESS.ordinal());
        stockTask.setCommanderId(userId);
        stockTask.setCommanderName(user.getName());
        stockTask.setCommanderDepartmentId(user.getDepartmentId());
        stockTask.setCommanderDepartmentName(department.getName());
        stockTask.setTaskCount(taskCount);
        stockTask.setFinishCount(0);
        this.create(stockTask);
        // 更新在途出库数量
        stock.setInTransitOutCount(stock.getInTransitOutCount() + stockTask.getTaskCount());
        stockService.update(stock);
        // TODO: 调用通知服务
        return stockTask;
    }



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
    @Override @Transactional
    public StockTask createStockConsumeTask(String packageId, Integer count, String description, String userId) {
        User user = userFeignClient.select(userId).getData();
        // TODO: 根据包装id 查找 批次, 根据批次 查找 库存, 根据库存的类型id 得到公司库存, 更新相关消耗参数
        return null;
    }

    /**
     * 取消库存任务
     *
     * @param taskId 库存任务id
     * @param reason 取消原因
     * @param userId   取消人(任务发布人)
     * @return 库存任务
     */
    @Override @Transactional
    public void cancelStockTask(String taskId, String reason, String userId) {
        User user = userFeignClient.select(userId).getData();
        // 先检查当前要取消的任务id是否具有 主任务
        StockTaskQueryCondition condition = new StockTaskQueryCondition();
        condition.setIsEnable(true);
        condition.setReTaskId(taskId);
        List<StockTask> stockTasks = this.search(condition);
        // 有主任务 对主任务和其本身进行操作
        if (Objects.nonNull(stockTasks) && !stockTasks.isEmpty()){
            this.doCancelStockTask(stockTasks.get(0), reason, user);
            this.doCancelStockTask(this.select(taskId), reason, user);
        }
        // 无主任务 或本身就是主任务
        else {
            StockTask stockTask = this.select(taskId);
            this.doCancelStockTask(stockTask, reason, user);
            // 检查是否有从任务
            if (Objects.nonNull(stockTask.getReTaskId()))
                this.doCancelStockTask(this.select(stockTask.getReTaskId()), reason, user);
        }
    }

    /**
     * 取消库存任务的执行逻辑
     * @param stockTask 库存任务
     * @param reason 取消原因
     * @param user 用户
     */
    private void doCancelStockTask(StockTask stockTask, String reason, User user){
        Stock stock = stockService.select(stockTask.getStockId());
        if (Objects.isNull(stock) || !stock.getIsEnable())
            throw new BusinessVerifyFailedException("stockTask.stockId 非法");
        // 所有任务, 未处理时均可直接取消掉
        if (stockTask.getStatus() == UN_PROCESS.ordinal()){
            if (stockTask.getType() == IN.ordinal())
                stock.setInTransitInCount(stock.getInTransitInCount() - stockTask.getFinishCount());
            else if (stockTask.getType() == OUT.ordinal())
                stock.setInTransitOutCount(stock.getInTransitOutCount() - stockTask.getFinishCount());
        } else if (stockTask.getStatus() == PROCESSING.ordinal()){
            // 对外部任务 或 内部转移从任务
            if (Objects.isNull(stockTask.getReTaskId()) && stockTask.getType() < CONSUME.ordinal()){
                // 对已经完成任务的包装项不予取消, 未完成的可以取消, 如需取消已完成的可重新创建任务
                Integer rollbackCount = stockTask.getTaskCount() - stockTask.getFinishCount();
                if (stockTask.getType() == IN.ordinal())
                    stock.setInTransitInCount(stock.getInTransitInCount() - rollbackCount);
                else if (stockTask.getType() == OUT.ordinal())
                    stock.setInTransitOutCount(stock.getInTransitOutCount() - rollbackCount);
            }
            // 对内部任务
            else {
                // 出库任务中 已离库的包装可以重新回库, 但已出库的包装不能再回库, 如需取消可重新创建入库任务
                if (Objects.nonNull(stockTask.getReTaskId())){
                    // TODO: 此处处理 内部转移主(出库)任务的取消
                    // TODO: 对于回库操作时, 需要对关联的任务的完成数量 进行减操作, 回一个减去一个
                }
                // 对库存消耗任务暂时不予取消
                else if (stockTask.getType() == CONSUME.ordinal()){
                    // TODO: 此处处理 内部消耗任务的取消
                }
            }
        }
        stockTask.setCancelReason(String.format("%s 取消了该任务, 原因: %s", user.getName(), reason));
        this.update(stockTask);
        stockService.update(stock);
    }

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
    @Override @Transactional
    public StockTask createStockTransferTask(Integer contentType, String itemTypeId, String itemSpecification,
                                             Integer taskType, String taskDescription, Integer taskCount, String taskRemark, String userId) {
        User user = userFeignClient.select(userId).getData();
        return null;
    }

    /**
     * 入库 扫码
     * 无条码的包装需要先打印条码并贴好, 再进行扫码
     * 需要先创建批次, 然后和批次关联, 任务完成后解除原批次离库状态关联
     * 每次扫码 会改变库存的在途入库库存(-), 库存采购总量(+), 批次的 总量(+) 余量(+) 计划余量(+), 更新包装原批次关联状态为 离库 禁用, 创建新批次关联 在库
     * 注意 任务关联id
     *
     * @param code 条码内容
     * @param userId 扫码人
     */
    @Override
    public StockTaskInfo stockInScanCode(String code, String userId) {
        return null;
    }

    /**
     * 出库 扫码
     * 有任务才可以出库, 且出库量需要和任务量一致, 出库前可进行包装的拆分, 合并
     * 无条码的包装需要先打印条码并贴好, 再进行扫码
     * 未被入库扫码登记确认前, 货运包装与现有批次属于离库状态, 记在出库扫码登记人名下
     * 注意 任务关联id
     *
     * @param code 条码内容
     * @param userId 扫码人
     */
    @Override
    public StockTaskInfo stockOutScanCode(String code, String userId) {
        return null;
    }

    /**
     * 离库 扫码
     *
     * @param code 条码内容
     * @param userId 扫码人
     */
    public StockTaskInfo stockLeaveScanCode(String code, String userId) {
        return null;
    }

    /**
     * 回库 扫码
     *
     * @param code 条码内容
     * @param userId 扫码人
     */
    public StockTaskInfo stockBackScanCode(String code, String userId) {
        return null;
    }
}
