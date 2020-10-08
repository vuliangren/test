package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.apply.EquipmentYearPlanApplicationStatusConst;
import com.welearn.dictionary.apply.EquipmentYearPlanStatusConst;
import com.welearn.dictionary.common.CompanyConfigConst;
import com.welearn.dictionary.common.CompanyTypeConst;
import com.welearn.dictionary.common.DepartmentTagConst;
import com.welearn.dictionary.equipment.LargeEquipmentTypeConst;
import com.welearn.dictionary.procurement.ProcurementDetailStatusConst;
import com.welearn.dictionary.procurement.ProcurementTypeConst;
import com.welearn.entity.po.apply.EquipmentYearPlan;
import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import com.welearn.entity.po.apply.LargeEquipmentApplication;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.apply.EquipmentYearPlanApplicationQueryCondition;
import com.welearn.entity.qo.apply.EquipmentYearPlanQueryCondition;
import com.welearn.entity.qo.common.CompanyQueryCondition;
import com.welearn.entity.qo.common.DepartmentQueryCondition;
import com.welearn.entity.vo.response.apply.EquipmentYearPlanApplicationInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.CompanyFeignClient;
import com.welearn.feign.common.DepartmentFeignClient;
import com.welearn.feign.procurement.ProcurementDetailFeignClient;
import com.welearn.mapper.EquipmentYearPlanMapper;
import com.welearn.service.EquipmentYearPlanApplicationService;
import com.welearn.service.EquipmentYearPlanCommitteeApprovalService;
import com.welearn.service.EquipmentYearPlanService;
import com.welearn.service.LargeEquipmentApplicationHandlerService;
import com.welearn.util.GlobalContextUtil;
import com.welearn.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.welearn.dictionary.apply.EquipmentYearPlanApplicationStatusConst.*;

