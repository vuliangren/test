package com.welearn.error.handler;

import com.welearn.dictionary.error.ErrorMessageConst;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.Signature;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.error.exception.UserAuthCheckFailedException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 全局Controller异常统一处理
 * @author JinRuiyang 2018/02/25
 */
@Slf4j
@ControllerAdvice
public class GlobleExceptionHandler {

    @Value("${debug}")
    private Boolean isDebug;

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    /**
     * Validate 参数验证异常处理
     * @param exception ConstraintViolationException
     * @return 统一响应
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse handle(ValidationException exception) {
        Signature signature = new Signature(ErrorMessageConst.ARGUMENT_VALID_FAILED);
        if (!isDebug)
            return signature.toErrorResponse(exception.getMessage());
        // Debug 模式下处理错误信息的显示
        Map<String, Object> errorDetail = new HashMap<>();
        if(exception instanceof ConstraintViolationException){
            String[] parameterNames = null;
            ConstraintViolationException exs = (ConstraintViolationException)exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                PathImpl path = (PathImpl)item.getPropertyPath();
                String[] paths = path.toString().split("\\.");
                Method[] methods = item.getRootBeanClass().getMethods();
                String methodName = null;
                for (Method method : methods){
                    if (method.getName().equals(paths[0])){
                        parameterNames = discoverer.getParameterNames(method);
                        methodName = method.getName();
                        break;
                    }
                }
                String key = item.getRootBeanClass().getName()+"."+methodName+"/";
                Pattern pattern = Pattern.compile("arg(.+?)");
                Matcher matcher = pattern.matcher(paths[1]);
                if (matcher.find()){
                    Integer index = Integer.parseInt(matcher.group(1));
                    if (Objects.nonNull(parameterNames))
                        key+= parameterNames[index];
                    else key += "arg"+index;
                }
                for (int i = 2; i < paths.length; i++)
                    key += "." + paths[i];
                Object error = errorDetail.get(key);
                if (error != null)
                    error += " | " + item.getMessage();
                else error = item.getMessage();
                errorDetail.put(key, error);
            }
        }
        signature.setErrorDetail(errorDetail);
        return signature.toErrorResponse(exception.getMessage());
    }

    /**
     * Validate 参数验证异常处理
     * @param exception MethodArgumentNotValidException
     * @return 统一响应
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse handle(MethodArgumentNotValidException exception){
        Signature signature = new Signature(ErrorMessageConst.ARGUMENT_VALID_FAILED);
        if (!isDebug)
            return signature.toErrorResponse(exception.getMessage());
        // Debug 模式下处理错误信息的显示
        Map<String, Object> errorDetail = new HashMap<>();
        String defaultKey = exception.getParameter().getMethod().getDeclaringClass().getName()+"."+exception.getParameter().getMethod().getName()+"/";
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for (ObjectError objectError : errors){
            String key = defaultKey + objectError.getObjectName();
            Object error = errorDetail.get(key);
            if (error != null)
                error += " | " + objectError.getDefaultMessage();
            else error = objectError.getDefaultMessage();
            errorDetail.put(key, error);
        }
        signature.setErrorDetail(errorDetail);
        return signature.toErrorResponse(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponse handle(UserAuthCheckFailedException exception){
        log.error(exception.getMessage(), exception);
        Signature signature = new Signature(ErrorMessageConst.USER_AUTH_FAILED);
        if (isDebug)
            signature.setException(exception);
        return signature.toErrorResponse(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse handle(BusinessVerifyFailedException exception){
        log.error(exception.getMessage(), exception);
        // TODO: 内部调用时直接抛异常 否则可能会影响其它服务获取数据 需要手动判断数据是否准确
        Signature signature = new Signature(ErrorMessageConst.BUSINESS_ERROR);
        if (isDebug)
            signature.setException(exception);
        return signature.toErrorResponse(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse handle(Exception exception){
        log.error(exception.getMessage(), exception);
        Signature signature = new Signature(ErrorMessageConst.SYSTEM_ERROR);
        if (isDebug)
            signature.setException(exception);
        return signature.toErrorResponse(exception.getMessage());
    }
}
