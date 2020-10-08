package com.welearn.entity.vo.response.authorization;

import com.welearn.entity.dto.authorization.Token;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.common.Position;
import com.welearn.entity.po.common.WechatAppUser;
import com.welearn.entity.po.equipment.Engineer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/20.
 */
@Data
@EqualsAndHashCode
public class AuthResult implements Serializable{
    private Token accessToken;
    private Set<String> permissionCodeList;
    private Set<String> roleCodeList;
    private Set<String> positionNameList;
    private Company company;
    // 当前默认的部门 客户端请求时 请求头会带上
    // currentDepartmentId 由 GlobalContextUtil.getAuthResult 设置
    private Department department;
    // 用户关联的所有部门
    private Set<Department> departments;
    private WechatAppUser wechatAppUser;
    private Set<Engineer> engineerInfoList;
}
