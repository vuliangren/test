package com.welearn.util;

import com.alibaba.fastjson.JSON;
import com.welearn.cache.getter.AuthorizationCacheGetter;
import com.welearn.dictionary.common.RequestHeaderConst;
import com.welearn.entity.po.common.Department;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.error.exception.ProgramCheckFailedException;
import com.welearn.error.exception.UserAuthCheckFailedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.welearn.dictionary.common.RequestHeaderConst.AUTH_REUSLT;
import static com.welearn.dictionary.common.RequestHeaderConst.VEDA_DEPARTMENT_ID;

public class GlobalContextUtil {

    /**
     * 获取当前处理的请求
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest(){
        try {
            return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (IllegalStateException ex){
            throw new ProgramCheckFailedException("Request is illegal, may be the request is from system inside");
        }
    }

    /**
     * 获取当前请求的 认证结果
     * 对于内部定时任务发起的请求 无法获取当前请求 及认证结果
     * 需要对可能无法获取到认证结果的情况进行处理 异常
     * @return RequestToken
     */
    public static AuthResult getAuthResult() throws Exception{
        HttpServletRequest request = getRequest();
        // 防止重复获取 AuthResult 故而缓存起来
        Object authResultObject = request.getAttribute("_AuthResult");
        if (Objects.nonNull(authResultObject))
            return (AuthResult)authResultObject;
        // 获取缓存读取器实例
        AuthorizationCacheGetter authorizationCacheGetter = SpringContextUtil.getBean(AuthorizationCacheGetter.class);
        AuthResult authResult = authorizationCacheGetter.getAuthCheckCache(getAuthResultHeader());
        if (Objects.isNull(authResult))
            throw new UserAuthCheckFailedException("AuthResult parse object failed");
        // 更新 authResult 内的默认部门信息
        try {
            String currentDepartmentId = getCurrentDepartmentId();
            authResult.getAccessToken().getUser().setDepartmentId(currentDepartmentId);
            authResult.setDepartment(authResult.getDepartments().stream().filter(department -> department.getId().equals(currentDepartmentId)).findFirst().get());
        } catch (Exception e) {
            throw new UserAuthCheckFailedException("user default departmentId: 非法");
        }
        // 将验证结果对象 缓存在本地请求 中
        request.setAttribute("_AuthResult", authResult);
        return authResult;
    }

    /**
     * 获取 AuthResult JSON
     * @return AuthResult JSON String
     */
    public static String getAuthResultHeader(){
        HttpServletRequest request = getRequest();
        // 先检查 请求头中是否有 Auth-Result 请求头
        String authResultHeader = request.getHeader(AUTH_REUSLT.getHeaderName());
        if (StringUtils.isBlank(authResultHeader))
            throw new UserAuthCheckFailedException("Can not found Auth-Result in request");
        return authResultHeader;
    }

    /**
     * 获取 用户当前部门 id
     * @return currentDepartmentId String 36 位
     */
    public static String getCurrentDepartmentId(){
        HttpServletRequest request = getRequest();
        // 先检查 请求头中是否有 Auth-Result 请求头
        String currentDepartmentId = request.getHeader(VEDA_DEPARTMENT_ID.getHeaderName());
        if (StringUtils.isBlank(currentDepartmentId))
            throw new UserAuthCheckFailedException("Can not found Veda-DepartmentId in request");
        return currentDepartmentId;
    }

    /**
     * 获取 ACCESS-TOKEN
     * @return ACCESS-TOKEN String
     */
    public static String getVedaToken(){
        return getRequest().getHeader(RequestHeaderConst.VEDA_TOKEN.getHeaderName());
    }
}
