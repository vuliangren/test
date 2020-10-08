package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.welearn.dictionary.notify.SmsSendRecordStatusConst;
import com.welearn.dictionary.notify.SmsSignatureConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.notify.SmsSendRecord;
import com.welearn.entity.po.notify.SmsSendTemplate;
import com.welearn.entity.qo.notify.SmsSendRecordQueryCondition;
import com.welearn.entity.vo.request.notify.SmsReport;
import com.welearn.entity.vo.response.notify.SendReportResponse;
import com.welearn.entity.vo.response.notify.SendSmsResponse;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.SmsSendRecordMapper;
import com.welearn.service.SmsSendRecordService;
import com.welearn.service.SmsSendService;
import com.welearn.service.SmsSendTemplateService;
import com.welearn.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.welearn.dictionary.notify.SmsSendRecordStatusConst.*;

/**
 * Description : 短信发送服务
 * Created by Setsuna Jin on 2019/3/18.
 */
@Slf4j
@Service
public class SmsSendServiceImpl implements SmsSendService {

    @Value("${debug}")
    private Boolean isDebug;

    @Value("${aliyun.sms.domain}")
    private String domain;

    @Value("${aliyun.sms.version}")
    private String version;

    @Autowired
    private IAcsClient iAcsClient;

    @Autowired
    private SmsSendTemplateService smsSendTemplateService;

    @Autowired
    private SmsSendRecordService smsSendRecordService;

    @Autowired
    private SmsSendRecordMapper smsSendRecordMapper;

    /**
     * 短信发送实现
     *
     * @param phoneNumber  手机号
     * @param signature    签名
     * @param templateCode 模板编号
     * @param params       参数
     * @return 发送记录
     */
    private SendSmsResponse send(String phoneNumber, SmsSignatureConst signature, String templateCode, String params) {
        if (isDebug)
            throw new BusinessVerifyFailedException("测试环境无法调用此接口");
        CommonRequest request = new CommonRequest();
        request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction("SendSms");
        // 设置参数
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", signature.getSignature());
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", StringUtils.isBlank(params) ? "{}" : params);
        // 开始发送
        try {
            CommonResponse response = iAcsClient.getCommonResponse(request);
            String responseData = response.getData();
            if (StringUtils.isBlank(responseData))
                throw new BusinessVerifyFailedException("短信发送请求结果 无响应体");
            SendSmsResponse sendSmsResponse = JSON.parseObject(responseData, SendSmsResponse.class);
            if (Objects.isNull(sendSmsResponse) || StringUtils.isBlank(sendSmsResponse.getBizId()))
                throw new BusinessVerifyFailedException("短信发送请求结果 响应体数据格式异常: %s", responseData);
            return sendSmsResponse;
        } catch (ServerException e) {
            throw new BusinessVerifyFailedException(e, "短信发送失败-云服务端异常: phoneNumber: %s, signature: %s, templateCode: %s", phoneNumber, signature.getSignature(), templateCode);
        } catch (ClientException e) {
            throw new BusinessVerifyFailedException(e, "短信发送失败-客户端的异常: phoneNumber: %s, signature: %s, templateCode: %s", phoneNumber, signature.getSignature(), templateCode);
        } catch (Exception e) {
            throw new BusinessVerifyFailedException(e, "短信发送失败-程序代码异常: phoneNumber: %s, signature: %s, templateCode: %s", phoneNumber, signature.getSignature(), templateCode);
        }
    }

    /**
     * 短信发送
     *
     * @param user         用户
     * @param signature    签名
     * @param code         系统内模板编号
     * @param params       参数
     */
    @Override @Async("smsSendExecutor")
    public void sendForUser(User user, SmsSignatureConst signature, String code, Map<String, String> params, String noticeId) {
        this.sendForPhone(user.getTelephone(), signature, code, params, user.getCompanyId(), user.getDepartmentId(), user.getId(), noticeId);
    }

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
    @Override @Async("smsSendExecutor")
    public void sendForPhone(String phoneNumber, SmsSignatureConst signature, String code,
                              Map<String, String> params, String companyId, String departmentId, String userId, String noticeId) {
        SmsSendTemplate smsSendTemplate = smsSendTemplateService.selectByCode(code);
        // 对于前置性条件检验未通过的直接报错
        if (Objects.isNull(smsSendTemplate))
            throw new BusinessVerifyFailedException("code 非法");
        if (StringUtils.isBlank(phoneNumber) || !RegexUtil.checkPhone(phoneNumber))
            throw new BusinessVerifyFailedException("phoneNumber 非法");
        // 处理空参数问题
        String paramsJson = Objects.isNull(params) ? "{}" : JSON.toJSONString(params);
        // 构建发送记录
        SmsSendRecord record = new SmsSendRecord();
        record.setCompanyId(companyId);
        record.setDepartmentId(departmentId);
        record.setUserId(userId);
        record.setPhoneNumber(phoneNumber);
        record.setParamsJson(paramsJson);
        record.setNoticeId(noticeId);
        record.setSignature(signature.getSignature());
        record.setTemplateId(smsSendTemplate.getId());
        // 执行发送逻辑
        try {
            SendSmsResponse sendSmsResponse = this.send(phoneNumber, signature, smsSendTemplate.getTemplateCode(), paramsJson);
            // 记录流水信息 临时设置 code 和 message
            record.setBizId(sendSmsResponse.getBizId());
            record.setReportCode(sendSmsResponse.getCode());
            record.setReportMessage(sendSmsResponse.getMessage());
            // 判断发送结果
            if ("OK".equals(sendSmsResponse.getCode())){
                record.setStatus(WAITING.ordinal());
            } else {
                record.setStatus(FAILED.ordinal());
            }
        } catch (Exception e){
            log.error("短信发送失败: 系统/代码/接口问题", e);
        }
        // 无论发送成功与否 都会创建一条记录
        smsSendRecordService.create(record);
    }

    /**
     * 结果回调
     *
     * @param smsReports 阿里云的回调参数
     * @return 阿里云要求的响应格式
     */
    @Override
    public SendReportResponse report(List<SmsReport> smsReports) {
        for (SmsReport smsReport : smsReports) {
            SmsSendRecordQueryCondition condition = new SmsSendRecordQueryCondition();
            condition.setIsEnable(true);
            condition.setBizId(smsReport.getBizId());
            List<SmsSendRecord> sendRecords = smsSendRecordMapper.selectByCondition(condition);
            // 更新短信发送记录的状态
            if (Objects.nonNull(sendRecords) && !sendRecords.isEmpty()) {
                SmsSendRecord smsSendRecord = sendRecords.get(0);
                smsSendRecord.setSendAt(smsReport.getSendTime());
                smsSendRecord.setReceivedAt(smsReport.getReportTime());
                smsSendRecord.setReportCode(smsReport.getErrCode());
                smsSendRecord.setReportMessage(smsReport.getErrMsg());
                smsSendRecord.setSize(smsReport.getSmsSize());
                smsSendRecord.setOutId(smsReport.getOutId());
                smsSendRecord.setStatus(smsReport.getSuccess() ? SUCCESS.ordinal() : FAILED.ordinal());
                smsSendRecordMapper.updateByPK(smsSendRecord);
            }
        }
        return new SendReportResponse(0, "成功");
    }
}
