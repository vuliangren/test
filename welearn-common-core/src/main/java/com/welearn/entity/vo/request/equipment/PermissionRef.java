package com.welearn.entity.vo.request.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Description : 查询设备权限关联数据的查询体
 * Created by Setsuna Jin on 2019/1/3.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRef {
    @NotNull
    private Integer permissionCode;
    @NotNull
    private Integer type;
    @NotBlank
    private String typeId;
}
