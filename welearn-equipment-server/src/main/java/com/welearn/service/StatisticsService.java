package com.welearn.service;

import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/12.
 */
public interface StatisticsService {

    /**
     * 获取公司设备统计数据
     * @param companyId 公司id
     * @return 统计数据
     */
    Map<String, Object> getEquipmentsStatistics(String companyId);

    /**
     * 获取单个设备统计数据
     * @param equipmentId 设备id
     * @return 统计数据
     */
    Map<String, Object> getEquipmentStatistics(String equipmentId);

    /**
     * 维修花费统计
     * 总花费, 周花费, 周环比, 月环比, 各部门花费
     * @param companyId 公司id
     * @return 统计数据
     */
    Map<String, Object> repairCost(String companyId);

    /**
     * 设备数量统计
     * 设备总数, 各部门设备数量及状态, 设备利用率
     * @param companyId 公司id
     * @return 统计数据
     */
    Map<String, Object> equipmentStatus(String companyId);

    /**
     * 报修数量统计
     * 报修总数, 各状态的数量, 各部门报修数量, 最近三十天报修数量, 周环比, 月环比, 设备平均维修耗时
     * @param companyId 公司id
     * @return 统计数据
     */
    Map<String, Object> repairRequestCount(String companyId);

    /**
     * 维护相关数量统计
     * 维护任务总数, 维护方式总数, 最近三十天维护任务分配数量, 维护任务分配总数, 维护执行率
     * @param companyId 公司id
     * @return 统计数据
     */
    Map<String, Object> maintenanceCount(String companyId);

    /**
     * 工程师数量统计
     * 内部工程师数量, 外部工程师数量, 临时工程师数量
     * @param companyId 公司id
     * @return 统计数据
     */
    Map<String, Object> engineerCount(String companyId);

    /**
     * 工程师详细统计
     * 内部工程师数量, 外部工程师数量, 临时工程师数量
     * @param engineerId 工程师id
     * @return 统计数据
     */
    Map<String, Object> getEngineerStatistics(String engineerId);

//    /**
//     * 证书数量统计
//     * 待审批, 审批通过的, 审批失败的, 过期的, 类型数量
//     * @param companyId 公司id
//     * @return 统计数据
//     */
//    Map<String, Object> certificationCount(String companyId);
//
//    /**
//     * 用户数量统计
//     * 公司总人数, 部门数量, 职位数量, 每个职位的人数, 部门人数
//     * @param companyId 公司id
//     * @return 统计数据
//     */
//    Map<String, Object> userCount(String companyId);
}
