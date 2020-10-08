package com.welearn.controller;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.request.storage.UploadToken;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.QiniuObjectStoreService;
import com.welearn.service.SignatureRecordService;
import com.welearn.service.StatisticsRecordService;
import com.welearn.util.PasswordGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Description : 七牛对象存储接口
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/qiniuFile")
public class QiniuFileController {

    @Autowired
    private QiniuObjectStoreService qiniuObjectStoreService;

    @RequestMapping(value = "/uploadToken")
    @ApiOperation(value = "获取文件上传凭证", httpMethod = "POST")
    public CommonResponse<String> uploadToken(@RequestBody UploadToken uploadToken){
        String token = qiniuObjectStoreService.uploadToken(uploadToken.getIsPublic(), uploadToken.getIsSystemUser(), uploadToken.getUserId(), uploadToken.getSuffix());
        return new CommonResponse<>(token);
    }

    @RequestMapping(value = "/downloadUrls")
    @ApiOperation(value = "获取文件下载链接", httpMethod = "POST")
    public CommonResponse<List<String>> downloadUrl(@RequestBody List<String> fileKeys) {
        List<String> downloadUrls = qiniuObjectStoreService.downloadUrl(fileKeys);
        return new CommonResponse<>(downloadUrls);
    }

    @RequestMapping(value = "/downloadUrl")
    @ApiOperation(value = "获取文件下载链接", httpMethod = "POST")
    public CommonResponse<String> downloadUrl(@RequestBody String fileKey) throws UnsupportedEncodingException {
        String downloadUrl = qiniuObjectStoreService.downloadUrl(fileKey);
        return new CommonResponse<>(downloadUrl);
    }
}
