package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.po.common.Department;
import com.welearn.entity.vo.request.common.*;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.common.ContactInfo;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.DepartmentService;
import com.welearn.service.ReUserDepartmentService;
import com.welearn.service.SupplierRegisterService;
import com.welearn.util.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.Role;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.wechat.WechatInfo;
import com.welearn.entity.qo.common.UserQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.common.UserInfo;
import com.welearn.error.exception.DbOperationFailedException;
import com.welearn.service.UserService;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/user")
public class UserController extends BaseController<User, UserQueryCondition, UserService>{

    @RequestMapping(value = "/entry")
    @ApiOperation(value = "账户登记", httpMethod = "POST")
    public CommonResponse<User> entry(@RequestBody Entry entry, @RequestUser User user)  {
        User result = service.entry(entry.getUser(), entry.getRoleIds(), entry.getPositionIds(), user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/leave")
    @ApiOperation(value = "用户离职", httpMethod = "POST")
    public CommonResponse leave(@RequestBody Leave leave, @RequestUser User user)  {
        service.leave(leave.getUserId(), leave.getRemark(), user);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/roleCreator")
    @ApiOperation(value = "roleCreator", httpMethod = "POST")
    public CommonResponse<User> roleCreator(@RequestBody Role role) throws DbOperationFailedException {
        User result = service.roleCreator(role);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/passwordLogin")
    @ApiOperation(value = "账户密码方式登录", httpMethod = "POST")
    public CommonResponse<User> passwordLogin(@RequestBody PasswordLogin passwordLogin)  {
        User result = service.passwordLogin(passwordLogin.getEmail(), passwordLogin.getPassword());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/wechatInfoOwner")
    @ApiOperation(value = "查询微信账户信息关联用户", httpMethod = "POST")
    public CommonResponse<User> wechatInfoOwner(@RequestBody WechatInfo wechatInfo) throws DbOperationFailedException {
        User result = service.wechatInfoOwner(wechatInfo);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "searchInfo", httpMethod = "POST")
    public CommonResponse<List<UserInfo>> searchInfo(@RequestBody UserQueryCondition userQueryCondition)  {
        List<UserInfo> result = service.searchInfo(userQueryCondition);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/changePassword")
    @ApiOperation(value = "修改账户密码", httpMethod = "POST")
    public CommonResponse changePassword(@RequestBody ChangePassword changePassword, @RequestUser User user)  {
        service.changePassword(changePassword.getOldPassword(), changePassword.getNewPassword(), user);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/resetPassword")
    @ApiOperation(value = "重置用户密码", httpMethod = "POST")
    public CommonResponse resetPassword(@RequestBody String string)  {
        service.resetPassword(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/cancelLock")
    @ApiOperation(value = "解除账户锁定", httpMethod = "POST")
    public CommonResponse cancelLock(@RequestBody String string)  {
        service.cancelLock(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/forgetPasswordRequest")
    @ApiOperation(value = "发起忘记密码请求", httpMethod = "POST")
    public CommonResponse forgetPasswordRequest(@RequestBody ForgetPasswordRequest forgetPasswordRequest)  {
        service.forgetPasswordRequest(forgetPasswordRequest.getType(), forgetPasswordRequest.getId());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/forgetPasswordProcess")
    @ApiOperation(value = "处理忘记密码流程", httpMethod = "POST")
    public CommonResponse forgetPasswordProcess(@RequestBody ForgetPasswordProcess forgetPasswordProcess)  {
        service.forgetPasswordProcess(forgetPasswordProcess.getType(), forgetPasswordProcess.getId(), forgetPasswordProcess.getAuthCode(), forgetPasswordProcess.getNewPassword());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/generateAuthResult")
    @ApiOperation(value = "generateAuthResult", httpMethod = "POST")
    public CommonResponse<AuthResult> generateAuthResult(@RequestBody GenerateAuthResult generateAuthResult)  {
        AuthResult result = service.generateAuthResult(generateAuthResult.getUser(), ClientTypeConst.values()[generateAuthResult.getClientType()], generateAuthResult.getAccessToken());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/leaveDepartment")
    @ApiOperation(value = "leaveDepartment", httpMethod = "POST")
    public CommonResponse leaveDepartment(@RequestBody LeaveDepartment leaveDepartment, @RequestUser User user)  {
        service.leaveDepartment(leaveDepartment.getUserId(), leaveDepartment.getRemark(), leaveDepartment.getDepartmentId(), user);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/joinDepartment")
    @ApiOperation(value = "joinDepartment", httpMethod = "POST")
    public CommonResponse joinDepartment(@RequestBody JoinDepartment joinDepartment, @RequestUser User user)  {
        service.joinDepartment(joinDepartment.getUserId(), joinDepartment.getRemark(), joinDepartment.getDepartmentId(), joinDepartment.getPositionIds(), user);
        return CommonResponse.getSuccessResponse();
    }


    @RequestMapping(value = "/registerRequest")
    @ApiOperation(value = "registerRequest", httpMethod = "POST")
    public CommonResponse<User> registerRequest(@RequestBody User user)  {
        User result = service.registerRequest(user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/registerProcess")
    @ApiOperation(value = "registerProcess", httpMethod = "POST")
    public CommonResponse<User> registerProcess(@RequestBody RegisterProcess registerProcess)  {
        User result = service.registerProcess(registerProcess.getTelephone(), registerProcess.getAuthCode(), registerProcess.getRoleIds(), registerProcess.getPositionIds());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/contacts")
    @ApiOperation(value = "获取用户通讯录信息", httpMethod = "POST")
    public CommonResponse<List<ContactInfo>> contacts(@RequestBody Contacts contacts)  {
        List<ContactInfo> result = service.contacts(contacts.getCompanyId(), contacts.getDepartmentId());
        return new CommonResponse<>(result);
    }
}