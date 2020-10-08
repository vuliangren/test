package com.welearn.entity.vo.request.apply;

import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.po.apply.ApprovalResult;
import java.lang.String;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Approval {
    @NotNull private List<ApprovalProcessPoint> points;
    @NotNull private ApprovalResult result;
    @NotNull private String approverId;
}