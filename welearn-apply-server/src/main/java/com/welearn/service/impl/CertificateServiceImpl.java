package com.welearn.service.impl;

import com.welearn.dictionary.apply.CertificateStatusConst;
import com.welearn.entity.po.apply.Certificate;
import com.welearn.entity.qo.apply.CertificateQueryCondition;
import com.welearn.mapper.CertificateMapper;
import com.welearn.service.CertificateService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description : CertificateService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class CertificateServiceImpl extends BaseServiceImpl<Certificate,CertificateQueryCondition,CertificateMapper>
        implements CertificateService{
    
    @Autowired
    private CertificateMapper certificateMapper;
    
    @Override
    CertificateMapper getMapper() {
        return certificateMapper;
    }

    /**
     * 自动检查正常的证书是否过期, 对临近过期 或 即将过期的证书 进行通知
     * 对即将过期证书修改证书状态为 已过期
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void autoExpiredCertificate(){
        log.info("证书自动过期任务 开始执行");
        CertificateQueryCondition condition = new CertificateQueryCondition();
        condition.setIsEnable(true);
        condition.setStatus(CertificateStatusConst.PASS.ordinal());
        List<Certificate> certificates = this.search(condition);
        long now = new Date().getTime();
        for (Certificate certificate : certificates) {
            long seconds = (certificate.getExpirationDate().getTime() - now) / 1000;
            if (seconds < 0) {
                certificate.setStatus(CertificateStatusConst.EXPIRED.ordinal());
                this.update(certificate);
                log.info("证书已经自动过期:{}, id: {}", certificate.getName(), certificate.getId());
                // TODO: 通知相关人员 进行 更新证书
            }
            else {
                long days = seconds / 3600 / 24;
                if (days == 7){
                    // TODO: 通知相关人员 进行 更新证书
                    log.info("证书07天后即将过期:{}, id: {}", certificate.getName(), certificate.getId());
                }
                else if (days == 30) {
                    // TODO: 通知相关人员 进行 更新证书
                    log.info("证书30天后即将过期:{}, id: {}", certificate.getName(), certificate.getId());
                }
            }
        }
        log.info("证书自动过期任务 执行完毕");
    }
}
