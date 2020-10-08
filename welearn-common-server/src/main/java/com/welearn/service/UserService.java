package com.welearn.service;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.dto.authorization.Token;
import com.welearn.entity.po.common.*;
import com.welearn.entity.po.wechat.WechatInfo;
import com.welearn.entity.qo.common.UserQueryCondition;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.common.ContactInfo;
import com.welearn.entity.vo.response.common.UserInfo;
import com.welearn.error.exception.DbOperationFailedException;
import com.welearn.validate.annotation.common.EntityCheck;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : 用户业务接口
 * Created by Setsuna Jin on 2018/1/7.
 */
@Validated
public interface UserService extends BaseService<User, UserQueryCondition> {

    /**
     * 用户注册使用
     * @param user 用户信息
     * @return 保存的禁用后的用户信息
     */
    User registerRequest(@NotNull User user);

    /**
     * 用户注册检查(验证验证码)
     * @param telephone 用户手机号
     * @param authCode 验证码
     * @param roleIds 角色ids
     * @param positionIds 职位ids
     * @return 注册成功的用户
     */
    User registerProcess(@NotBlank String telephone, @NotBlank String authCode, @NotNull List<String> roleIds, @NotNull List<String> positionIds);

    /**
     * 员工入职
     * @param user 用户(公司 部门id, 手机姓名邮箱等信息)
     * @param roleIds 需要绑定的角色id
     * @param positionIds 需要绑定的职位id
     * @param operator 操作人员
     */
    User entry(@NotNull User user, @NotNull List<String> roleIds, @NotNull List<String> positionIds,
               @EntityCheck(checkId = true) User operator);

    /**
     * 员工离职
     * 禁用用户, 锁定用户, 全部职位自动离职, 全部角色均禁用掉
     * @param userId 用户id
     */
    void leave(@NotBlank String userId, @NotBlank String remark, @EntityCheck(checkId = true) User operator);

    /**
     * 用户加入部门
     * @param userId 用户id
     * @param remark 加入部门原因
     * @param departmentId 需要加入部门id
     * @param positionIds 加入部门拥有的职位
     * @param operator 操作人员
     */
    void joinDepartment(@NotBlank String userId, String remark, @NotBlank String departmentId,
                        @NotNull List<String> positionIds, @EntityCheck(checkId = true) User operator);

    /**
     * 用户离开部门
     * @param userId 用户id
     * @param remark 离开部门原因
     * @param departmentId 离开部门id
     * @param operator 操作人员
     */
    void leaveDepartment(@NotBlank String userId, String remark, @NotBlank String departmentId, @EntityCheck(checkId = true) User operator);

    /**
     * 请求忘记密码
     * 发送短信给用户
     */
    void forgetPasswordRequest(@NotNull Integer type, @NotBlank String id);

    /**
     * 处理忘记密码
     * 发送短信给用户
     */
    void forgetPasswordProcess(@NotNull Integer type, @NotBlank String id, @NotBlank String authCode, @NotBlank String newPassword);

    /**
     * 重置密码
     * 只能由用户管理里进行重置
     */
    void resetPassword(@NotBlank String userId);

    /**
     * 取消锁定
     * @param userId 用户id
     */
    void cancelLock(@NotBlank String userId);

    /**
     * 修改密码
     * 只能使用者自己修改
     */
    void changePassword(@NotBlank String oldPassword, @NotBlank String newPassword, @EntityCheck(checkId = true) User user);

    /**
     * 密码方式登陆获取用户
     * @param id 邮箱 / 手机号
     * @param password 密码
     * @return 用户
     */
    User passwordLogin(@NotBlank String id, @NotBlank String password);

    /**
     * 角色创建者
     * @param role 角色
     * @return 用户
     */
    User roleCreator(@NotNull Role role)
            throws DbOperationFailedException;

    /**
     * 微信信息关联的用户
     * @param wechatInfo 微信信息
     * @return 用户
     */
    User wechatInfoOwner(@NotNull WechatInfo wechatInfo)
            throws DbOperationFailedException;

    /**
     * 根据条件查询用户信
     * @param condition 条件
     * @return 用户详细信息
     */
    List<UserInfo> searchInfo(@NotNull UserQueryCondition condition);

    /**
     * 生成用户的认证结果信息
     * @param user 用户
     * @param clientType 客户端类型
     * @param accessToken 令牌
     * @return 认证结果
     */
    AuthResult generateAuthResult(User user, ClientTypeConst clientType, Token accessToken);

    /**
     * 查询部门用户的联系方式等基本信息
     * @param companyId 公司id
     * @param departmentId 部门id
     * @return 用户基本信息
     */
    List<ContactInfo> contacts(@NotBlank String companyId, String departmentId);
}
