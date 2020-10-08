package com.welearn.service.impl;

import com.welearn.service.EmailSendService;
import com.welearn.service.HtmlRenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Description : 邮件发送服务实现
 * Created by Setsuna Jin on 2018/11/22.
 */
@Slf4j
@Service
public class EmailSendServiceImpl implements EmailSendService {

    private static final String sendFromPersonal = "锐阳医疗设备管理系统";

    @Value("${spring.mail.username}")
    private String sendFromAddress;

    @Autowired
    private HtmlRenderService htmlRenderService;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送网页邮件
     *
     * @param receiver 接收人
     * @param subject  邮件主体
     * @param html     邮件内容
     */
    @Override @Async("mailSendExecutor")
    public void sendHtml(String receiver, String subject, String html) {
        this.sendHtml(new String[]{receiver}, subject, html);
    }

    /**
     * 发送网页邮件
     *
     * @param receivers 接收人列表
     * @param subject  邮件主体
     * @param html     邮件内容
     */
    @Override @Async("mailSendExecutor")
    public void sendHtml(String[] receivers, String subject, String html) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(new InternetAddress(sendFromAddress,sendFromPersonal,"utf-8"));
            helper.setTo(receivers);
            helper.setSubject(subject);
            helper.setText(html, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 发送网页邮件
     *
     * @param receiver 接收人
     * @param subject  邮件主体
     * @param code     模板类型
     * @param args     模板参数
     */
    @Override @Async("mailSendExecutor")
    public void sendHtml(String receiver, String subject, String code, Object args) {
        this.sendHtml(receiver, subject, htmlRenderService.renderHtml(code, args));
    }

    /**
     * 发送网页邮件
     *
     * @param receivers 接收人列表
     * @param subject   邮件主体
     * @param code      模板类型
     * @param args      模板参数
     */
    @Override @Async("mailSendExecutor")
    public void sendHtml(String[] receivers, String subject, String code, Object args) {
        this.sendHtml(receivers, subject, htmlRenderService.renderHtml(code, args));
    }

    /**
     * 发送网页邮件
     *
     * @param receivers 接收人列表
     * @param subject   邮件主体
     * @param code      模板类型
     * @param args      模板参数
     */
    @Override @Async("mailSendExecutor")
    public void sendHtml(List<String> receivers, String subject, String code, Object args) {
        this.sendHtml(receivers.toArray(new String[]{}), subject, htmlRenderService.renderHtml(code, args));
    }

    /**
     * 发送网页邮件
     *
     * @param receivers 接收人列表
     * @param subject  邮件主体
     * @param html     邮件内容
     */
    @Override @Async("mailSendExecutor")
    public void sendHtml(List<String> receivers, String subject, String html) {
        this.sendHtml(receivers.toArray(new String[]{}), subject, html);
    }
}
