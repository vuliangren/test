package com.welearn.service;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : 邮件发送服务
 * Created by Setsuna Jin on 2018/11/22.
 */
public interface EmailSendService {
    /**
     * 发送网页邮件
     * @param receiver 接收人
     * @param subject 邮件主体
     * @param html 邮件内容
     */
    void sendHtml(@NotBlank String receiver, @NotBlank String subject, @NotBlank String html);


    /**
     * 发送网页邮件
     *
     * @param receivers 接收人列表
     * @param subject  邮件主体
     * @param html     邮件内容
     */
    void sendHtml(@NotEmpty String[] receivers, @NotBlank String subject, @NotBlank String html);

    /**
     * 发送网页邮件
     *
     * @param receivers 接收人列表
     * @param subject  邮件主体
     * @param html     邮件内容
     */
    void sendHtml(@NotEmpty List<String> receivers, @NotBlank String subject, @NotBlank String html);

    /**
     * 发送网页邮件
     *
     * @param receiver 接收人
     * @param subject  邮件主体
     * @param code     模板类型
     * @param args     模板参数
     */
    void sendHtml(@NotBlank String receiver, @NotBlank String subject, @NotBlank String code, @NotNull Object args);

    /**
     * 发送网页邮件
     *
     * @param receivers 接收人列表
     * @param subject  邮件主体
     * @param code     模板类型
     * @param args     模板参数
     */
    void sendHtml(@NotEmpty String[] receivers, @NotBlank String subject, @NotBlank String code, @NotNull Object args);

    /**
     * 发送网页邮件
     *
     * @param receivers 接收人列表
     * @param subject  邮件主体
     * @param code     模板类型
     * @param args     模板参数
     */
    void sendHtml(@NotEmpty List<String> receivers, @NotBlank String subject, @NotBlank String code, @NotNull Object args);

}
