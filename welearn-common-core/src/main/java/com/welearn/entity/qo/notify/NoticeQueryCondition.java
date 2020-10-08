package com.welearn.entity.qo.notify;

import com.welearn.entity.po.notify.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : Notice Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/21 20:16:21
 * @see Notice
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class NoticeQueryCondition extends Notice {
    private Integer typeGreaterThan;
    private Date releasedAtLessThan;
    private Date releasedAtGreaterThan;

    private String userId;
    private Boolean isViewed;
}