/**
 * Description : EquipmentYearPlanService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class EquipmentYearPlanServiceImpl extends BaseServiceImpl<EquipmentYearPlan,EquipmentYearPlanQueryCondition,EquipmentYearPlanMapper>
        implements EquipmentYearPlanService{

    @Value("${spring.jackson.date-format}")
    private String dateFormat;

    @Autowired
    private EquipmentYearPlanMapper equipmentYearPlanMapper;

    @Autowired
    private EquipmentYearPlanApplicationService equipmentYearPlanApplicationService;

    @Autowired
    private ProcurementDetailFeignClient procurementDetailFeignClient;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    @Override
    EquipmentYearPlanMapper getMapper() {
        return equipmentYearPlanMapper;
    }

    /**
     * 根据 公司id 获取其当年的年度设备计划
     * 如为医院类型公司而没有年度设备计划则创建一个并返回
     * @param companyId 公司id
     * @return 当前的年度设备计划
     */
    @Override @Transactional
    public EquipmentYearPlan current(String companyId) {
        EquipmentYearPlan currentPlan = null;
        // 验证企业
        Company company = companyFeignClient.select(companyId).getData();
        if (Objects.isNull(company) || company.getType() != CompanyTypeConst.HOSPITAL.ordinal())
            throw new BusinessVerifyFailedException("companyId 非法");
        // 查询年度计划
        EquipmentYearPlanQueryCondition condition = new EquipmentYearPlanQueryCondition();
        condition.setCompanyId(companyId);
        condition.setYear(new DateTime().getYear());
        List<EquipmentYearPlan> plans = this.search(condition);
        if (Objects.isNull(plans) || plans.isEmpty()){
            // 读取企业配置文件
            Map<String, Object> config = CompanyConfigConst.parseConfigJson(company.getConfigJson(), CompanyTypeConst.HOSPITAL);
            String start = config.get(CompanyConfigConst.EYPDAS.getKey()).toString().replace("year", condition.getYear().toString());
            String end = config.get(CompanyConfigConst.EYPDAE.getKey()).toString().replace("year", condition.getYear().toString());
            // 创建当前年度设备计划
            currentPlan = new EquipmentYearPlan();
            currentPlan.setCompanyId(companyId);
            currentPlan.setYear(condition.getYear());
            currentPlan.setDepartmentApplyStart(DateTime.parse(start, DateTimeFormat.forPattern(dateFormat)).toDate());
            currentPlan.setDepartmentApplyEnd(DateTime.parse(end, DateTimeFormat.forPattern(dateFormat)).toDate());
            currentPlan.setStatus(EquipmentYearPlanStatusConst.DEPARTMENT_APPLY.ordinal());
            this.create(currentPlan);
        } else {
            currentPlan = plans.get(0);
        }
        return currentPlan;
    }

    /**
     * 将 年度设备计划 提交给 装备委员会评审
     * 默认会有 定时任务 按时执行 也可手动触发
     *
     * @param equipmentYearPlanId 年度设备计划id
     * @param userId 设备科主任id
     */
    @Override @Transactional
    public void committeeApproval(String equipmentYearPlanId, String userId) {
        EquipmentYearPlan plan  = this.select(equipmentYearPlanId);
        if (Objects.isNull(plan) || StringUtils.isBlank(userId))
            throw new BusinessVerifyFailedException("参数 equipmentYearPlanId 或 userId 非法");
        // 更新年度设备计划状态
        plan.setStatus(EquipmentYearPlanStatusConst.COMMITTEE_APPROVAL.ordinal());
        plan.setDepartmentApplyEnd(new Date());
        // 更新年度设备计划申请项状态 2 -> 4
        EquipmentYearPlanApplicationQueryCondition condition = new EquipmentYearPlanApplicationQueryCondition();
        condition.setEquipmentYearPlanId(equipmentYearPlanId);
        condition.setStatus(DEPARTMENT_PASS.ordinal());
        List<EquipmentYearPlanApplication> applicationList = equipmentYearPlanApplicationService.search(condition);
        for (EquipmentYearPlanApplication equipmentYearPlanApplication : applicationList) {
            equipmentYearPlanApplication.setStatus(COMMITTEE_PASS.ordinal());
            equipmentYearPlanApplicationService.update(equipmentYearPlanApplication);
        }
        // 创建年度设备计划委员会评审申请
        EquipmentYearPlanCommitteeApprovalService equipmentYearPlanCommitteeApprovalService = SpringContextUtil.getBean(EquipmentYearPlanCommitteeApprovalService.class);
        equipmentYearPlanCommitteeApprovalService.apply(plan, userId, ApplicationTypeConst.FORM_APPLICATION.ordinal(), null);
    }

    // 定时任务 每月的1日的凌晨2点调整任务
    @Scheduled(cron = "0 0 2 1 * ?")
    public void autoCheckEquipmentYearPlan(){
        // 获取所有医院类型公司
        CompanyQueryCondition companyQueryCondition = new CompanyQueryCondition();
        companyQueryCondition.setIsEnable(true);
        companyQueryCondition.setType(CompanyTypeConst.HOSPITAL.ordinal());
        List<Company> companyList = companyFeignClient.search(companyQueryCondition).getData();
        // 根据公司id 获取当前年度设备计划
        for (Company company : companyList) {
            try {
                EquipmentYearPlan equipmentYearPlan = this.current(company.getId());
                // 判断申请时间是否过期
                if (equipmentYearPlan.getDepartmentApplyEnd().getTime() < new Date().getTime()){
                    // 查找 公司 设备科 科室主管
                    DepartmentQueryCondition condition = new DepartmentQueryCondition();
                    condition.setCompanyId(company.getId());
                    condition.setIsEnable(true);
                    condition.setTags(DepartmentTagConst.EQUIPMENT_DIVISION.getName());
                    List<Department> equipmentDivisions = departmentFeignClient.search(condition).getData();
                    if (Objects.isNull(equipmentDivisions) || equipmentDivisions.isEmpty())
                        throw new BusinessVerifyFailedException("公司:{} 的设备科 不存在", company.getName());
                    // TODO: 暂将 部门 的 adminId 对应的用户 认为 科室主管 职位人员
                    // 过期则 提交 装备委员会评审 并更新 计划 状态
                    this.committeeApproval(equipmentYearPlan.getId(), equipmentDivisions.get(0).getAdminId());

                    // 查找 公司 设备委员会 科室主管
                    condition.setTags(DepartmentTagConst.EQUIPMENT_COMMITTEE.getName());
                    List<Department> equipmentCommittees = departmentFeignClient.search(condition).getData();
                    if (Objects.isNull(equipmentCommittees) || equipmentCommittees.isEmpty())
                        throw new BusinessVerifyFailedException("公司:{} 的设备委员会 不存在", company.getName());
                    // TODO: 通知装备委员会
                }
            } catch (Exception e){
                log.error("AutoCheckEquipmentYearPlan 公司:{} 发送异常", company.getName());
            }
        }
    }

    /**
     * 年度设备计划 执行
     * 将 年度设备计划 包含的申请 转换为 待采购项
     * 如为大型医疗设备 则 创建 大型医疗设备装配申请
     * @param equipmentYearPlan 年度设备计划
     * @param applicationId 申请id
     * @param applicationType 申请类型编码
     */
    @Override
    @Transactional
    public void equipmentYearPlanExecute(EquipmentYearPlan equipmentYearPlan, String applicationId,
                                         String applicationType, LargeEquipmentApplicationHandlerService service) {
        EquipmentYearPlanApplicationQueryCondition condition = new EquipmentYearPlanApplicationQueryCondition();
        condition.setEquipmentYearPlanId(equipmentYearPlan.getId());
        condition.setStatus(DIRECTOR_PASS.ordinal());
        List<EquipmentYearPlanApplicationInfo> applications = equipmentYearPlanApplicationService.searchInfo(condition);
        for (EquipmentYearPlanApplicationInfo application : applications) {
            if (application.getIsLargeEquipment() == LargeEquipmentTypeConst.NOT.ordinal()){
                // 非大型医疗设备 直接转换 为待采购项
                this.equipmentYearPlanApplicationExecute(application, applicationId, applicationType);
                application.setStatus(PURCHASING.ordinal());
                equipmentYearPlanApplicationService.update(application);
            } else {
                // 大型医疗设备 创建 大型医疗设备配置申请
                LargeEquipmentApplication largeEquipmentApplication = new LargeEquipmentApplication();
                largeEquipmentApplication.setEquipmentYearPlanApplicationId(application.getId());
                // 创建申请
                service.save(largeEquipmentApplication, application.getApplicantId(), ApplicationTypeConst.FORM_APPLICATION.ordinal(), null);
                // TODO: 通知 设备申请人 补交 大型医疗设备装备申请相关信息 及 相关机关批复
            }
        }
    }

    /**
     * 年度设备计划申请 执行
     * 将 年度设备计划申请 转换为 待采购项
     * @param application 年度设备计划申请信息
     * @param applicationId 申请id
     * @param applicationType 申请类型编码
     */
    public void equipmentYearPlanApplicationExecute(EquipmentYearPlanApplicationInfo application, String applicationId, String applicationType){
        ProcurementDetail detail = new ProcurementDetail();
        detail.setClassification(ProcurementTypeConst.EQUIPMENT.ordinal());
        detail.setTypeId(application.getEquipmentTypeId());
        detail.setTypeName(application.getEquipmentTypeName());
        detail.setSpecification(application.getSpecification());
        detail.setCount(application.getCount());
        detail.setExpectedPrice(application.getUnitPrice());
        detail.setCapitalSource(application.getCapitalSource());
        detail.setDepartmentId(application.getDepartmentId());
        detail.setDepartmentName(application.getDepartmentName());
        detail.setApplicantId(application.getApplicantId());
        detail.setApplicantName(application.getApplicantName());
        detail.setCompanyId(application.getCompanyId());
        detail.setApplicationId(applicationId);
        detail.setApplicationType(applicationType);
        detail.setStatus(ProcurementDetailStatusConst.UN_PURCHASE.ordinal());
        procurementDetailFeignClient.create(detail);
    }
}
