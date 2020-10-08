package com.welearn.service.impl;

import com.welearn.dictionary.authorization.RegisterTypeConst;
import com.welearn.dictionary.common.PermissionTypeConst;
import com.welearn.dictionary.common.PersistantConst;
import com.welearn.dictionary.common.SystemRoleConst;
import com.welearn.dictionary.equipment.EquipmentPermissionTypeConst;
import com.welearn.dictionary.notify.HtmlTemplateTypeConst;
import com.welearn.entity.po.common.*;
import com.welearn.entity.po.equipment.EquipmentPermission;
import com.welearn.entity.qo.common.CompanyQueryCondition;
import com.welearn.entity.qo.common.SupplierRegisterQueryCondition;
import com.welearn.entity.qo.common.UserQueryCondition;
import com.welearn.entity.qo.equipment.EquipmentPermissionQueryCondition;
import com.welearn.entity.vo.request.common.RegisterProcess;
import com.welearn.entity.vo.request.notify.SendHtmlTemplate;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.authorization.WeChatAppLoginResult;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.*;
import com.welearn.feign.equipment.EquipmentPermissionFeignClient;
import com.welearn.feign.notify.EmailSendFeignClient;
import com.welearn.service.RegisterService;
import com.welearn.service.WeChatAppAuthService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2018/11/29.
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private SupplierRegisterFeignClient supplierRegisterFeignClient;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private EmailSendFeignClient emailSendFeignClient;

    @Autowired
    private LinkCodeFeignClient linkCodeFeignClient;

    @Autowired
    private EquipmentPermissionFeignClient equipmentPermissionFeignClient;

    @Autowired
    private RoleFeignClient roleFeignClient;

    @Autowired
    private WeChatAppAuthService weChatAppAuthService;

    /**
     * 供应商注册申请
     *
     * @param supplierRegister 注册申请
     */
    @Override
    public void supplierRegister(SupplierRegister supplierRegister) {
        // 检查是否以及存在公司
        CompanyQueryCondition companyQueryCondition = new CompanyQueryCondition();
        companyQueryCondition.setIsEnable(true);
        companyQueryCondition.setName(supplierRegister.getCompanyName());
        List<Company> companies = companyFeignClient.search(companyQueryCondition).getData();
        if (Objects.nonNull(companies) && !companies.isEmpty())
            throw new BusinessVerifyFailedException("该公司已被注册, 如有疑问请与客服沟通");
        // 检查是否重复申请
        SupplierRegisterQueryCondition condition = new SupplierRegisterQueryCondition();
        condition.setUnifiedSocialCreditCode(supplierRegister.getUnifiedSocialCreditCode());
        condition.setHeadOfSalesIdCard(supplierRegister.getHeadOfSalesIdCard());
        condition.setIsEnable(true);
        List<SupplierRegister> data = supplierRegisterFeignClient.search(condition).getData();
        if (Objects.nonNull(data) && !data.isEmpty())
            throw new BusinessVerifyFailedException("请勿重复提交注册申请");
        // 检查邮箱是否合法
        UserQueryCondition userQueryCondition = new UserQueryCondition();
        userQueryCondition.setIsEnable(true);
        userQueryCondition.setEmail(supplierRegister.getHeadOfSalesEmail());
        List<User> users = userFeignClient.search(userQueryCondition).getData();
        if (Objects.nonNull(users) && !users.isEmpty())
            throw new BusinessVerifyFailedException("邮箱 已被使用, 请更换后重试");
        // 检查手机号是否合法
        userQueryCondition = new UserQueryCondition();
        userQueryCondition.setIsEnable(true);
        userQueryCondition.setTelephone(supplierRegister.getHeadOfSalesPhoneNumber());
        users = userFeignClient.search(userQueryCondition).getData();
        if (Objects.nonNull(users) && !users.isEmpty())
            throw new BusinessVerifyFailedException("手机号已被使用, 请更换后重试");
        // 存储注册申请
        Base64 base64 = new Base64();
        String password = DigestUtils.sha256Hex(base64.decode(supplierRegister.getHeadOfSalesPassword()));
        supplierRegister.setHeadOfSalesPassword(password);
        supplierRegisterFeignClient.create(supplierRegister);
        // 通知申请人申请已收到请等待申请结果通知
        SendHtmlTemplate sendHtmlTemplate = new SendHtmlTemplate();
        sendHtmlTemplate.setReceiver(supplierRegister.getHeadOfSalesEmail());
        sendHtmlTemplate.setSubject("供应商注册 申请提交成功 通知");
        sendHtmlTemplate.setCode(HtmlTemplateTypeConst.SUPPLIER_REGISTER_APPLY_SUCCESS.name());
        supplierRegister.setHeadOfSalesPassword(null);
        sendHtmlTemplate.setArgs(supplierRegister);
        emailSendFeignClient.forUserUseTemplate(sendHtmlTemplate);
        // TODO: 通知相关审批负责人

    }

    /**
     * 用户小程序注册
     *
     * @param name  用户名
     * @param telephone 手机号
     * @param sn    扫码时的二位码序列号(选填)
     */
    @Override
    public void userWeChatAppRequest(String name, String telephone, String sn) {
        User user = new User();
        user.setName(name);
        user.setTelephone(telephone);
        // 如果 SN 有值 尝试通过其关联的数据找到 公司 或 部门 id
        if (StringUtils.isNotBlank(sn)) {
            LinkCode linkCode = linkCodeFeignClient.selectBySerialNumber(sn).getData();
            if (linkCode.getIsBinding() && StringUtils.isNotBlank(linkCode.getBindingId())) {
                switch (PersistantConst.getByUUID(linkCode.getBindingId())) {
                    case Equipment:
                        EquipmentPermissionQueryCondition condition = new EquipmentPermissionQueryCondition();
                        condition.setEquipmentId(linkCode.getBindingId());
                        condition.setType(EquipmentPermissionTypeConst.DEPARTMENT.ordinal());
                        List<EquipmentPermission> permissions = equipmentPermissionFeignClient.search(condition).getData();
                        if (!permissions.isEmpty()) {
                            EquipmentPermission permission = permissions.get(0);
                            if (Objects.nonNull(permission)) {
                                user.setCompanyId(permission.getCompanyId());
                                user.setDepartmentId(permission.getDepartmentId());
                            }
                        }
                        break;
                }
            }
        }
        if (StringUtils.isBlank(user.getCompanyId()))
            throw new BusinessVerifyFailedException("扫描的二位码未绑定设备信息, 无法进行报修注册");
        CommonResponse<User> userCommonResponse = userFeignClient.registerRequest(user);
        if (!userCommonResponse.isSuccessResponse())
            throw new BusinessVerifyFailedException(userCommonResponse.getSignature().getErrorMessage());
    }

    /**
     * 用户小程序注册 验证验证码
     * @param telephone 手机
     * @param authCode 验证码
     * @param openId 微信小程序的openId
     * @param type 注册类型 影响注册用户的角色/职位
     * @return WeChatAppLoginResult
     */
    @Override
    public WeChatAppLoginResult userWeChatAppProcess(String telephone, String authCode, String openId, Integer type) {
        User user = null;
        switch (RegisterTypeConst.values()[type]) {
            case REPAIR_REPORTER_REGISTER:
                Role role = roleFeignClient.selectByCode(SystemRoleConst.HOSPITAL_REPAIR_REPORTER.getCode()).getData();
                CommonResponse<User> response = userFeignClient.registerProcess(new RegisterProcess(telephone, authCode,
                        Collections.singletonList(role.getId()), Collections.emptyList()));
                if (response.isSuccessResponse())
                    user = response.getData();
                else
                    throw new BusinessVerifyFailedException(response.getSignature().getErrorMessage());
                break;
        }
        if (Objects.isNull(user))
            throw new BusinessVerifyFailedException("用户注册失败, 请稍后重试");
        return weChatAppAuthService.bind(user, openId);
    }
}
