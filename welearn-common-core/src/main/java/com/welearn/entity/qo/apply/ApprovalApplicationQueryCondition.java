package com.welearn.entity.qo.apply;

import com.welearn.entity.po.apply.ApprovalApplication;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : ApprovalApplication Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/18 17:12:07
 * @see ApprovalApplication
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ApprovalApplicationQueryCondition extends ApprovalApplication {
    private String applicationType;
    private String approverId;
    private Date approvalAtStart;
    private Date approvalAtEnd;
    private Date applyAtStart;
    private Date applyAtEnd;
    private Integer approvalType;
    // 是否是历史审批 sql 中条件 status >= 3
    private Boolean isHistoryApproval;
}
