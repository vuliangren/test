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
public class WeChatQRCodeLoginResult extends TokenPackage {
    private String openId;
    private String unionId;
}
