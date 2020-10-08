package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.welearn.entity.po.storage.UploadRecord;
import com.welearn.entity.qo.storage.UploadRecordQueryCondition;
import com.welearn.entity.vo.response.storage.QiniuFileInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.error.exception.TokenAuthCheckFailedException;
import com.welearn.mapper.UploadRecordMapper;
import com.welearn.service.QiniuObjectStoreService;
import com.welearn.util.GlobalContextUtil;
import com.welearn.util.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description : 七牛对象存储业务实现
 * Created by Setsuna Jin on 2018/11/19.
 */
@Slf4j
@Service
public class QiniuObjectStoreServiceImpl implements QiniuObjectStoreService {

    @Autowired
    private UploadRecordMapper uploadRecordMapper;

    @Autowired
    private Auth auth;

    @Value("${qiniu.bucket.private.name}")
    private String privateBucketName;

    @Value("${qiniu.bucket.private.url}")
    private String privateBucketUrl;

    @Value("${qiniu.bucket.public.name}")
    private String publicBucketName;

    @Value("${qiniu.bucket.public.url}")
    private String publicBucketUrl;

    @Value("${qiniu.callback-url}")
    private String callbackUrl;

    @Value("${qiniu.token.upload.expire-seconds}")
    private Long uploadTokenExpireSeconds;

    @Value("${qiniu.token.download.expire-seconds}")
    private Long downloadTokenExpireSeconds;

    private static final String UNREGISTERED = "unregistered";

    /**
     * 获取上传凭证
     * @param isSystemUser 是否是系统用户
     * @return 上传凭证
     */
    @Override
    public String uploadToken(Boolean isPublic, Boolean isSystemUser, String userId, String ext) {
        // 设置默认值 兼容旧版接口设计 2019-05-18
        if (Objects.isNull(isPublic))
            isPublic = false;
        if (Objects.isNull(isSystemUser))
            isSystemUser = true;
        String bucketName = isPublic ? publicBucketName : privateBucketName;
        // 文件上传后的 callback 参数 -> UploadRecord
        Map<String, String> callbackBodyConfig = new HashMap<>();
        String suffix = StringUtils.isBlank(ext) ? "$(ext)" : ext;
        callbackBodyConfig.put("key","$(key)");
        callbackBodyConfig.put("hash","$(etag)");
        callbackBodyConfig.put("bucket","$(bucket)");
        callbackBodyConfig.put("size","$(fsize)");
        callbackBodyConfig.put("name","$(fname)");
        callbackBodyConfig.put("mimeType","$(mimeType)");
        callbackBodyConfig.put("creatorId","$(endUser)");
        callbackBodyConfig.put("suffix", suffix);
        // 定义上传回调信息
        StringMap putPolicy = new StringMap();
        try {
            if (StringUtils.isBlank(userId)) {
                if (isSystemUser)
                    userId = GlobalContextUtil.getAuthResult().getAccessToken().getUser().getId();
                else
                    userId = UNREGISTERED;
            }
            putPolicy.put("endUser", userId);
            putPolicy.put("saveKey", "$(endUser)/$(year)-$(mon)-$(day)/" + bucketName + "/${etag}" + suffix);
        } catch (Exception e) {
            log.error("获取 上传凭证时 userId 获取失败: {}", e.getMessage());
            throw new BusinessVerifyFailedException("userId 非法");
        }
        putPolicy.put("callbackUrl", callbackUrl);
        putPolicy.put("callbackBody", JSON.toJSONString(callbackBodyConfig));
        putPolicy.put("callbackBodyType", "application/json");
        return auth.uploadToken(bucketName, null, uploadTokenExpireSeconds, putPolicy, false);
    }

    /**
     * 文件上传回调处理
     * @param originAuthorization Authorization 请求头
     * @param url 回调的请求url
     * @param body 回调的请求body
     * @param contentType 回调的请求 content-type 请求头
     * @return QiniuFileInfo
     */
    @Override @Transactional
    public QiniuFileInfo uploadCallback(String originAuthorization, String url, byte[] body, String contentType) throws UnsupportedEncodingException {
        if (!auth.isValidCallback(originAuthorization, url, body, contentType))
            throw new TokenAuthCheckFailedException("Qiniu OriginAuthorization 非法");
        // 手动解析请求体JSON内容
        UploadRecord uploadRecord = JSON.parseObject(new String(body, StandardCharsets.UTF_8), UploadRecord.class);
        // 保存文件信息 先检查是否存在 再添加 防止重复添加 TODO: // 待优化
        UploadRecordQueryCondition condition = new UploadRecordQueryCondition();
        condition.setKey(uploadRecord.getKey());
        if (uploadRecordMapper.selectByCondition(condition).isEmpty()){
            uploadRecord.setId(UUIDGenerator.get(UploadRecord.class));
            // 当为空字符串时表示是未注册用户上传的图片, 如进行注册申请中的用户
            if (uploadRecord.getCreatorId().equals(UNREGISTERED))
                uploadRecord.setCreatorId("");
            uploadRecordMapper.insertSelective(uploadRecord);
        }
        return new QiniuFileInfo(downloadUrl(uploadRecord.getKey()),  uploadRecord);
    }

    /**
     * 文件上传回调
     *
     * @param request HttpServletRequest
     * @return QiniuFileInfo
     */
    @Override
    public QiniuFileInfo uploadCallback(HttpServletRequest request) throws IOException {
        // 获取请求头
        String authorization = request.getHeader("Authorization");
        String contentType = request.getHeader("Content-Type");
        // 获取请求体
        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        ServletInputStream in = null;
        try {
            in = request.getInputStream();
            in.read(buffer, 0, len);
            in.close();
        } catch (IOException e) {
            log.error("读取 文件上传回调请求体 失败", e);
        }
        finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uploadCallback(authorization, callbackUrl, buffer, contentType);
    }

    /**
     * 获取下载链接
     * @param fileKey 需要下载文件的名称
     * @return 下载链接
     */
    @Override
    public String downloadUrl(String fileKey) throws UnsupportedEncodingException {
        boolean isPublic = fileKey.contains("/" + publicBucketName + "/");
        String fileUrl = String.format("%s/%s", isPublic ? publicBucketUrl : privateBucketUrl, URLEncoder.encode(fileKey, "utf-8"));
        if (isPublic)
            return fileUrl;
        return auth.privateDownloadUrl(fileUrl, downloadTokenExpireSeconds);
    }

    /**
     * 获取下载链接
     * @param fileKeys 需要下载文件的key list
     * @return 下载链接 list
     */
    @Override
    public List<String> downloadUrl(List<String> fileKeys) {
        return fileKeys.stream().map(fileKey -> {
            try {
                return this.downloadUrl(fileKey);
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
                return null;
            }
        }).collect(Collectors.toList());
    }
}
