package com.welearn.entity.vo.response.common;

import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.common.Position;
import com.welearn.entity.po.common.Role;
import com.welearn.entity.po.common.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends User {
    private String companyName;
    private List<Department> departments;
    private List<Role> roles;
    private List<PositionInfo> positions;
}
