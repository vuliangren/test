package com.welearn.entity.vo.request.common;

import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.common.Department;
import java.lang.String;
import java.util.List;

import com.welearn.entity.po.common.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import io.swagger.annotations.Api;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyAndInit {
    private Company company;
    private Department department;
    private User user;
    private List<String> roleIds;
    private List<String> positionIds;
}