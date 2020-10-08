package com.welearn.controller;

import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.storage.QiniuFileInfo;
import com.welearn.entity.vo.request.storage.UploadToken;
import com.welearn.service.QiniuObjectStoreService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Description : 开放接口控制器 允许未认证访问
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/public")
public class PublicController {

    @Autowired
    private QiniuObjectStoreService qiniuObjectStoreService;

    @RequestMapping(value = "/qiniuFileUploadToken")
    @ApiOperation(value = "获取文件上传凭证", httpMethod = "POST")
    public CommonResponse<String> uploadToken(@RequestBody UploadToken uploadToken){
        String token = qiniuObjectStoreService.uploadToken(uploadToken.getIsPublic(), false, uploadToken.getUserId(), uploadToken.getSuffix());
        return new CommonResponse<>(token);
    }

    @RequestMapping(value = "/qiniuFileUploadCallback")
    @ApiOperation(value = "文件上传业务回调", httpMethod = "POST")
    public CommonResponse<QiniuFileInfo> uploadCallback(HttpServletRequest request) throws IOException {
        QiniuFileInfo fileInfo = qiniuObjectStoreService.uploadCallback(request);
        return new CommonResponse<>(fileInfo);
    }
}