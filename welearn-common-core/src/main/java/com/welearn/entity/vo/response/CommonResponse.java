package com.welearn.entity.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "统一的响应格式,在处理前应先检查响应签名的 status")
public class CommonResponse<T> implements Serializable{
    @ApiModelProperty(value = "响应数据")
    private T data;

    @ApiModelProperty(value = "响应签名")
    private Signature signature;

    @ApiModelProperty(value = "分页信息")
    private Pagination pagination;

    public CommonResponse(){}

    public CommonResponse(T data){
        this.data = data;
        this.signature = new Signature();
    }

    public CommonResponse(Signature signature){
        this.signature = signature;
    }

    public static CommonResponse getSuccessResponse(){
        return new CommonResponse(new Signature());
    }

    /**
     * 是否是成功响应
     * @return 是否成功
     */
    @JsonIgnore
    public Boolean isSuccessResponse(){
        return this.signature.isSuccessResponse();
    }

    public void setPagination(PageInfo pageInfo) {
        this.pagination = new Pagination(pageInfo);
    }
}
