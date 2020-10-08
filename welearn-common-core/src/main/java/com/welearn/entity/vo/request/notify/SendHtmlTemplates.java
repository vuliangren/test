package com.welearn.entity.vo.request.notify;

import java.lang.String;
import java.lang.Object;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Description : 多邮件 模板生成 发送
 * Created by Setsuna Jin on 2018/11/27.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SendHtmlTemplates {
    @NotNull private String[] receivers;
    @NotNull private String subject;
    @NotNull private String code;
    @NotNull private Object args;
}