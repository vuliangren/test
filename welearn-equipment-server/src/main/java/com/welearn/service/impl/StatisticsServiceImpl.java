package com.welearn.service.impl;

import com.welearn.cache.setter.EquipmentCacheSetter;
import com.welearn.dictionary.common.CompanyTypeConst;
import com.welearn.dictionary.equipment.*;
import com.welearn.entity.dto.common.DateStartInfo;
import com.welearn.entity.dto.equipment.*;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.po.equipment.MaintenanceCostItem;
import com.welearn.entity.po.equipment.MaintenanceRecord;
import com.welearn.entity.po.finance.FixedAssets;
import com.welearn.entity.qo.common.CompanyQueryCondition;
import com.welearn.entity.qo.common.DepartmentQueryCondition;
import com.welearn.entity.qo.equipment.InspectionPlanQueryCondition;
import com.welearn.entity.qo.equipment.InspectionRecordQueryCondition;
import com.welearn.entity.qo.equipment.MaintenanceRecordQueryCondition;
import com.welearn.entity.vo.response.equipment.RepairDispatchInfo;
import com.welearn.feign.common.CompanyFeignClient;
import com.welearn.feign.common.DepartmentFeignClient;
import com.welearn.feign.finance.FixedAssetsFeignClient;
import com.welearn.mapper.*;
import com.welearn.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description : 数据分析, 查询范围为(每年第一天 - 当前时间)
 * Created by Setsuna Jin on 2019/3/12.
 */
