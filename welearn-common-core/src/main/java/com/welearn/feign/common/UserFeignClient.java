package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.vo.request.common.*;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.common.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.common.Role;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.wechat.WechatInfo;
import com.welearn.entity.qo.common.UserQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;

import java.util.List;

/**
 * Description : welearn-common-service / com.welearn.controller.UserController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface UserFeignClient {
    @RequestMapping(value = "/user/entry")
    CommonResponse entry(Entry entry);

    @RequestMapping(value = "/user/registerRequest")
    CommonResponse<User> registerRequest(User user);

    @RequestMapping(value = "/user/registerProcess")
    CommonResponse<User> registerProcess(RegisterProcess registerProcess);

    @RequestMapping(value = "/user/forgetPasswordProcess")
    CommonResponse forgetPasswordProcess(ForgetPasswordProcess forgetPasswordProcess);

    @RequestMapping(value = "/user/generateAuthResult")
    CommonResponse<AuthResult> generateAuthResult(GenerateAuthResult generateAuthResult);

    @RequestMapping(value = "/user/forgetPasswordRequest")
    CommonResponse forgetPasswordRequest(ForgetPasswordRequest forgetPasswordRequest);

    @RequestMapping(value = "/user/resetPassword")
    CommonResponse resetPassword(String string);

    @RequestMapping(value = "/user/cancelLock")
    CommonResponse cancelLock(String string);

    @RequestMapping(value = "/user/leaveDepartment")
    CommonResponse leaveDepartment(LeaveDepartment leaveDepartment);

    @RequestMapping(value = "/user/changePassword")
    CommonResponse changePassword(ChangePassword changePassword);

    @RequestMapping(value = "/user/roleCreator")
    CommonResponse<User> roleCreator(Role role);

    @RequestMapping(value = "/user/leave")
    CommonResponse leave(Leave leave);

    @RequestMapping(value = "/user/searchInfo")
    CommonResponse<List<UserInfo>> searchInfo(UserQueryCondition userQueryCondition);

    @RequestMapping(value = "/user/wechatInfoOwner")
    CommonResponse<User> wechatInfoOwner(WechatInfo wechatInfo);

    @RequestMapping(value = "/user/passwordLogin")
    CommonResponse<User> passwordLogin(PasswordLogin passwordLogin);

    @RequestMapping(value = "/user/joinDepartment")
    CommonResponse joinDepartment(JoinDepartment joinDepartment);

    @RequestMapping(value = "/user/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/user/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/user/update")
    CommonResponse update(User entity);

    @RequestMapping(value = "/user/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/user/create")
    CommonResponse<User> create(User entity);

    @RequestMapping(value = "/user/search")
    CommonResponse<List<User>> search(UserQueryCondition queryCondition);

    @RequestMapping(value = "/user/select")
    CommonResponse<User> select(String id);
}