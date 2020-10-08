package com.welearn.service;

import com.welearn.dictionary.notify.SmsSignatureConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.notify.SmsSendRecord;
import com.welearn.entity.vo.request.notify.SmsReport;
import com.welearn.entity.vo.response.notify.SendReportResponse;
import com.welearn.entity.vo.response.notify.SendSmsResponse;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Description : 短信发送服务
 * Created by Setsuna Jin on 2019/3/18.
 */
public interface SmsSendService {

    /**
     * 短信发送
     *
     * @param user         用户
     * @param signature    签名
     * @param code         系统内模板编号
     * @param params       参数
     * @param noticeId     通知id
     */
    void sendForUser(@EntityCheck(checkId = true) User user, @NotNull SmsSignatureConst signature, @NotBlank String code, Map<String, String> params, String noticeId);

    /**
     * 短信发送
     *
     * @param phoneNumber  手机号
     * @param signature    签名
     * @param code         系统内模板编号
     * @param params       参数
     * @param companyId    公司id
     * @param departmentId 部门id
     * @param userId       用户id
     * @param noticeId     通知id
     */
    void sendForPhone(@NotBlank String phoneNumber, @NotNull SmsSignatureConst signature, @NotBlank String code,
                         Map<String, String> params, String companyId, String departmentId, String userId, String noticeId);


    /**
     * 结果回调
     * @param smsReports 阿里云的回调参数
     * @return 阿里云要求的响应格式
     */
    SendReportResponse report(@NotNull List<SmsReport> smsReports);
}
