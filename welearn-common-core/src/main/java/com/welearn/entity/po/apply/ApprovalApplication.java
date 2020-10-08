package com.welearn.entity.po.apply;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_apply : approval_application
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/12 14:50:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApprovalApplication extends BasePersistant
{
    /**
     * Description  : 分步申请中的上一步申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String linkId;

    /**
     * Description  : 申请类型:0-一般申请,1-分步申请
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer type;

    /**
     * Description  : 申请人id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String applicantId;

    /**
     * Description  : 申请人姓名
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String applicantName;

    /**
     * Description  : 公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String companyId;

    /**
     * Description  : 申请人所在部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String departmentId;

    /**
     * Description  : 申请人所在部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String departmentName;

    /**
     * Description  : 申请时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:timestamp]
     */
    private Date applyAt;

    /**
     * Description  : 申请状态 0-待提交 1-待审批 2-待修改 3-通过 4-驳回
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer status;

    /**
     * Description  : 申请内容id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String contentId;

    /**
     * Description  : 内容简述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:4096]
     */
    private String outlook;

    /**
     * Description  : 审批流程id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String processId;

    /**
     * Description  : 审批流程缓存,用户可对审批流程进行一定的自定义,此处保存自定义后流程的JSON序列化数据
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:text][SIZE:65535]
     */
    private String processJson;

}
