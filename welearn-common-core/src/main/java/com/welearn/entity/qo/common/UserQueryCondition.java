package com.welearn.entity.qo.common;

import com.welearn.entity.po.common.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description : User Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/13 11:40:42
 * @see com.welearn.entity.po.common.User
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserQueryCondition extends User {
    private String roleId;
    private String positionId;
}
