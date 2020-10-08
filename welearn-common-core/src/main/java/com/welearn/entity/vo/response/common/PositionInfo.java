package com.welearn.entity.vo.response.common;

import com.welearn.entity.po.common.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/15.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PositionInfo extends Position {
    private String userId;
    private String userName;
    private String userDepartmentId;
}
