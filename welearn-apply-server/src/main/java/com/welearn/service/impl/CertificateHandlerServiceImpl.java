package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.apply.CertificateStatusConst;
import com.welearn.entity.po.apply.Certificate;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.service.CertificateHandlerService;
import com.welearn.service.CertificateService;
import com.welearn.util.UUIDGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Description : 证书登记 申请处理
 * Created by Setsuna Jin on 2018/10/20.
 */
@Service
public class CertificateHandlerServiceImpl extends ApplicationServiceImpl<Certificate> implements CertificateHandlerService {

    @Autowired
    private CertificateService certificateService;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(Certificate content) {
        if (StringUtils.isNotBlank(content.getCurrentId())){
            Certificate current = certificateService.select(content.getCurrentId());
            if (Objects.isNull(current))
                throw new BusinessVerifyFailedException("currentId 非法");
        }
        certificateService.create(content);
        certificateService.disable(content);
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        certificateService.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(Certificate content) {
        certificateService.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public Certificate selectContent(String contentId) {
        return certificateService.select(contentId);
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(Certificate content) {
        return content.getName();
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.CERTIFICATE;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    @Transactional
    public void afterApplicationPass(String applicationId, String contentId) {
        Certificate certificate = this.selectContent(contentId);
        certificate.setStatus(CertificateStatusConst.PASS.ordinal());
        certificate.setApplicationId(applicationId);
        // 根据现有内容 更新 新证书
        String currentId = certificate.getCurrentId();
        if (StringUtils.isNotBlank(currentId)){
            // 更新现有内容
            certificateService.update(certificate);
            // 更新再用证书
            certificate.setId(currentId);
            certificate.setCurrentId(null);
            certificate.setIsEnable(true);
            certificateService.update(certificate);
        }
        // 根据现有内容 创建 新证书
        else {
            String certificateId = UUIDGenerator.get(Certificate.class);
            certificate.setCurrentId(certificateId);
            // 更新现有内容
            certificateService.update(certificate);
            // 创建新的证书
            certificate.setId(certificateId);
            certificate.setCurrentId(null);
            certificate.setIsEnable(true);
            certificateService.create(certificate);
        }
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) {
        Certificate certificate = this.selectContent(contentId);
        certificate.setStatus(CertificateStatusConst.REJECT.ordinal());
        certificate.setApplicationId(applicationId);
        certificateService.update(certificate);
    }
}
