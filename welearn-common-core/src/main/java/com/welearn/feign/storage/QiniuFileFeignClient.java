package com.welearn.feign.storage;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.vo.request.storage.UploadToken;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.vo.response.CommonResponse;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-storage-service / com.welearn.controller.QiniuFileController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-storage-server", configuration = FeignConfiguration.class)
public interface QiniuFileFeignClient {

    @RequestMapping(value = "/qiniuFile/uploadToken")
    CommonResponse<String> uploadToken(UploadToken uploadToken);

    @RequestMapping(value = "/qiniuFile/downloadUrl")
    CommonResponse<String> downloadUrl(String fileKey);

    @RequestMapping(value = "/qiniuFile/downloadUrls")
    CommonResponse<List<String>> downloadUrls(List<String> fileKeys);
}