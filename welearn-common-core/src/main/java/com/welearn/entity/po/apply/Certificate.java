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
 * Persistent Object : ryme_apply : certificate
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/20 11:21:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Certificate extends BasePersistant
{
    /**
     * Description  : 是否是被替换的旧证书：0-否，1-是
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @NotNull
    private Boolean isReplaced;
    
    /**
     * Description  : 当前证书id 证书失效后补交重审时使用
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String currentId;
    
    /**
     * Description  : 编号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String no;
    
    /**
     * Description  : 类型
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String type;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String name;
    
    /**
     * Description  : 内容 Json 存储
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:8192]
     */
    @NotBlank
    private String content;
    
    /**
     * Description  : 状态: 0-审批中 1-审批通过 2-审批失败 3-证书过期
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     * DefaultValue : 0
     */
    private Integer status;
    
    /**
     * Description  : 颁发时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:timestamp]
     * DefaultValue : 0000-00-00 00:00:00
     */
    @NotNull
    private Date licenceDate;
    
    /**
     * Description  : 过期时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:timestamp]
     * DefaultValue : 0000-00-00 00:00:00
     */
    @NotNull
    private Date expirationDate;
    
    /**
     * Description  : 证书性质: 0-企业证书 1-部门证书 2-个人证书
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer nature;
    
    /**
     * Description  : 所属公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String companyId;
    
    /**
     * Description  : 所属个人id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String personId;
    
    /**
     * Description  : 所属个人名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String personName;
    
    /**
     * Description  : 所属部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String departmentId;
    
    /**
     * Description  : 所属部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String departmentName;
    
    /**
     * Description  : 复印件图片 Json存储多个图片链接
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:8192]
     */
    @NotBlank
    private String photocopy;
    
    /**
     * Description  : 证书添加申请id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String applicationId;
    
}
