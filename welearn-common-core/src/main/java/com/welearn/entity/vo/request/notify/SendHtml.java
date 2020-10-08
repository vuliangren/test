package com.welearn.entity.vo.request.notify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Description : 单邮件发送
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SendHtml {
    @NotNull private String receiver;
    @NotNull private String subject;
    @NotNull private String html;
}