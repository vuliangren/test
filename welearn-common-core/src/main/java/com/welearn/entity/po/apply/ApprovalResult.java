package com.welearn.entity.po.apply;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_apply : approval_result
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/25 13:45:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ApprovalResult", description = "ApprovalResult 领域实体")
public class ApprovalResult extends BasePersistant
{
    /**
     * Description  : 申请id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "申请id", allowEmptyValue = false, position = 4 )
    private String applicationId;

    /**
     * Description  : 排序,对应processJson的某个节点
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @ApiModelProperty( value = "排序,对应processJson的某个节点", allowEmptyValue = false, position = 5 )
    private Integer sort;

    /**
     * Description  : 审批意见
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:2048]
     */
    @ApiModelProperty( value = "审批意见", allowEmptyValue = true, position = 6 )
    private String opinion;

    /**
     * Description  : 审批结果
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "审批结果", allowEmptyValue = false, position = 7 )
    private Integer result;

    /**
     * Description  : 部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "部门id", allowEmptyValue = true, position = 8 )
    private String departmentId;

    /**
     * Description  : 部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "部门名称", allowEmptyValue = true, position = 9 )
    private String departmentName;

    /**
     * Description  : 审批人id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "审批人id", allowEmptyValue = false, position = 10 )
    private String approverId;

    /**
     * Description  : 审批人姓名
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "审批人姓名", allowEmptyValue = true, position = 11 )
    private String approverName;

    /**
     * Description  : 审批人签名
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "审批人签名", allowEmptyValue = true, position = 12 )
    private String signatureId;

    /**
     * Description  : 审批类型 0-部门领导审批 1-部门汇总意见后领导录入 2-部门汇总意见后领导审批 3-抄送通知
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "审批类型 0-部门领导审批 1-部门汇总意见后领导录入 2-部门汇总意见后领导审批 3-抄送通知", allowEmptyValue = false, position = 13 )
    private Integer approvalType;

    /**
     * Description  : 审批时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:timestamp]
     */
    @ApiModelProperty( value = "审批时间", allowEmptyValue = true, position = 14 )
    private Date approvalAt;

}
