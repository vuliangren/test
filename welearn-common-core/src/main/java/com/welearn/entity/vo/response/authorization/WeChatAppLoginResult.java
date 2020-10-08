package com.welearn.entity.vo.response.authorization;

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
public class WeChatAppLoginResult extends TokenPackage {
    private Boolean isBind;
    private String openId;
}
