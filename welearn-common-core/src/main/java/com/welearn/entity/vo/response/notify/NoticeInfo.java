package com.welearn.entity.vo.response.notify;

import com.welearn.entity.po.notify.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description :
 * Created by Setsuna Jin on 2019/2/11.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NoticeInfo extends Notice {
    private String userId;
    private String userName;
    private Boolean isViewed;
}
