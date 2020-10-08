package com.welearn.service;

import com.welearn.entity.po.common.ReRoutePermission;
import com.welearn.entity.qo.common.ReRoutePermissionQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : ReRoutePermissionService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReRoutePermissionService extends BaseService<ReRoutePermission, ReRoutePermissionQueryCondition>{


    /**
     * 根据角色编码 查询与之关联的路由 所 关联权限编码
     * @param roleCode 角色编码
     * @param clientType 客户端类型
     * @return 权限编码
     */
    List<String> selectPermissionCodeByRoleCode(@NotBlank String roleCode, @NotNull Integer clientType);

    /**
     * 批量创建和更新 一个路由的权限关联
     * 将路由现有关联与参数进行对比, 相同的不变, 其余的创建或删除
     * @param clientType 客户端类型
     * @param path 路由地址
     * @param permissionCodes 权限关联
     */
    void batchCreateAndUpdate(@NotNull Integer clientType,
                              @NotBlank String path,
                              @NotEmpty List<String> permissionCodes);
}