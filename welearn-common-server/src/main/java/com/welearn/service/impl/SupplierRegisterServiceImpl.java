package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.common.*;
import com.welearn.dictionary.notify.HtmlTemplateTypeConst;
import com.welearn.entity.po.common.*;
import com.welearn.entity.qo.common.CompanyQueryCondition;
import com.welearn.entity.qo.common.SupplierRegisterQueryCondition;
import com.welearn.entity.vo.request.notify.SendHtmlTemplate;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.notify.EmailSendFeignClient;
import com.welearn.mapper.SupplierRegisterMapper;
import com.welearn.service.CompanyService;
import com.welearn.service.DepartmentService;
import com.welearn.service.SupplierRegisterService;
import com.welearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.common.CompanyTypeConst.*;

/**
 * Description : SupplierRegisterService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SupplierRegisterServiceImpl extends BaseServiceImpl<SupplierRegister,SupplierRegisterQueryCondition,SupplierRegisterMapper>
        implements SupplierRegisterService{
    
    @Autowired
    private SupplierRegisterMapper supplierRegisterMapper;

    @Autowired
    private EmailSendFeignClient emailSendFeignClient;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    SupplierRegisterMapper getMapper() {
        return supplierRegisterMapper;
    }

    /**
     * 供应商注册审批
     *
     * @param supplierRegisterId 注册申请 id
     * @param isPassed           审批是否通过
     * @param approver           审批人
     * @param result             审批结果描述
     */
    @Override @Transactional
    public void approval(String supplierRegisterId, Boolean isPassed, User approver, String result) {
        SupplierRegister supplierRegister = this.select(supplierRegisterId);
        if (Objects.isNull(supplierRegister) || supplierRegister.getStatus() != SupplierRegisterStatusConst.UN_APPROVAL.ordinal())
            throw new BusinessVerifyFailedException("supplierRegisterId 非法");
        // 更新 supplierRegister
        supplierRegister.setIsEnable(isPassed);
        supplierRegister.setStatus(isPassed ? SupplierRegisterStatusConst.SUCCESS.ordinal() : SupplierRegisterStatusConst.FAILED.ordinal());
        supplierRegister.setApproverId(approver.getId());
        supplierRegister.setResult(result);
        this.update(supplierRegister);

        SendHtmlTemplate sendHtmlTemplate = new SendHtmlTemplate();
        // 处理审批通过后的操作
        if (isPassed){
            supplierInit(supplierRegister, approver);
            sendHtmlTemplate.setSubject("供应商注册 申请审批通过 通知");
            sendHtmlTemplate.setCode(HtmlTemplateTypeConst.SUPPLIER_REGISTER_APPROVAL_PASS.name());
        } else{
            sendHtmlTemplate.setSubject("供应商注册 申请审批失败 通知");
            sendHtmlTemplate.setCode(HtmlTemplateTypeConst.SUPPLIER_REGISTER_APPROVAL_FAIL.name());
        }
        // 发送邮件通知用户审批结果
        sendHtmlTemplate.setReceiver(supplierRegister.getHeadOfSalesEmail());
        sendHtmlTemplate.setArgs(supplierRegister);
        // TODO: 根据 HtmlTemplateTypeConst 添加HTML模板
        emailSendFeignClient.forUserUseTemplate(sendHtmlTemplate);

    }

    @Transactional
    protected void supplierInit(SupplierRegister supplierRegister, User approver){
        CompanyQueryCondition companyQueryCondition = new CompanyQueryCondition();
        companyQueryCondition.setIsEnable(true);
        companyQueryCondition.setName(supplierRegister.getCompanyName());
        List<Company> companies = companyService.search(companyQueryCondition);
        if (Objects.nonNull(companies) && !companies.isEmpty())
            throw new BusinessVerifyFailedException("申请注册的公司已经存在");
        // 1 构建用户信息
        User user = new User();
        user.setName(supplierRegister.getHeadOfSalesName());
        user.setPassword(supplierRegister.getHeadOfSalesPassword());
        user.setEmail(supplierRegister.getHeadOfSalesEmail());
        user.setTelephone(supplierRegister.getHeadOfSalesPhoneNumber());
        userService.create(user);
        // 2 构建公司信息
        Company company = new Company();
        company.setCreatorId(approver.getId());
        company.setName(supplierRegister.getCompanyName());
        company.setType(SUPPLIER.ordinal());
        company.setTags(supplierRegister.getSupplierType());
        company.setBusinessLicenseId(supplierRegister.getUnifiedSocialCreditCode());
        company.setConfigJson(JSON.toJSONString(CompanyConfigConst.getDefaultConfig(SUPPLIER)));
        company.setAdminId(user.getId());
        companyService.create(company);
        // 3 构建部门信息
        Department department = new Department();
        DepartmentTagConst sales = DepartmentTagConst.SALES_DIVISION;
        department.setCreatorId(approver.getId());
        department.setAdminId(user.getId());
        department.setCompanyId(company.getId());
        department.setCreatorId(user.getId());
        department.setName(sales.getDescription());
        department.setTags(sales.getName());
        departmentService.createAndInitPosition(department, user);
        // 4 查询相关角色
        // 5 分配销售部门主管职位
        // TODO: 绑定销售部门主管职位
        // 6 绑定相关角色
        // TODO: 绑定供应商管理员角色
    }
}
