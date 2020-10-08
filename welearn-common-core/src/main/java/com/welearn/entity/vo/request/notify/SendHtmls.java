package com.welearn.entity.vo.request.notify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Description : 多邮件发送
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SendHtmls {
    @NotNull private String[] receivers;
    @NotNull private String subject;
    @NotNull private String html;
}