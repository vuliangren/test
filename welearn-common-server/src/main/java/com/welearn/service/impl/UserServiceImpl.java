package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.welearn.dictionary.authorization.IdTypeConst;
import com.welearn.dictionary.common.*;
import com.welearn.dictionary.notify.HtmlTemplateTypeConst;
import com.welearn.dictionary.notify.SmsSignatureConst;
import com.welearn.dictionary.notify.SmsTemplateConst;
import com.welearn.entity.dto.authorization.Token;
import com.welearn.entity.po.common.*;
import com.welearn.entity.po.wechat.WechatInfo;
import com.welearn.entity.qo.common.ReUserDepartmentQueryCondition;
import com.welearn.entity.qo.common.ReUserRoleQueryCondition;
import com.welearn.entity.qo.common.UserQueryCondition;
import com.welearn.entity.qo.common.WechatAppUserQueryCondition;
import com.welearn.entity.qo.equipment.EngineerQueryCondition;
import com.welearn.entity.vo.request.notify.SendForUser;
import com.welearn.entity.vo.request.notify.SendHtmlTemplate;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.common.ContactInfo;
import com.welearn.entity.vo.response.common.UserInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.error.exception.DbOperationFailedException;
import com.welearn.error.exception.UserAuthCheckFailedException;
import com.welearn.feign.equipment.EngineerFeignClient;
import com.welearn.feign.notify.EmailSendFeignClient;
import com.welearn.feign.notify.SmsSendFeignClient;
import com.welearn.feign.storage.QiniuFileFeignClient;
import com.welearn.mapper.*;
import com.welearn.service.*;
import com.welearn.util.PaginateUtil;
import com.welearn.util.PasswordGenerator;
import com.welearn.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description : 用户业务接口实现
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<User,UserQueryCondition,UserMapper> implements UserService {

    // 最大密码重试次数
    private static final int MAX_PASSWORD_TRY_COUNT = 10;
    // 验证码发送时间间隔
    private static final long AUTH_CODE_SEND_INTERVAL = 7200;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ReUserRoleMapper reUserRoleMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private PositionService positionService;

    @Autowired
    private SmsSendFeignClient smsSendFeignClient;

    @Autowired
    private EmailSendFeignClient emailSendFeignClient;

    @Autowired
    private ReUserDepartmentService reUserDepartmentService;

    @Autowired
    private EngineerFeignClient engineerFeignClient;

    @Autowired
    private QiniuFileFeignClient qiniuFileFeignClient;

    @Autowired
    private WechatAppUserMapper wechatAppUserMapper;

    @Override
    UserMapper getMapper() {
        return userMapper;
    }


    private interface EntryCallback {
        void run(User user, User operator, Company company, Department department, String password);
    }

    @Override
    public User create(User user){
        if (StringUtils.isBlank(user.getEmail()) && StringUtils.isBlank(user.getTelephone()))
            throw new BusinessVerifyFailedException("email 或 telephone 需至少选填一个");
        if (StringUtils.isNotBlank(user.getEmail()) && !RegexUtil.checkEmail(user.getEmail()))
            throw new BusinessVerifyFailedException("email 非法");
        if (StringUtils.isNotBlank(user.getTelephone()) && !RegexUtil.checkPhone(user.getTelephone()))
            throw new BusinessVerifyFailedException("telephone 非法");
        // 检查手机或邮箱的唯一性
        if (StringUtils.isNotBlank(user.getEmail())){
            if (!this.selectUserByEmail(user.getEmail()).isEmpty())
                throw new BusinessVerifyFailedException("该邮箱已被使用, 请更换邮箱后重试");
        }
        if (StringUtils.isNotBlank(user.getTelephone())){
            if (!this.selectUserByTelephone(user.getTelephone()).isEmpty())
                throw new BusinessVerifyFailedException("该手机号已被使用, 请更换手机号后重试");
        }
        // TODO: 待处理用户配置, 如是否首次登陆等
        user.setConfigJson("{}");
        return super.create(user); 
    }

    private List<User> selectUserByEmail(String email){
        UserQueryCondition condition = new UserQueryCondition();
        condition.setIsEnable(true);
        condition.setEmail(email);
        return this.search(condition);
    }

    private List<User> selectUserByTelephone(String telephone){
        UserQueryCondition condition = new UserQueryCondition();
        condition.setIsEnable(true);
        condition.setTelephone(telephone);
        return this.search(condition);
    }

    private static String parseId(String id){
        Base64 base64 = new Base64();
        return new String(base64.decode(id));
    }

    private static String parsePassword(String password){
        Base64 base64 = new Base64();
        return DigestUtils.sha256Hex(base64.decode(password));
    }

    private User getUserFromTypeAndId(Integer type, String id){
        String loginId = parseId(id);
        UserQueryCondition condition = new UserQueryCondition();
        condition.setIsEnable(true);
        if (type == IdTypeConst.EMAIL.ordinal() && RegexUtil.checkEmail(loginId))
            condition.setEmail(loginId);
        else if (type == IdTypeConst.TELEPHONE.ordinal() && RegexUtil.checkPhone(loginId))
            condition.setTelephone(loginId);
        else
            throw new BusinessVerifyFailedException("ID 格式不匹配");
        List<User> search = this.search(condition);
        if (Objects.isNull(search) || search.size() != 1)
            throw new BusinessVerifyFailedException("ID 非法");
        return search.get(0);
    }

    /**
     * 发送用户注册用验证码
     * @param user 用户
     * @param authCode 验证码
     */
    private void sendRegisterAuthCodeMessage(User user, String authCode) {
        // 短信通知
        if (RegexUtil.checkPhone(user.getTelephone())){
            SendForUser sendForUser = new SendForUser();
            sendForUser.setCode(SmsTemplateConst.USER_REGISTER.getCode());
            sendForUser.setSignature(SmsSignatureConst.RUIYANG_MEDICAL);
            sendForUser.setUser(user);
            sendForUser.setParams(SmsTemplateConst.USER_REGISTER.getParams(authCode));
            smsSendFeignClient.sendForUser(sendForUser);
        }
        // 邮件通知 TODO: 待添加模板
//        if (RegexUtil.checkEmail(user.getEmail())){
//            SendHtmlTemplate sendHtmlTemplate = new SendHtmlTemplate();
//            sendHtmlTemplate.setReceiver(user.getEmail());
//            sendHtmlTemplate.setSubject("用户注册操作的验证码");
//            sendHtmlTemplate.setCode(HtmlTemplateTypeConst.FORGET_PASSWORD.name());
//            Map<String, String> args = new HashMap<>();
//            args.put("userName", user.getName());
//            args.put("authCode", authCode);
//            sendHtmlTemplate.setArgs(args);
//            emailSendFeignClient.forUserUseTemplate(sendHtmlTemplate);
//        }
    }

    /**
     * 用户注册使用
     *
     * @param user 用户信息 (姓名, 手机号)
     * @return 保存的禁用后的用户信息
     */
    @Override
    public User registerRequest(User user) {
        if (StringUtils.isBlank(user.getCompanyId()) || !RegexUtil.checkPhone(user.getTelephone()))
            throw new BusinessVerifyFailedException("companyId 或 telephone 非法");
        UserQueryCondition condition = new UserQueryCondition();
        condition.setTelephone(user.getTelephone());
        condition.setState(UserStateConst.REGISTERING.ordinal());
        condition.setIsEnable(false);
        List<User> search = this.search(condition);
        Date now = new Date();
        if (search.isEmpty()) {
            // 创建禁用的注册状态用户 设置验证码并发送
            user.setState(UserStateConst.REGISTERING.ordinal());
            user.setIsEnable(false);
            user.setAuthCode(PasswordGenerator.get(6, 1));
            user.setAuthCodeLastSendAt(now);
            user.setPassword(null);
            user = this.create(user);
            this.sendRegisterAuthCodeMessage(user, user.getAuthCode());
        } else {
            user = search.get(0);
            // 判断是否需要更新验证码
            if ((now.getTime() - user.getAuthCodeLastSendAt().getTime()) / 1000 > AUTH_CODE_SEND_INTERVAL){
                String authCode = PasswordGenerator.get(6, 1);
                user.setAuthCode(authCode);
                user.setAuthCodeLastSendAt(now);
                this.update(user);
                this.sendRegisterAuthCodeMessage(user, authCode);
            } else
                throw new BusinessVerifyFailedException("验证码发送时间间隔最短为两小时, 请勿重复操作");
        }
        return user;
    }

    /**
     * 用户注册检查(验证验证码)
     *
     * @param telephone   用户id
     * @param authCode    验证码
     * @param roleIds     角色ids
     * @param positionIds 职位ids
     * @return 注册成功的用户
     */
    @Override
    public User registerProcess(String telephone, String authCode, List<String> roleIds, List<String> positionIds) {
        if (!RegexUtil.checkPhone(telephone) || authCode.length() != 6)
            throw new BusinessVerifyFailedException("telephone 或 authCode 非法");
        UserQueryCondition condition = new UserQueryCondition();
        condition.setIsEnable(false);
        condition.setState(UserStateConst.REGISTERING.ordinal());
        condition.setTelephone(telephone);
        List<User> search = this.search(condition);
        if (search.isEmpty())
            throw new BusinessVerifyFailedException("无该手机号相关的用户注册信息");
        User user = search.get(0);

        // 如果 账户因验证码错误次数过多而锁定 当超过 AUTH_CODE_SEND_INTERVAL 后 自动解锁
        if (user.getLockStatus() == UserLockStatusConst.LOCKED.ordinal()) {
            Date now = new Date();
            if ((now.getTime() - user.getAuthCodeLastSendAt().getTime()) / 1000 > AUTH_CODE_SEND_INTERVAL) {
                user.setLockCount(0);
                user.setAuthCodeLastSendAt(now);
                user.setLockStatus(UserLockStatusConst.UNLOCK.ordinal());
            } else {
                throw new BusinessVerifyFailedException("验证码错误次数过多, 已被暂时锁定, 请稍后再试");
            }
        }
        // 如果验证码错误次数超过限制 则会锁定注册账户
        if (!authCode.equals(user.getAuthCode())) {
            user.setLockCount(user.getLockCount() + 1);
            if (user.getLockCount() >= MAX_PASSWORD_TRY_COUNT)
                user.setLockStatus(UserLockStatusConst.LOCKED.ordinal());
            this.update(user);
            throw new BusinessVerifyFailedException("验证码错误");
        } else {
            // 验证码匹配 开始用户注册逻辑
            // 删除现有用户
            this.delete(user);
            user.setIsEnable(true);
            user.setLockCount(0);
            user.setLockStatus(UserLockStatusConst.UNLOCK.ordinal());
            user.setAuthCode("");
            // 重新创建用户
            this.entry(user, roleIds, positionIds, user, (entryUser, entryOperator, company, department, password) -> {
                // 用户注册成功
                String userPrefix = Objects.isNull(department) ? company.getName() : department.getName();
                if (StringUtils.isNotBlank(entryUser.getTelephone())){
                    SendForUser sendForUser = new SendForUser();
                    sendForUser.setCode(SmsTemplateConst.REGISTER_SUCCESS.getCode());
                    sendForUser.setSignature(SmsSignatureConst.RUIYANG_MEDICAL);
                    sendForUser.setUser(entryUser);
                    sendForUser.setParams(SmsTemplateConst.REGISTER_SUCCESS.getParams(String.format("%s-%s", userPrefix, entryUser.getName()), password));
                    smsSendFeignClient.sendForUser(sendForUser);
                }
            });
        }
        return user;
    }

    /**
     * 用户注册 部门, 角色, 职位关联
     * @param user 用户
     * @param roleIds 角色id
     * @param positionIds 职位关连
     * @param operator 操作人员
     * @param entryCallback 执行完后的回调
     * @return 注册后的用户
     */
    private User entry(User user, List<String> roleIds, List<String> positionIds, User operator, EntryCallback entryCallback) {
        // 前置条件验证
        if (StringUtils.isBlank(user.getCompanyId()))
            throw new BusinessVerifyFailedException("companyId 不能为空");
        Department department = null;
        Company company = null;
        if (StringUtils.isNotBlank(user.getDepartmentId())) {
            department = departmentMapper.selectByPK(user.getDepartmentId());
            if (Objects.isNull(department) || !department.getIsEnable())
                throw new BusinessVerifyFailedException("departmentId 非法");
        } else {
            company = companyMapper.selectByPK(user.getCompanyId());
        }
        // 初始化密码
        String password = PasswordGenerator.get(8, 3);
        user.setPassword(DigestUtils.sha256Hex(password));
        this.create(user);

        // 角色绑定
        // TODO: 验证角色的派生关系, 防止越级分配权限
        roleService.bindUser(user.getId(), roleIds);

        if (Objects.nonNull(department)) {
            // 部门关联
            ReUserDepartment reUserDepartment = new ReUserDepartment();
            reUserDepartment.setCreatorId(operator.getId());
            reUserDepartment.setCompanyId(user.getCompanyId());
            reUserDepartment.setDepartmentId(user.getDepartmentId());
            reUserDepartment.setUserId(user.getId());
            reUserDepartment.setEntryRemark(String.format("由用户: %s 操作入职", operator.getName()));
            reUserDepartmentService.create(reUserDepartment);

            // 职位绑定
            for (String positionId : positionIds) {
                positionService.allot(user.getId(), department.getId(), positionId, operator);
            }
        }

        if (Objects.nonNull(entryCallback)) {
            entryCallback.run(user, operator, company, department, password);
        }
        return user;
    }


    /**
     * 员工入职
     *
     * @param user        用户(公司 部门id, 手机姓名邮箱等信息)
     * @param roleIds     需要绑定的角色id
     * @param positionIds 需要绑定的职位id
     * @param operator    操作人员
     */
    @Override @Transactional
    public User entry(User user, List<String> roleIds, List<String> positionIds, User operator) {
        return this.entry(user, roleIds, positionIds, operator, (entryUser, entryOperator, company, department, password) -> {
            // 通知该用户
            String userPrefix = Objects.isNull(department) ? company.getName() : department.getName();
            if (StringUtils.isNotBlank(entryUser.getTelephone())){
                SendForUser sendForUser = new SendForUser();
                sendForUser.setCode(SmsTemplateConst.EMPLOYEE_ENTRY.getCode());
                sendForUser.setSignature(SmsSignatureConst.RUIYANG_MEDICAL);
                sendForUser.setUser(entryUser);
                sendForUser.setParams(SmsTemplateConst.EMPLOYEE_ENTRY.getParams(String.format("%s-%s", userPrefix, entryUser.getName()), password));
                // TODO: 测试用户添加
                // smsSendFeignClient.sendForUser(sendForUser);
                log.info("短信模拟发送: {}", JSON.toJSONString(sendForUser));
            }
            if (StringUtils.isNotBlank(entryUser.getEmail())){
                SendHtmlTemplate sendHtmlTemplate = new SendHtmlTemplate();
                sendHtmlTemplate.setReceiver(entryUser.getEmail());
                sendHtmlTemplate.setSubject("账户创建通知");
                sendHtmlTemplate.setCode(HtmlTemplateTypeConst.EMPLOYEE_ENTRY.name());
                Map<String, String> args = new HashMap<>();
                args.put("userName", entryUser.getName());
                args.put("departmentName", userPrefix);
                args.put("email", entryUser.getEmail());
                args.put("telephone", entryUser.getTelephone());
                args.put("password", password);
                sendHtmlTemplate.setArgs(args);
                // TODO: 测试用户添加
                 emailSendFeignClient.forUserUseTemplate(sendHtmlTemplate);
                log.info("邮件模拟发送: {}", JSON.toJSONString(sendHtmlTemplate));
            }
        });
    }

    private List<ReUserDepartment> selectReUserDepartments(String userId, String companyId, String departmentId){
        ReUserDepartmentQueryCondition condition = new ReUserDepartmentQueryCondition();
        condition.setIsEnable(true);
        condition.setUserId(userId);
        condition.setCompanyId(companyId);
        if (StringUtils.isNotBlank(departmentId))
            condition.setDepartmentId(departmentId);
        return reUserDepartmentService.search(condition);
    }

    /**
     * 员工离职
     * 禁用用户, 锁定用户, 全部职位自动离职, 全部角色均禁用掉
     *
     * @param userId   用户id
     * @param operator 操作人员
     */
    @Override @Transactional
    public void leave(String userId, String remark, User operator) {
        User user = this.select(userId);
        if (Objects.isNull(user) || !user.getIsEnable())
            throw new BusinessVerifyFailedException("userId 非法 或 状态异常");
        // 创建离职记录
        List<ReUserDepartment> reUserDepartments = this.selectReUserDepartments(userId, user.getCompanyId(), null);
        reUserDepartments.forEach(reUserDepartment -> {
            reUserDepartment.setLeaveRemark(remark);
            reUserDepartment.setIsEnable(false);
            reUserDepartmentService.update(reUserDepartment);
        });
        // 更新用户信息
        user.setLockStatus(UserLockStatusConst.LOCKED.ordinal());
        user.setIsEnable(false);
        user.setState(UserStateConst.LEAVE.ordinal());
        // 删除默认部门id
        user.setDepartmentId(null);
        userMapper.updateByPK(user);
        // 处理职位离职
        positionService.takeBackCompany(userId, user.getCompanyId(), operator);
        // 处理角色禁用
        ReUserRoleQueryCondition condition = new ReUserRoleQueryCondition();
        condition.setIsEnable(true);
        condition.setUserId(userId);
        List<ReUserRole> reUserRoles = reUserRoleMapper.selectByCondition(condition);
        for (ReUserRole reUserRole : reUserRoles) {
            reUserRoleMapper.disable(reUserRole.getRoleId());
        }
        // TODO: 处理工程师信息禁用 设备管理维护权限等禁用
    }

    /**
     * 用户加入部门
     * @param userId 用户id
     * @param remark 加入部门原因
     * @param departmentId 需要加入部门id
     * @param positionIds 加入部门拥有的职位
     * @param operator 操作人员
     */
    @Override @Transactional
    public void joinDepartment(String userId, String remark, String departmentId, List<String> positionIds, User operator) {
        User user = this.select(userId);
        if (Objects.isNull(user) || !user.getIsEnable())
            throw new BusinessVerifyFailedException("userId 非法 或 状态异常");
        // 如果用户首次加入部门, 将其设置为默认部门id
        if (StringUtils.isBlank(user.getDepartmentId())){
            user.setDepartmentId(departmentId);
            this.update(user);
        }
        // 创建部门入职记录
        ReUserDepartment leaveOldDepartment = new ReUserDepartment();
        leaveOldDepartment.setCreatorId(operator.getId());
        leaveOldDepartment.setCompanyId(user.getCompanyId());
        leaveOldDepartment.setDepartmentId(departmentId);
        leaveOldDepartment.setUserId(userId);
        leaveOldDepartment.setEntryRemark(remark);
        reUserDepartmentService.create(leaveOldDepartment);
        // 处理新部门职位入职
        for (String positionId : positionIds) {
            positionService.allot(userId, departmentId, positionId, operator);
        }
    }

    /**
     * 用户离开部门
     * @param userId 用户id
     * @param remark 离开部门原因
     * @param departmentId 离开部门id
     * @param operator 操作人员
     */
    @Override @Transactional
    public void leaveDepartment(String userId, String remark, String departmentId, User operator) {
        User user = this.select(userId);
        if (Objects.isNull(user) || !user.getIsEnable())
            throw new BusinessVerifyFailedException("userId 非法 或 状态异常");
        // 检查是否需要重置 默认部门id
        if (departmentId.equals(user.getDepartmentId())){
            List<Department> departments = departmentMapper.userDepartments(userId);
            List<Department> collect = departments.stream().filter(d -> !d.getId().equals(departmentId)).collect(Collectors.toList());
            // 存在多个部门 重置部门id
            if (collect.size() > 0) {
                user.setDepartmentId(collect.get(0).getId());
            } else {
                user.setDepartmentId(null);
            }
            userMapper.updateByPK(user);
        }
        // 删除部门关联
        List<ReUserDepartment> reUserDepartments = this.selectReUserDepartments(userId, user.getCompanyId(), departmentId);
        reUserDepartments.forEach(reUserDepartment -> {
            reUserDepartment.setLeaveRemark(remark);
            reUserDepartment.setIsEnable(false);
            reUserDepartmentService.update(reUserDepartment);
        });
        // 处理原部门职位离职
        positionService.takeBackDepartment(userId, departmentId, operator);
    }

    /**
     * 请求忘记密码
     * 发送短信给用户
     *
     * @param type ID类型
     * @param id ID
     */
    @Override @Transactional
    public void forgetPasswordRequest(Integer type, String id) {
        User user = getUserFromTypeAndId(type, id);
        // 检查上次验证码发送时间 间隔2小时才可再次发送
        Date now = new Date();
        if ((now.getTime() - user.getAuthCodeLastSendAt().getTime()) / 1000 > AUTH_CODE_SEND_INTERVAL){
            String authCode = PasswordGenerator.get(6, 1);
            user.setAuthCode(authCode);
            user.setAuthCodeLastSendAt(now);
            this.update(user);
            // 短信通知
            if (RegexUtil.checkPhone(user.getTelephone())){
                SendForUser sendForUser = new SendForUser();
                sendForUser.setCode(SmsTemplateConst.FORGET_PASSWORD.getCode());
                sendForUser.setSignature(SmsSignatureConst.RUIYANG_MEDICAL);
                sendForUser.setUser(user);
                sendForUser.setParams(SmsTemplateConst.FORGET_PASSWORD.getParams(authCode));
                smsSendFeignClient.sendForUser(sendForUser);
            }
            // 邮件通知
            if (RegexUtil.checkEmail(user.getEmail())){
                SendHtmlTemplate sendHtmlTemplate = new SendHtmlTemplate();
                sendHtmlTemplate.setReceiver(user.getEmail());
                sendHtmlTemplate.setSubject("忘记密码操作的验证码");
                sendHtmlTemplate.setCode(HtmlTemplateTypeConst.FORGET_PASSWORD.name());
                Map<String, String> args = new HashMap<>();
                args.put("userName", user.getName());
                args.put("authCode", authCode);
                sendHtmlTemplate.setArgs(args);
                emailSendFeignClient.forUserUseTemplate(sendHtmlTemplate);
            }
        } else
            throw new BusinessVerifyFailedException("验证码发送时间间隔最短为两小时, 请勿重复操作");
    }

    /**
     * 处理忘记密码
     * 发送短信给用户
     *
     * @param type ID类型
     * @param id ID
     * @param authCode 验证码
     * @param newPassword 新密码
     */
    @Override
    public void forgetPasswordProcess(Integer type, String id, String authCode, String newPassword) {
        User user = getUserFromTypeAndId(type, id);
        if (StringUtils.isBlank(user.getAuthCode()))
            throw new BusinessVerifyFailedException("无验证码信息或已失效, 请获取验证码后再进行操作");
        // 验证码符合要求
        if (user.getAuthCode().equals(authCode)){
            user.setPassword(parsePassword(newPassword));
            user.setLockCount(0);
            user.setLockStatus(UserLockStatusConst.UNLOCK.ordinal());
            user.setAuthCode("");
            this.update(user);
        }
        // 验证码不匹配
        else {
            user.setLockCount(user.getLockCount() + 1);
            if (user.getLockCount() >= 15) {
                // 错误次数过多重置验证码
                user.setAuthCode("");
                user.setLockCount(MAX_PASSWORD_TRY_COUNT);
                this.update(user);
                throw new BusinessVerifyFailedException("验证码错误次数过多已被重置, 请重新获取验证码");
            } else {
                this.update(user);
                throw new BusinessVerifyFailedException("验证码不匹配, 请确认短信签名是否为[%s]", SmsSignatureConst.RUIYANG_MEDICAL.getSignature());
            }
        }
    }

    /**
     * 重置密码
     * 只能由用户管理里进行重置
     *
     * @param userId 用户id
     */
    @Override @Transactional
    public void resetPassword(String userId) {
        User user = this.select(userId);
        if (Objects.isNull(user) || !user.getIsEnable())
            throw new BusinessVerifyFailedException("userId 非法 或 该用户已被禁用");
        String password = PasswordGenerator.get(8, 3);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setLockCount(0);
        user.setLockStatus(UserLockStatusConst.UNLOCK.ordinal());
        this.update(user);
        if (StringUtils.isNotBlank(user.getTelephone())){
            SendForUser sendForUser = new SendForUser();
            sendForUser.setCode(SmsTemplateConst.RESET_PASSWORD.getCode());
            sendForUser.setSignature(SmsSignatureConst.RUIYANG_MEDICAL);
            sendForUser.setUser(user);
            sendForUser.setParams(SmsTemplateConst.RESET_PASSWORD.getParams(password));
            smsSendFeignClient.sendForUser(sendForUser);
        }
        if (StringUtils.isNotBlank(user.getEmail())){
            SendHtmlTemplate sendHtmlTemplate = new SendHtmlTemplate();
            sendHtmlTemplate.setReceiver(user.getEmail());
            sendHtmlTemplate.setSubject("密码重置通知");
            sendHtmlTemplate.setCode(HtmlTemplateTypeConst.RESET_PASSWORD.name());
            Map<String, String> args = new HashMap<>();
            args.put("userName", user.getName());
            args.put("email", user.getEmail());
            args.put("telephone", user.getTelephone());
            args.put("password", password);
            sendHtmlTemplate.setArgs(args);
            emailSendFeignClient.forUserUseTemplate(sendHtmlTemplate);
        }
    }

    /**
     * 取消锁定
     *
     * @param userId 用户id
     */
    @Override
    public void cancelLock(String userId) {
        User user = this.select(userId);
        if (Objects.isNull(user) || !user.getIsEnable() || user.getLockStatus() != UserLockStatusConst.LOCKED.ordinal())
            throw new BusinessVerifyFailedException("userId 非法 或 该用户已被禁用或已解除锁定 ");
        user.setLockCount(0);
        user.setLockStatus(UserLockStatusConst.UNLOCK.ordinal());
        this.update(user);
        if (StringUtils.isNotBlank(user.getEmail())){
            SendHtmlTemplate sendHtmlTemplate = new SendHtmlTemplate();
            sendHtmlTemplate.setReceiver(user.getEmail());
            sendHtmlTemplate.setSubject("账户解除锁定通知");
            sendHtmlTemplate.setCode(HtmlTemplateTypeConst.UN_LOCK_ACCOUNT.name());
            Map<String, String> args = new HashMap<>();
            args.put("userName", user.getName());
            sendHtmlTemplate.setArgs(args);
            emailSendFeignClient.forUserUseTemplate(sendHtmlTemplate);
        }
    }

    /**
     * 修改密码
     * 只能使用者自己修改
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param user 当前用户
     */
    @Override
    public void changePassword(String oldPassword, String newPassword, User user) {
        User select = this.select(user.getId());
        if (Objects.isNull(select) || select.getLockStatus() == UserLockStatusConst.LOCKED.ordinal())
            throw new BusinessVerifyFailedException("user 非法");
        if (!select.getPassword().equals(parsePassword(oldPassword)))
            throw new BusinessVerifyFailedException("密码错误");
        select.setPassword(parsePassword(newPassword));
        this.update(select);
    }

    /**
     * 密码方式登陆获取用户
     *
     * @param id    邮箱 / 手机号
     * @param password 密码
     * @return 用户
     */
    @Override
    public User passwordLogin(String id, String password) {
        String loginId = parseId(id);
        UserQueryCondition condition = new UserQueryCondition();
        condition.setIsEnable(true);
        condition.setLockStatus(UserLockStatusConst.UNLOCK.ordinal());
        // 根据 id 格式查询用户
        if (RegexUtil.checkEmail(loginId))
            condition.setEmail(loginId);
        else if (RegexUtil.checkPhone(loginId))
            condition.setTelephone(loginId);
        else
            throw new UserAuthCheckFailedException("ID 不是合法的邮箱或手机号");
        List<User> search = this.search(condition);
        if (Objects.isNull(search) || search.size() != 1)
            throw new UserAuthCheckFailedException("ID 非法 或 账号已锁定");
        User user = search.get(0);
        // 比对密码信息
        if (user.getPassword().equals(parsePassword(password))){
            user.setLockCount(0);
            this.update(user);
            return user;
        } else {
            user.setLockCount(user.getLockCount() + 1);
            // 密码错误次数超过10次锁定账户
            if (user.getLockCount() >= MAX_PASSWORD_TRY_COUNT){
                user.setLockStatus(UserLockStatusConst.LOCKED.ordinal());
                this.update(user);
                // 短信通知
                if (StringUtils.isNotBlank(user.getTelephone())){
                    SendForUser sendForUser = new SendForUser();
                    sendForUser.setCode(SmsTemplateConst.LOCK_ACCOUNT.getCode());
                    sendForUser.setSignature(SmsSignatureConst.RUIYANG_MEDICAL);
                    sendForUser.setUser(user);
                    sendForUser.setParams(SmsTemplateConst.LOCK_ACCOUNT.getParams(""+MAX_PASSWORD_TRY_COUNT));
                    smsSendFeignClient.sendForUser(sendForUser);
                }
                // 邮件通知
                if (StringUtils.isNotBlank(user.getEmail())){
                    SendHtmlTemplate sendHtmlTemplate = new SendHtmlTemplate();
                    sendHtmlTemplate.setReceiver(user.getEmail());
                    sendHtmlTemplate.setSubject("账户锁定通知");
                    sendHtmlTemplate.setCode(HtmlTemplateTypeConst.LOCK_ACCOUNT.name());
                    Map<String, Object> args = new HashMap<>();
                    args.put("userName", user.getName());
                    args.put("count", MAX_PASSWORD_TRY_COUNT);
                    sendHtmlTemplate.setArgs(args);
                    emailSendFeignClient.forUserUseTemplate(sendHtmlTemplate);
                }
                throw new UserAuthCheckFailedException("密码错误次数超过%s次, 账户已锁定", MAX_PASSWORD_TRY_COUNT);
            } else {
                this.update(user);
                throw new UserAuthCheckFailedException("密码错误, 请重新输入密码");
            }
        }
    }

    /**
     * 角色创建者
     *
     * @param role 角色
     * @return 用户
     */
    @Override
    public User roleCreator(Role role) throws DbOperationFailedException {
        return this.select(role.getCreatorId());
    }

    /**
     * 微信信息关联的用户
     *
     * @param wechatInfo 微信信息
     * @return 用户
     */
    @Override
    public User wechatInfoOwner(WechatInfo wechatInfo) throws DbOperationFailedException {
        return this.select(wechatInfo.getUserId());
    }

    /**
     * 根据条件查询用户信息 分页
     *
     * @param condition 条件
     * @return 用户详细信息
     */
    @Override
    public List<UserInfo> searchInfo(UserQueryCondition condition) {
        List<UserInfo> userInfos = PaginateUtil.page(() -> userMapper.searchInfo(condition));
        userInfos.forEach(userInfo -> {
            userInfo.setDepartments(departmentMapper.userDepartments(userInfo.getId()));
            userInfo.setRoles(roleMapper.selectByUserId(userInfo.getId()));
            userInfo.setPositions(positionMapper.userPositionInfo(userInfo.getId()));
        });
        return userInfos;
    }

    /**
     * 生成用户的认证结果信息
     *
     * @param user        用户
     * @param clientType  客户端类型
     * @param accessToken 令牌
     * @return 认证结果
     */
    @Override
    public AuthResult generateAuthResult(User user, ClientTypeConst clientType, Token accessToken) {
        AuthResult authResult = new AuthResult();
        authResult.setAccessToken(accessToken);
        List<Permission> permissions =  permissionService.userPermissions(user.getId());
        List<Role> roles = roleService.userRoles(user.getId());
        if (Objects.isNull(permissions))
            permissions = Collections.emptyList();
        if (Objects.isNull(roles))
            roles = Collections.emptyList();
        authResult.setPermissionCodeList(permissions.stream().map(Permission::getCode).collect(Collectors.toSet()));
        authResult.setRoleCodeList(roles.stream().map(Role::getCode).collect(Collectors.toSet()));
        if (Objects.nonNull(user.getCompanyId()) && Objects.nonNull(user.getDepartmentId())){
            Company company = companyMapper.selectByPK(user.getCompanyId());
            authResult.setCompany(company);
            // 设置关联部门
            authResult.setDepartments(new HashSet<>(departmentMapper.userDepartments(user.getId())));
            List<Position> positions = positionService.userPositions(user.getId());
            authResult.setPositionNameList(positions.stream().map(Position::getName).collect(Collectors.toSet()));
            // 更新公司的配置文件信息
            if (Objects.nonNull(company))
                company.setConfigJson(JSON.toJSONString(CompanyConfigConst.parseConfigJson(company.getConfigJson(), CompanyTypeConst.get(company.getType()))));
        }
        // 获取微信小程序用户信息
        WechatAppUserQueryCondition wechatAppUserQueryCondition = new WechatAppUserQueryCondition();
        wechatAppUserQueryCondition.setUserId(user.getId());
        wechatAppUserQueryCondition.setIsEnable(true);
        List<WechatAppUser> wechatAppUsers = wechatAppUserMapper.selectByCondition(wechatAppUserQueryCondition);
        if (Objects.nonNull(wechatAppUsers) && !wechatAppUsers.isEmpty())
            authResult.setWechatAppUser(wechatAppUsers.get(0));
        // 检查是否是工程师
        EngineerQueryCondition engineerQueryCondition = new EngineerQueryCondition();
        engineerQueryCondition.setIsEnable(true);
        engineerQueryCondition.setUserId(user.getId());
        authResult.setEngineerInfoList(new HashSet<>(engineerFeignClient.search(engineerQueryCondition).getData()));
        return authResult;
    }

    /**
     * 查询部门用户的联系方式等基本信息
     *
     * @param companyId    公司id
     * @param departmentId 部门id
     * @return 用户基本信息
     */
    @Override
    public List<ContactInfo> contacts(String companyId, String departmentId) {
        return userMapper.contacts(companyId, departmentId);
    }
}
