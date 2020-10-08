package com.welearn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.notify.NoticeMethodConst;
import com.welearn.dictionary.notify.NoticeStatusConst;
import com.welearn.dictionary.notify.NoticeTypeConst;
import com.welearn.dictionary.notify.WebSocketClientRegisterConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.notify.Notice;
import com.welearn.entity.po.notify.NoticeRecord;
import com.welearn.entity.po.storage.RichText;
import com.welearn.entity.qo.common.UserQueryCondition;
import com.welearn.entity.qo.notify.NoticeQueryCondition;
import com.welearn.entity.qo.notify.NoticeRecordQueryCondition;
import com.welearn.entity.vo.response.notify.NoticeInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.UserFeignClient;
import com.welearn.feign.storage.RichTextFeignClient;
import com.welearn.mapper.NoticeMapper;
import com.welearn.service.EmailSendService;
import com.welearn.service.NoticeRecordService;
import com.welearn.service.NoticeService;
import com.welearn.util.PaginateUtil;
import com.welearn.websocket.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.welearn.dictionary.notify.HtmlTemplateTypeConst.*;

/**
 * Description : NoticeService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice,NoticeQueryCondition,NoticeMapper>
        implements NoticeService{

    private static final String[] EMAIL_TEMPLATE_CODE = new String[]{SYSTEM_NOTICE.name(), COMPANY_NOTICE.name(), DEPARTMENT_NOTICE.name(), USER_NOTICE.name()};

    private static final String[] NOTICE_TYPE = new String[]{"系统公告","公司公告","部门公告","员工通知"};
    private static final String[] NOTICE_LEVER = new String[]{"一般通知","重要通知","紧急通知","加急通知"};

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private EmailSendService emailSendService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private RichTextFeignClient richTextFeignClient;

    @Autowired
    private NoticeRecordService noticeRecordService;

    @Autowired
    private WebSocketService webSocketService;

    @Override
    NoticeMapper getMapper() {
        return noticeMapper;
    }

    @Override
    public Notice create(Notice notice){
        // 检查是否立即发送通知
        boolean isSendImmediately = false;
        if (Objects.isNull(notice.getReleasedAt())){
            isSendImmediately = true;
            notice.setReleasedAt(new Date());
        }
        // 如果 未设置过期时间, 则默认按照三天后过期
        if (Objects.isNull(notice.getExpiredAt()))
            notice.setExpiredAt(new DateTime(notice.getReleasedAt()).plusDays(3).toDate());
        super.create(notice);
        // 是否立即发送
        if (isSendImmediately){
            this.sendNotice(this.select(notice.getId()));
        }
        return notice;
    }

    /**
     * 发送通知
     * @param notice 通知
     */
    @Override
    public void sendNotice(Notice notice) {
        if (Objects.isNull(notice) || notice.getStatus() != NoticeStatusConst.UN_SEND.ordinal() || !notice.getIsEnable())
            throw new BusinessVerifyFailedException("通知的状态 非法");
        // 标记状态
        log.info("开始发送通知:{}", notice.getTitle());
        notice.setStatus(NoticeStatusConst.SENDING.ordinal());
        this.update(notice);
        // 判断是否需要发送邮件 或 短信
        boolean isSendEmail = notice.getMethod() == NoticeMethodConst.MESSAGE_EMAIL.ordinal()
                || notice.getMethod() == NoticeMethodConst.MESSAGE_EMAIL_SMS.ordinal();
        boolean isSendSMS   = notice.getMethod() == NoticeMethodConst.MESSAGE_SMS.ordinal()
                || notice.getMethod() == NoticeMethodConst.MESSAGE_EMAIL_SMS.ordinal();
        // 查找用户信息
        List<User> receivers = this.searchReceivers(notice);
        // 开始发送逻辑
        try {
            if (Objects.isNull(receivers) || receivers.isEmpty())
                return;
            else {
                // 添加站内信发送记录
                for (User receiver : receivers) {
                    NoticeRecord noticeRecord = new NoticeRecord();
                    noticeRecord.setIsViewed(false);
                    noticeRecord.setNoticeId(notice.getId());
                    noticeRecord.setUserId(receiver.getId());
                    noticeRecord.setUserName(receiver.getName());
                    noticeRecordService.create(noticeRecord);
                    // 尝试通知在线客户端重新查询通知
                    JSONObject object = new JSONObject();
                    object.put("notice", notice);
                    webSocketService.notifyClient(receiver.getId(), WebSocketClientRegisterConst.FETCH_NOTICES, object);
                }
            }
            // 发送邮件
            if (isSendEmail){
                this.sendNoticeByEmail(notice, receivers);
            }
            // 发送短信
            if (isSendSMS){
                // TODO: 待实现短信发送通知部分, 短信只发送标题信息
            }
            notice.setStatus(NoticeStatusConst.ALL_SEND.ordinal());
        } catch (Exception e){
            log.error("消息通知发送出现异常", e);
            notice.setStatus(NoticeStatusConst.PART_SEND.ordinal());
        }
        this.update(notice);
        log.info("通知:{} 发送结束", notice.getTitle());

    }

    /**
     * 根据公告信息获取接收人列表
     * @param notice 公告内容
     * @return 接收人列表
     */
    public List<User> searchReceivers(Notice notice){
        UserQueryCondition condition = new UserQueryCondition();
        condition.setIsEnable(true);
        if (notice.getType() == NoticeTypeConst.COMPANY_NOTICE.ordinal()){
            condition.setCompanyId(notice.getReceiverCompanyId());
        } else if (notice.getType() == NoticeTypeConst.DEPARTMENT_NOTICE.ordinal()) {
            condition.setDepartmentId(notice.getReceiverDepartmentId());
        } else if (notice.getType() == NoticeTypeConst.USER_NOTICE.ordinal()) {
            condition.setId(notice.getReceiverId());
        }
        return userFeignClient.search(condition).getData();
    }

    /**
     * 通过邮件发送通知
     * @param notice 通知
     * @param receivers 接收人
     */
    public void sendNoticeByEmail(Notice notice, List<User> receivers){
        Map<String, Object> data = new HashMap<>();
        List<String> emails = receivers.stream()
                .map(User::getEmail)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
        // 检查是否含有富文本内容
        if (Objects.nonNull(notice.getContentId()) && !notice.getIsShortNotice()){
            RichText richText = richTextFeignClient.select(notice.getContentId()).getData();
            if (Objects.nonNull(richText)){
                data.put("content", richText.getHtml());
            }
        }
        // 添加上下文渲染参数
        data.put("releasedAt", new DateTime(notice.getReleasedAt()).toString("yyyy-MM-dd HH:mm:ss"));
        data.put("type", notice.getType());
        data.put("lever", NOTICE_TYPE[notice.getType()]);
        data.put("title", NOTICE_LEVER[notice.getLever()]);
        data.put("isShortNotice", notice.getIsShortNotice());
        data.put("senderDescription", notice.getSenderDescription());
        data.put("senderName", notice.getSenderName());
        data.put("senderCompanyName", notice.getSenderCompanyName());
        data.put("senderDepartmentName", notice.getSenderDepartmentName());
        // 发送邮件
        emailSendService.sendHtml(emails, notice.getTitle(), EMAIL_TEMPLATE_CODE[notice.getType()], data);
    }

    /**
     * 查询需要定时发送的通知提醒, 进行发送
     * 每十分钟查询一次
     */
    @Override
    @Scheduled(cron = "0 0/10 * * * *")
    public void autoSendNotice() {
        // 查询 前5分钟 至 后5分钟 符合条件的维护任务
        DateTime current = new DateTime();
        NoticeQueryCondition condition = new NoticeQueryCondition();
        condition.setIsEnable(true);
        condition.setStatus(NoticeStatusConst.UN_SEND.ordinal());
        condition.setReleasedAtGreaterThan(current.minusMinutes(5).toDate());
        condition.setReleasedAtLessThan(current.plusMinutes(5).toDate());
        List<Notice> unSendNoticeList = this.search(condition);
        log.info("查询到需要发送的通知: {} 个", unSendNoticeList.size());
        log.info("开始定时通知的发送");
        for (Notice notice : unSendNoticeList) {
            this.sendNotice(notice);
        }
        log.info("定时通知已发送完成");
    }

    /**
     * 浏览通知
     *
     * @param noticeId 通知id
     * @param userId   用户id
     */
    @Override
    public void view(String noticeId, String userId) {
        NoticeRecordQueryCondition condition = new NoticeRecordQueryCondition();
        condition.setIsEnable(true);
        condition.setNoticeId(noticeId);
        condition.setUserId(userId);
        List<NoticeRecord> records = noticeRecordService.search(condition);
        if (Objects.nonNull(records) && !records.isEmpty()){
            records.forEach(noticeRecord -> {
                if (!noticeRecord.getIsViewed()){
                    noticeRecord.setIsViewed(true);
                    noticeRecordService.update(noticeRecord);
                }
            });
        }
    }

    /**
     * 查询通知详情 分页
     * @param condition 条件
     */
    @Override
    public List<NoticeInfo> searchInfo(NoticeQueryCondition condition) {
        return PaginateUtil.page(() -> noticeMapper.selectInfoByCondition(condition));
    }
}
