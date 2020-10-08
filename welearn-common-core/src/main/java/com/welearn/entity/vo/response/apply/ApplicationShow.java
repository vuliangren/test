package com.welearn.entity.vo.response.apply;

import com.welearn.entity.po.apply.ApprovalApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : 申请管理显示用数据
 * Created by Setsuna Jin on 2018/10/16.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationShow extends ApprovalApplication {
    // 从 ApprovalApplication 查询
    private String applicationName;
    private String applicationDescription;
    private String applicationCode;
    // 从 ApprovalResult 查询
    private Integer approvalResult;
    private Integer approvalType;
    private Date approvalAt;
}
