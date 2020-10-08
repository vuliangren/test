package com.welearn.service;

import com.welearn.entity.po.storage.UploadRecord;
import com.welearn.entity.vo.response.storage.QiniuFileInfo;
import org.hibernate.validator.constraints.NotBlank;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Description : 七牛云存储业务
 * Created by Setsuna Jin on 2018/11/19.
 */
public interface QiniuObjectStoreService {

    /**
     * 获取上传凭证
     * @return 上传凭证
     */
    String uploadToken(Boolean isPublic, Boolean isSystemUser, String userId, String ext);

    /**
     * 文件上传回调
     * @param originAuthorization Authorization 请求头
     * @param url 回调的请求url
     * @param body 回调的请求body
     * @param contentType 回调的请求 content-type 请求头
     * @return QiniuFileInfo
     */
    QiniuFileInfo uploadCallback(@NotBlank String originAuthorization, @NotBlank String url, byte[] body,
                                 @NotBlank String contentType) throws UnsupportedEncodingException;

    /**
     * 文件上传回调
     * @param request HttpServletRequest
     * @return QiniuFileInfo
     */
    QiniuFileInfo uploadCallback(@NotNull HttpServletRequest request) throws IOException;


    /**
     * 获取下载链接
     * @param fileKey 需要下载文件的名称
     * @return 下载链接
     */
    String downloadUrl(@NotBlank String fileKey) throws UnsupportedEncodingException;

    /**
     * 获取下载链接
     * @param fileKeys 需要下载文件的名称 list
     * @return 下载链接 list
     */
    List<String> downloadUrl(@NotNull List<String> fileKeys);
}
