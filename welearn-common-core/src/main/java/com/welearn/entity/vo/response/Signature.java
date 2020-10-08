package com.welearn.entity.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.welearn.dictionary.error.ErrorMessageConst;
import com.welearn.dictionary.error.ErrorStatusConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 响应签名
 */
@Data
@ApiModel(description = "响应签名")
public class Signature implements Serializable {
    @ApiModelProperty(value = "响应状态|0:错误响应,1:正确响应", required = true, position = -1)
    private Integer status;
    @ApiModelProperty(value = "异常类型|0:系统异常,1:数据库异常,2:网络异常,3:认证异常,4:参数异常,5:业务异常", allowEmptyValue = true)
    private Integer errorType;
    @ApiModelProperty(value = "异常编码", allowEmptyValue = true)
    private String  errorCode;
    @ApiModelProperty(value = "异常描述", allowEmptyValue = true)
    private String  errorMessage;
    @ApiModelProperty(value = "异常详情", allowEmptyValue = true)
    private Map<String, Object> errorDetail;

    public Signature(ErrorMessageConst message){
        this.status = ErrorStatusConst.FAILED.ordinal();
        this.errorType = message.getType().ordinal();
        this.errorCode = message.getCode();
        this.errorMessage = message.getMessage();
    }

    public Signature(ErrorMessageConst message, String errorMessage){
        this.status = ErrorStatusConst.FAILED.ordinal();
        this.errorType = message.getType().ordinal();
        this.errorCode = message.getCode();
        this.errorMessage = errorMessage;
    }

    public Map<String, Object> setException(Exception exception){
        Map<String, Object> errorDetail = new HashMap<>();
        errorDetail.put("cause", exception.getCause());
        errorDetail.put("localizedMessage", exception.getLocalizedMessage());
        errorDetail.put("message", exception.getMessage());
        errorDetail.put("stackTrace", exception.getStackTrace());
        errorDetail.put("suppressed", exception.getSuppressed());
        errorDetail.put("class", exception.getClass());
        this.setErrorDetail(errorDetail);
        return errorDetail;
    }

    public Signature(){
        this.status = ErrorStatusConst.SUCCESS.ordinal();
    }

    public CommonResponse toErrorResponse(){
        return new CommonResponse(this);
    }

    public CommonResponse toErrorResponse(String errorMessage){
        this.setErrorMessage(errorMessage);
        return toErrorResponse();
    }

    /**
     * 是否是成功响应
     * @return 是否成功
     */
    @JsonIgnore
    public Boolean isSuccessResponse(){
        return this.status.equals(ErrorStatusConst.SUCCESS.ordinal());
    }
}