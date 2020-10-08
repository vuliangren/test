package com.welearn.entity.po.equipment;

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
 * Persistent Object : ryme_equipment : repair_request
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 11:05:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairRequest", description = "RepairRequest 领域实体")
public class RepairRequest extends BasePersistant
{
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 4 )
    private String equipmentId;
    
    /**
     * Description  : 设备名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备名称", allowEmptyValue = false, position = 5 )
    private String equipmentName;
    
    /**
     * Description  : 设备类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型id", allowEmptyValue = false, position = 6 )
    private String equipmentTypeId;
    
    /**
     * Description  : 设备类型名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型名称", allowEmptyValue = false, position = 7 )
    private String equipmentTypeName;
    
    /**
     * Description  : 过保日期
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "过保日期", allowEmptyValue = true, position = 8 )
    private Date guaranteeRepairExpiredAt;
    
    /**
     * Description  : 报修人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "报修人id", allowEmptyValue = false, position = 9 )
    private String reporterId;
    
    /**
     * Description  : 报修人名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "报修人名称", allowEmptyValue = false, position = 10 )
    private String reporterName;
    
    /**
     * Description  : 报修人电话
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:11]
     */
    @NotBlank
    @ApiModelProperty( value = "报修人电话", allowEmptyValue = false, position = 11 )
    private String reporterPhone;
    
    /**
     * Description  : 报修人部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "报修人部门id", allowEmptyValue = false, position = 12 )
    private String reporterDepartmentId;
    
    /**
     * Description  : 报修人部门名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "报修人部门名称", allowEmptyValue = false, position = 13 )
    private String reporterDepartmentName;
    
    /**
     * Description  : 报修人公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "报修人公司id", allowEmptyValue = false, position = 14 )
    private String reporterCompanyId;
    
    /**
     * Description  : 报修人公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "报修人公司名称", allowEmptyValue = false, position = 15 )
    private String reporterCompanyName;
    
    /**
     * Description  : 服务公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "服务公司id", allowEmptyValue = false, position = 16 )
    private String serveCompanyId;
    
    /**
     * Description  : 服务公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "服务公司名称", allowEmptyValue = false, position = 17 )
    private String serveCompanyName;
    
    /**
     * Description  : 故障预案
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "故障预案", allowEmptyValue = false, position = 18 )
    private String preceptId;
    
    /**
     * Description  : 故障描述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @NotBlank
    @ApiModelProperty( value = "故障描述", allowEmptyValue = false, position = 19 )
    private String description;
    
    /**
     * Description  : 故障图片
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "故障图片", allowEmptyValue = true, position = 20 )
    private String picture;
    
    /**
     * Description  : 紧急程度: 0-一般 1-严重 2-紧急
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "紧急程度: 0-一般 1-严重 2-紧急", allowEmptyValue = true, position = 21 )
    private Integer lever;
    
    /**
     * Description  : 优先级排序
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "优先级排序", allowEmptyValue = true, position = 22 )
    private Integer sort;
    
    /**
     * Description  : 状态: 0-待派工 1-待重派 2-待领工 3-待维修 4-维修中 5-待验收 6-待评价 7-已完成 8-待取消 9-已取消 10-已中止 11-待厂商维修 12-待外援维修
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-待派工 1-待重派 2-待领工 3-待维修 4-维修中 5-待验收 6-待评价 7-已完成 8-待取消 9-已取消 10-已中止 11-待厂商维修 12-待外援维修", allowEmptyValue = true, position = 23 )
    private Integer status;
    
    /**
     * Description  : 报修是否属实
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "报修是否属实", allowEmptyValue = true, position = 24 )
    private Boolean isReal;
    
    /**
     * Description  : 报修是否取消
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "报修是否取消", allowEmptyValue = true, position = 25 )
    private Boolean isCancel;
    
    /**
     * Description  : 是否更换配件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否更换配件", allowEmptyValue = true, position = 26 )
    private Boolean isPartRepalce;
    
    /**
     * Description  : 是否等待配件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否等待配件", allowEmptyValue = true, position = 27 )
    private Boolean isPartWaiting;
    
    /**
     * Description  : 是否申请外派
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否申请外派", allowEmptyValue = true, position = 28 )
    private Boolean isHelpApply;
    
    /**
     * Description  : 是否厂家保修
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否厂家保修", allowEmptyValue = true, position = 29 )
    private Boolean isWarranty;
    
    /**
     * Description  : 是否提交总结
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否提交总结", allowEmptyValue = true, position = 30 )
    private Boolean isSummarize;
    
    /**
     * Description  : 备注:取消说明/中止说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "备注:取消说明/中止说明", allowEmptyValue = true, position = 31 )
    private String remark;
    
    /**
     * Description  : 完成时间(用户确认维修完成的时间)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "完成时间(用户确认维修完成的时间)", allowEmptyValue = true, position = 32 )
    private Date finishedAt;
    
    /**
     * Description  : 是否建议报废
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否建议报废", allowEmptyValue = true, position = 33 )
    private Boolean isSuggestScrap;
    
    /**
     * Description  : 是否降级使用
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否降级使用", allowEmptyValue = true, position = 34 )
    private Boolean isDegrade;
    
}
