package com.welearn.entity.vo.request.authorization;

import com.welearn.entity.vo.request.common.PasswordLogin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeChatAppBind {
    private PasswordLogin passwordLogin;
    private String openId;
}