@Slf4j
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private EquipmentPermissionMapper equipmentPermissionMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private RepairCostItemMapper repairCostItemMapper;

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    @Autowired
    private RepairRequestMapper repairRequestMapper;

    @Autowired
    private MaintenanceMethodMapper maintenanceMethodMapper;

    @Autowired
    private MaintenanceTaskMapper maintenanceTaskMapper;

    @Autowired
    private TaskAssignmentMapper taskAssignmentMapper;

    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;

    @Autowired
    private EngineerMapper engineerMapper;

    @Autowired
    private RepairDispatchInsideMapper repairDispatchInsideMapper;

    @Autowired
    private EquipmentCacheSetter equipmentCacheSetter;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @Autowired
    private FixedAssetsFeignClient fixedAssetsFeignClient;

    @Autowired
    private MaintenanceCostItemMapper maintenanceCostItemMapper;

    @Autowired
    private InspectionRecordMapper inspectionRecordMapper;

    @Autowired
    private EquipmentBorrowMapper equipmentBorrowMapper;

    public List<Department> searchDepartment(String companyId){
        DepartmentQueryCondition condition = new DepartmentQueryCondition();
        condition.setIsEnable(true);
        condition.setCompanyId(companyId);
        return departmentFeignClient.search(condition).getData();
    }

    /**
     * 每天上午六点三十, 十二点三十, 下午六点三十 定时
     * 查询所有医院 的 设备统计数据 并 缓存
     */
    @Scheduled(cron = "0 30 6,12,18 * * ?")
    public void autoUpdateCompanyStatistics(){
        log.info("autoUpdateCompanyStatistics start");
        CompanyQueryCondition condition = new CompanyQueryCondition();
        condition.setIsEnable(true);
        condition.setType(CompanyTypeConst.HOSPITAL.ordinal());
        List<Company> hospitalList = companyFeignClient.search(condition).getData();
        for (Company hospital : hospitalList) {
            try {
                this.getEquipmentsStatistics(hospital.getId());
                log.info("getEquipmentsStatistics 执行成功, 医院 name: {}, id:{}", hospital.getName(), hospital.getId());
            } catch (Exception e){
                log.error("自动更新公司设备统计数据失败", e);
                log.error("getEquipmentsStatistics 执行失败, 医院 name: {}, id:{}", hospital.getName(), hospital.getId());
            }
            // TODO: 可考虑在夜间查询工程师的数据统计信息 并 缓存
        }
        log.info("autoUpdateCompanyStatistics finish");
    }

    @Override
    public Map<String, Object> getEquipmentsStatistics(String companyId){
        Map<String, Object> result = new HashMap<>();
        result.put("repairCost", this.repairCost(companyId));
        result.put("equipmentStatus", this.equipmentStatus(companyId));
        result.put("repairRequestCount", this.repairRequestCount(companyId));
        result.put("maintenanceCount", this.maintenanceCount(companyId));
        result.put("engineerCount", this.engineerCount(companyId));
        // 更新缓存内容
        equipmentCacheSetter.setEquipmentsStatistics(companyId, result);
        return result;
    }

    /**
     * 获取单个设备统计数据
     *
     * @param equipmentId 设备id
     * @return 统计数据
     */
    @Override
    public Map<String, Object> getEquipmentStatistics(String equipmentId) {
        Map<String, Object> result = new HashMap<>();
        DateStartInfo dateStartInfo = DateStartInfo.getDateRange();
        // 数据查询
        List<RepairRequestStatistic> repairRequests = repairRequestMapper.repairCostStatistic(null, null, equipmentId);
        List<RepairCostStatistic> repairCosts = repairCostItemMapper.repairCostStatistic(null, null, equipmentId);
        List<MaintenanceCostItem> maintenanceCosts = maintenanceCostItemMapper.maintenanceCostItemStatistic(null, null, equipmentId);
        MaintenanceRecordQueryCondition maintenanceRecordQueryCondition = new MaintenanceRecordQueryCondition();
        maintenanceRecordQueryCondition.setIsEnable(true);
        maintenanceRecordQueryCondition.setEquipmentId(equipmentId);
        List<MaintenanceRecord> maintenanceRecords = maintenanceRecordMapper.selectByCondition(maintenanceRecordQueryCondition);
        Integer inspectionRecordCount = inspectionRecordMapper.countByEquipmentId(equipmentId);
        List<EquipmentBorrow> borrowRecords = equipmentBorrowMapper.borrowStatistic(equipmentId);
        // NULL 值处理
        borrowRecords.forEach(item -> {
            if (Objects.isNull(item.getDayCount()))
                item.setDayCount(0d);
            if (Objects.isNull(item.getSumPrice()))
                item.setSumPrice(0d);
            if (Objects.isNull(item.getSatisfaction()))
                item.setSatisfaction(0);
        });
        // 数据统计
        result.put("repairCount", repairRequests.size());
        result.put("maintenanceCount", maintenanceRecords.size());
        result.put("inspectionCount", inspectionRecordCount);

        // 设备借用相关统计
        result.put("borrowCount", borrowRecords.size());
        result.put("borrowSumTime", borrowRecords.stream().map(EquipmentBorrow::getDayCount).reduce(0d, (a, b) -> a + b));
        result.put("borrowSumPrice", borrowRecords.stream().map(EquipmentBorrow::getSumPrice).reduce(0d, (a, b) -> a + b));
        Map<String, Long> borrowCountDepartment = new HashMap<>();
        Map<String, Double> borrowSumTimeDepartment = new HashMap<>();
        Map<String, Double> borrowAvgSatisfactionDepartment = new HashMap<>();
        borrowRecords.stream().map(EquipmentBorrow::getBorrowDepartmentName).collect(Collectors.toSet()).stream().forEach(departmentName -> {
            borrowCountDepartment.put(departmentName, borrowRecords.stream().filter(r -> departmentName.equals(r.getBorrowDepartmentName())).count());
            borrowSumTimeDepartment.put(departmentName, borrowRecords.stream().filter(r -> departmentName.equals(r.getBorrowDepartmentName())).map(EquipmentBorrow::getDayCount).reduce(0d, (a, b) -> a + b));
            borrowAvgSatisfactionDepartment.put(departmentName, borrowRecords.stream().filter(r -> departmentName.equals(r.getBorrowDepartmentName())).map(EquipmentBorrow::getSatisfaction).reduce(0, (a, b) -> a + b) * 1.0 / borrowCountDepartment.get(departmentName));
        });
        result.put("borrowCountDepartment", borrowCountDepartment);
        result.put("borrowSumTimeDepartment", borrowSumTimeDepartment);
        result.put("borrowAvgSatisfactionDepartment", borrowAvgSatisfactionDepartment);

        // 最近十二个月报修数量 维护数量 报修花费 维修花费
        Map<String, Long> last12MonthRepairCount = new HashMap<>();
        Map<String, Long> last12MonthMaintenanceCount = new HashMap<>();
        Map<String, Double> last12MonthRepairCost = new HashMap<>();
        Map<String, Double> last12MonthMaintenanceCost = new HashMap<>();
        DateTime thisMonthStart = new DateTime(dateStartInfo.getThisMonthStart());
        for (int i = 0; i < 12; i++) {
            DateTime start = thisMonthStart.minusMonths(i);
            Date end = start.plusMonths(1).toDate();
            String dateKey = start.toString("yyyy.MM");
            last12MonthRepairCount.put(dateKey, repairRequests.stream()
                    .filter(item -> item.getCreatedAt().getTime() >= start.toDate().getTime() && item.getCreatedAt().getTime() < end.getTime())
                    .count());
            last12MonthMaintenanceCount.put(dateKey, maintenanceRecords.stream()
                    .filter(item -> item.getCreatedAt().getTime() >= start.toDate().getTime() && item.getCreatedAt().getTime() < end.getTime())
                    .count());
            last12MonthRepairCost.put(dateKey, repairCosts.stream()
                    .filter(item -> item.getCreatedAt().getTime() >= start.toDate().getTime() && item.getCreatedAt().getTime() < end.getTime())
                    .map(RepairCostStatistic::getSumPrice)
                    .reduce(0d, (a, b) -> a + b));
            last12MonthMaintenanceCost.put(dateKey, maintenanceCosts.stream()
                    .filter(item -> item.getCreatedAt().getTime() >= start.toDate().getTime() && item.getCreatedAt().getTime() < end.getTime())
                    .map(MaintenanceCostItem::getSumPrice)
                    .reduce(0d, (a, b) -> a + b));
        }
        result.put("last12MonthRepairCount", last12MonthRepairCount);
        result.put("last12MonthMaintenanceCount", last12MonthMaintenanceCount);
        result.put("last12MonthRepairCost", last12MonthRepairCost);
        result.put("last12MonthMaintenanceCost", last12MonthMaintenanceCost);

        // 维修类型 自修 / 保修 / 外修
        Map<String, Long> repairType = new HashMap<>();
        repairType.put("selfRepair", repairRequests.stream().filter(r -> !r.getIsWarranty() && !r.getIsHelpApply()).count());
        repairType.put("warrantyRepair", repairRequests.stream().filter(RepairRequestStatistic::getIsWarranty).count());
        repairType.put("helpRepair", repairRequests.stream().filter(RepairRequestStatistic::getIsHelpApply).count());
        result.put("repairType", repairType);

        // 更新缓存信息
        equipmentCacheSetter.setEquipmentStatistics(equipmentId, result);
        return result;
    }


    /**
     * 维修花费统计
     * 总花费, 周花费, 周环比, 月环比, 各部门花费
     *
     * @param companyId 公司id
     * @return 统计数据
     */
    @Override
    public Map<String, Object> repairCost(String companyId) {
        Map<String, Object> result = new HashMap<>();
        DateStartInfo dateStartInfo = DateStartInfo.getDateRange();
        List<RepairCostStatistic> repairCostStatistics = repairCostItemMapper.repairCostStatistic(companyId, dateStartInfo.getSearchStartAt(), null);
        double sumCost = repairCostStatistics.stream().filter(a -> a.getCreatedAt().getTime() >= dateStartInfo.getThisYearStart().getTime())
                .map(RepairCostStatistic::getSumPrice).reduce(0d, (a, b) -> a + b);
        double thisWeekCost = repairCostStatistics.stream().filter(a -> a.getCreatedAt().getTime() >= dateStartInfo.getThisWeekStart().getTime())
                .map(RepairCostStatistic::getSumPrice).reduce(0d, (a, b) -> a + b);
        double lastWeekCost = repairCostStatistics.stream().filter(a -> a.getCreatedAt().getTime() >= dateStartInfo.getLastWeekStart().getTime() && a.getCreatedAt().getTime() < dateStartInfo.getThisWeekStart().getTime())
                .map(RepairCostStatistic::getSumPrice).reduce(0d, (a, b) -> a + b);
        double thisMonthCost = repairCostStatistics.stream().filter(a -> a.getCreatedAt().getTime() >= dateStartInfo.getThisMonthStart().getTime())
                .map(RepairCostStatistic::getSumPrice).reduce(0d, (a, b) -> a + b);
        double lastMonthCost = repairCostStatistics.stream().filter(a -> a.getCreatedAt().getTime() >= dateStartInfo.getLastMonthStart().getTime() && a.getCreatedAt().getTime() < dateStartInfo.getThisMonthStart().getTime())
                .map(RepairCostStatistic::getSumPrice).reduce(0d, (a, b) -> a + b);
        // 统计部门花费
        List<Department> departmentList = searchDepartment(companyId);
        List<DepartmentStatisticInfo<Double>> departments = departmentList.stream().map(a -> new DepartmentStatisticInfo<>(a.getId(), a.getName(), 0d))
                .peek(info -> info.setValue(repairCostStatistics.stream().filter(a -> a.getDepartmentId().equals(info.getId())).map(RepairCostStatistic::getSumPrice).reduce(0d, (a, b) -> a + b)))
                .collect(Collectors.toList());
        result.put("sumCost", sumCost);
        result.put("thisWeekCost", thisWeekCost);
        result.put("lastWeekCost", lastWeekCost);
        result.put("thisMonthCost", thisMonthCost);
        result.put("lastMonthCost", lastMonthCost);
        result.put("departments", departments);
        return result;
    }

    /**
     * 设备数量统计
     * 设备总数, 各部门设备数量及状态, 设备利用率
     *
     * @param companyId 公司id
     * @return 统计数据
     */
    @Override
    public Map<String, Object> equipmentStatus(String companyId) {
        Map<String, Object> result = new HashMap<>();
        // 所有的设备数量
        Integer companySumCount = equipmentPermissionMapper.selectRefEquipmentsCount(EquipmentPermissionCodeConst.OWNERSHIP.ordinal(),
                EquipmentPermissionTypeConst.COMPANY.ordinal(), companyId);
        List<EquipmentStatusStatistic> equipments = equipmentMapper.equipmentStatusStatistic(companyId);
        // 已分配设备数量
        int departmentSumCount = equipments.size();
        // 统计 部门设备 不同设备状态的 数量
        List<Department> departmentList = searchDepartment(companyId);
        List<DepartmentStatisticInfo<List<Long>>> departmentCount = departmentList.stream().map(a -> new DepartmentStatisticInfo<List<Long>>(a.getId(), a.getName(), new ArrayList<>()))
                .peek(a -> a.setValue(Arrays.stream(EquipmentStatusConst.values()).map(s -> equipments.stream().filter(e -> e.getDepartmentId().equals(a.getId()) && e.getEquipmentStatus().equals(s.ordinal())).count()).collect(Collectors.toList())))
                .collect(Collectors.toList());
        List<Long> equipmentStatusCount = Arrays.stream(EquipmentStatusConst.values()).map(s -> equipments.stream().filter(e -> e.getEquipmentStatus().equals(s.ordinal())).count()).collect(Collectors.toList());
        // 待初始化设备 包含 未分配设备
        equipmentStatusCount.set(EquipmentStatusConst.UN_INIT.ordinal(), equipmentStatusCount.get(0) + companySumCount - departmentSumCount);
        // 计算 设备利用率
        // 分母: 总数 - 报废 - 遗失 - 封存 - 调剂
        Long denominator = companySumCount - equipmentStatusCount.get(EquipmentStatusConst.SCRAP.ordinal()) - equipmentStatusCount.get(EquipmentStatusConst.LOSE.ordinal()) - equipmentStatusCount.get(EquipmentStatusConst.SEAL.ordinal()) - equipmentStatusCount.get(EquipmentStatusConst.ADJUST.ordinal());
        // 分子: 分母 - 故障
        Long numerator = denominator - equipmentStatusCount.get(EquipmentStatusConst.FAULT.ordinal());
        Double useRatio = denominator == 0 ? 0d : numerator / denominator;
        // 统计设备入账资产
        List<FixedAssets> assets = fixedAssetsFeignClient.equipmentValueStatistic(companyId).getData();
        // 总原值
        Double equipmentOriginalValue = assets.stream().map(FixedAssets::getOriginalValue).reduce(0d, (a, b) -> a + b);
        // 总残值
        Double equipmentResidualValue = assets.stream().map(FixedAssets::getResidualValue).reduce(0d, (a, b) -> a + b);
        // 统计各个部门的设备资产总值
        List<DepartmentStatisticInfo<Double>> departmentOriginalValue = departmentList.stream().map(a -> new DepartmentStatisticInfo<>(a.getId(), a.getName(), 0d))
                .peek(a -> a.setValue(assets.stream().filter(v -> v.getDepartmentId().equals(a.getId())).map(FixedAssets::getOriginalValue).reduce(0d, (n, m) -> n + m)))
                .collect(Collectors.toList());
        List<DepartmentStatisticInfo<Double>> departmentResidualValue = departmentList.stream().map(a -> new DepartmentStatisticInfo<>(a.getId(), a.getName(), 0d))
                .peek(a -> a.setValue(assets.stream().filter(v -> v.getDepartmentId().equals(a.getId())).map(FixedAssets::getResidualValue).reduce(0d, (n, m) -> n + m)))
                .collect(Collectors.toList());
        departmentOriginalValue.add(new DepartmentStatisticInfo<>("other", "其它", assets.stream().filter(s -> StringUtils.isEmpty(s.getDepartmentId())).map(FixedAssets::getOriginalValue).reduce(0d, (a, b) -> a + b)));
        departmentResidualValue.add(new DepartmentStatisticInfo<>("other", "其它", assets.stream().filter(s -> StringUtils.isEmpty(s.getDepartmentId())).map(FixedAssets::getResidualValue).reduce(0d, (a, b) -> a + b)));

        result.put("equipmentOriginalValue", equipmentOriginalValue);
        result.put("equipmentResidualValue", equipmentResidualValue);
        result.put("departmentOriginalValue", departmentOriginalValue);
        result.put("departmentResidualValue", departmentResidualValue);
        result.put("companySumCount", companySumCount);
        result.put("departmentSumCount", departmentSumCount);
        result.put("departmentCount", departmentCount);
        result.put("equipmentStatusCount", equipmentStatusCount);
        result.put("useRatio", useRatio);
        return result;
    }

    /**
     * 报修数量统计
     * 报修总数, 各状态的数量, 各部门报修数量, 最近三十天报修数量, 周环比, 月环比, 设备平均维修耗时
     *
     * @param companyId 公司id
     * @return 统计数据
     */
    @Override
    public Map<String, Object> repairRequestCount(String companyId) {
        Map<String, Object> result = new HashMap<>();
        DateStartInfo dateStartInfo = DateStartInfo.getDateRange();
        List<RepairRequestStatistic> repairRequestStatistics = repairRequestMapper.repairCostStatistic(companyId, dateStartInfo.getSearchStartAt(), null);
        int sumCount = repairRequestStatistics.size();
        // 各状态的数量
        List<Long> statusCount = Arrays.stream(RepairRequestStatusConst.values()).map(s -> repairRequestStatistics.stream().filter(rr -> rr.getStatus().equals(s.ordinal())).count()).collect(Collectors.toList());
        // 统计部门数量
        List<Department> departmentList = searchDepartment(companyId);
        List<DepartmentStatisticInfo<List<Long>>> departmentStatusCount = departmentList.stream().map(d -> new DepartmentStatisticInfo<List<Long>>(d.getId(), d.getName(), new ArrayList<>()))
                .peek(d -> d.setValue(Arrays.stream(RepairRequestStatusConst.values()).map(s -> repairRequestStatistics.stream().filter(rr -> rr.getDepartmentId().equals(d.getId()) && rr.getStatus().equals(s.ordinal())).count()).collect(Collectors.toList())))
                .collect(Collectors.toList());
        // 最近三十天报修数量
        Map<String, Long> last30DayCount = new HashMap<>();
        DateTime thisDayStart = new DateTime(dateStartInfo.getThisDayStart());
        for (int i = 0; i < 30; i++) {
            DateTime start = thisDayStart.minusDays(i);
            Date end;
            if (i == 0 )
                end = new Date();
            else
                end = thisDayStart.minusDays(i - 1).toDate();
            last30DayCount.put(start.toString("yyyy-MM-dd"), repairRequestStatistics.stream()
                    .filter(rr -> rr.getCreatedAt().getTime() >= start.toDate().getTime() && rr.getCreatedAt().getTime() < end.getTime()).count());
        }
        // 周环比 月环比 由前端根据下列值自行计算
        long thisWeekCount = repairRequestStatistics.stream().filter(rr -> rr.getCreatedAt().getTime() >= dateStartInfo.getThisWeekStart().getTime()).count();
        long thisMonthCount = repairRequestStatistics.stream().filter(rr -> rr.getCreatedAt().getTime() >= dateStartInfo.getThisMonthStart().getTime()).count();
        long lastWeekCount = repairRequestStatistics.stream().filter(rr -> rr.getCreatedAt().getTime() >= dateStartInfo.getLastWeekStart().getTime() && rr.getCreatedAt().getTime() < dateStartInfo.getThisWeekStart().getTime()).count();
        long lastMonthCount = repairRequestStatistics.stream().filter(rr -> rr.getCreatedAt().getTime() >= dateStartInfo.getLastMonthStart().getTime() && rr.getCreatedAt().getTime() < dateStartInfo.getThisMonthStart().getTime()).count();
        // 设备平均维修耗时 仅计算有finishedAt值的报修
        List<RepairRequestStatistic> finishedRepairRequests = repairRequestStatistics.stream().filter(rr -> Objects.nonNull(rr.getFinishedAt())).collect(Collectors.toList());
        Integer sumRepairCostTime = finishedRepairRequests.stream().map(a -> new Period(new DateTime(a.getCreatedAt()), new DateTime(a.getFinishedAt())).getMinutes()).reduce(0, (a, b) -> a + b);
        Double avgRepairCostTime = finishedRepairRequests.size() == 0 ? 0d : sumRepairCostTime * 1.0 / finishedRepairRequests.size();
        // 联系厂商保修 申请外部援助 数量
        long warrantyCount = repairRequestStatistics.stream().filter(RepairRequestStatistic::getIsWarranty).count();
        long helpApplyCount = repairRequestStatistics.stream().filter(RepairRequestStatistic::getIsHelpApply).count();
        long selfRepairCount = sumCount - warrantyCount - helpApplyCount;

        result.put("sumCount", sumCount);
        result.put("statusCount", statusCount);
        result.put("departmentStatusCount", departmentStatusCount);
        result.put("last30DayCount", last30DayCount);
        result.put("thisWeekCount", thisWeekCount);
        result.put("thisMonthCount", thisMonthCount);
        result.put("lastWeekCount", lastWeekCount);
        result.put("lastMonthCount", lastMonthCount);
        result.put("avgRepairCostTime", avgRepairCostTime);
        result.put("warrantyCount", warrantyCount);
        result.put("helpApplyCount", helpApplyCount);
        result.put("selfRepairCount", selfRepairCount);
        return result;
    }

    /**
     * 维护相关数量统计
     * 维护任务总数, 维护方式总数, 最近三十天维护任务分配数量, 维护任务分配总数, 维护执行率
     *
     * @param companyId 公司id
     * @return 统计数据
     */
    @Override
    public Map<String, Object> maintenanceCount(String companyId) {
        Map<String, Object> result = new HashMap<>();
        DateStartInfo dateStartInfo = DateStartInfo.getDateRange();
        // 维护方式数量
        Integer methodCount = maintenanceMethodMapper.countByCompanyId(companyId);
        // 维护任务数量
        Integer taskCount = maintenanceTaskMapper.countByCompanyId(companyId);
        // 维护记录数量
        Integer recordCount = maintenanceRecordMapper.countByCompanyId(companyId, dateStartInfo.getSearchStartAt());
        List<TaskAssignmentStatistic> taskAssignmentStatistics = taskAssignmentMapper.taskAssignmentStatistic(companyId, dateStartInfo.getSearchStartAt());
        Integer assignmentCount = taskAssignmentStatistics.size();
        // 各个状态数量
        List<Long> statusCount = Arrays.stream(MaintenanceTaskAssignmentStatusConst.values()).map(s -> taskAssignmentStatistics.stream().filter(ta -> ta.getStatus().equals(s.ordinal())).count()).collect(Collectors.toList());
        // 最近三十天报修数量
        Map<String, Long> last30DayCount = new HashMap<>();
        DateTime thisDayStart = new DateTime(dateStartInfo.getThisDayStart());
        for (int i = 0; i < 30; i++) {
            DateTime start = thisDayStart.minusDays(i);
            Date end = i == 0 ? new Date() : thisDayStart.minusDays(i - 1).toDate();
            last30DayCount.put(start.toString("yyyy-MM-dd"), taskAssignmentStatistics.stream()
                    .filter(rr -> rr.getCreatedAt().getTime() >= start.toDate().getTime() && rr.getCreatedAt().getTime() < end.getTime()).count());
        }
        // 维护执行率
        List<Double> collect = taskAssignmentStatistics.stream().map(ta -> ta.getTaskPriority() == 0 ? 0d : ta.getPriority() * 1.0 / ta.getTaskPriority()).collect(Collectors.toList());
        Double reduce = collect.stream().reduce(0d, (a, b) -> a + b);
        Double executeRatio = reduce == 0d ? 1 : collect.size() / reduce;

        result.put("methodCount", methodCount);
        result.put("taskCount", taskCount);
        result.put("recordCount", recordCount);
        result.put("assignmentCount", assignmentCount);
        result.put("statusCount", statusCount);
        result.put("last30DayCount", last30DayCount);
        result.put("executeRatio", executeRatio);
        return result;
    }

    /**
     * 工程师数量统计
     * 内部工程师数量, 外部工程师数量, 临时工程师数量
     *
     * @param companyId 公司id
     * @return 统计数据
     */
    @Override
    public Map<String, Object> engineerCount(String companyId) {
        Map<String, Object> result = new HashMap<>();
        List<Engineer> engineers = engineerMapper.engineerStatistic(companyId);
        Integer sumCount = engineers.size();
        List<Long> typeCount = Arrays.stream(EngineerTypeConst.values()).map(t -> engineers.stream().filter(e -> e.getType().equals(t.ordinal())).count()).collect(Collectors.toList());
        // 取内部 和 外部工程师  用于显示经验排名
        List<Engineer> rankingEngineer = engineers.stream().filter(e -> e.getType() < 2).collect(Collectors.toList());
        result.put("sumCount", sumCount);
        result.put("typeCount", typeCount);
        result.put("rankingEngineer", rankingEngineer);
        return result;
    }

    /**
     * 工程师详细统计
     * 维修派工数量, 维修派工状态分布, 维护记录数量
     *
     * @param engineerId 工程师id
     * @return 统计数据
     */
    @Override
    public Map<String, Object> getEngineerStatistics(String engineerId) {
        Map<String, Object> result = new HashMap<>();
        DateStartInfo dateStartInfo = DateStartInfo.getDateRange();
        List<RepairDispatchInfo> repairDispatchInfos = repairDispatchInsideMapper.RepairDispatchInfoStatistic(engineerId, dateStartInfo.getSearchStartAt());
        // 维护记录数量
        Integer maintenanceRecordCount = maintenanceRecordMapper.countByEngineerId(engineerId, dateStartInfo.getSearchStartAt());
        // 维修派工数量
        Integer repairDispatchCount = repairDispatchInfos.size();
        // 统计不同状态的派工数量
        List<Long> repairDispatchStatusCount = Arrays.stream(InsideDispatchStatusConst.values()).map(s -> repairDispatchInfos.stream().filter(rd -> rd.getStatus().equals(s.ordinal())).count()).collect(Collectors.toList());
        // 计算派工到领工的平均响应时间 单位:分钟
        List<RepairDispatchInfo> collect = repairDispatchInfos.stream().filter(rd -> Objects.nonNull(rd.getReceivedAt())).collect(Collectors.toList());
        Integer sumResponseCostTime = collect.stream().map(rd -> new Period(new DateTime(rd.getCreatedAt()), new DateTime(rd.getReceivedAt())).getMinutes()).reduce(0, (a, b) -> a + b);
        Double avgResponseCostTime = collect.size() == 0 ? 0 : sumResponseCostTime * 1.0 / collect.size();
        result.put("maintenanceRecordCount", maintenanceRecordCount);
        result.put("repairDispatchCount", repairDispatchCount);
        result.put("repairDispatchStatusCount", repairDispatchStatusCount);
        result.put("avgResponseCostTime", avgResponseCostTime);
        // 更新到缓存中
        equipmentCacheSetter.setEngineerStatistics(engineerId, result);
        return result;
    }
}
