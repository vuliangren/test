package com.welearn.entity.vo.response.common;

import com.welearn.entity.po.common.Permission;
import com.welearn.entity.po.common.ReRolePermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class RoleRefPermissions {
    @NotNull private List<ReRolePermission> reRolePermissions;
    @NotNull private List<Permission> permissions;
}