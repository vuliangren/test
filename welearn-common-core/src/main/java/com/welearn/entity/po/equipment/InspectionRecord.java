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
 * Persistent Object : ryme_equipment : inspection_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/12 14:55:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "InspectionRecord", description = "InspectionRecord 领域实体")
public class InspectionRecord extends BasePersistant
{
    /**
     * Description  : 计划id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "计划id", allowEmptyValue = false, position = 4 )
    private String inspectionPlanId;

    /**
     * Description  : 类型id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "类型id", allowEmptyValue = false, position = 5 )
    private String typeId;

    /**
     * Description  : 产品id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "产品id", allowEmptyValue = false, position = 5 )
    private String productId;
    
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 6 )
    private String equipmentId;
    
    /**
     * Description  : 设备所属公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备所属公司id", allowEmptyValue = false, position = 7 )
    private String companyId;
    
    /**
     * Description  : 证书编号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "证书编号", allowEmptyValue = true, position = 8 )
    private String no;
    
    /**
     * Description  : 委托方或申请方单位名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "委托方或申请方单位名称", allowEmptyValue = true, position = 9 )
    private String customerCompanyName;
    
    /**
     * Description  : 发出证书的单位名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "发出证书的单位名称", allowEmptyValue = true, position = 10 )
    private String inspectionCompanyName;
    
    /**
     * Description  : 被检定计量器具名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "被检定计量器具名称", allowEmptyValue = false, position = 11 )
    private String measuringDeviceName;
    
    /**
     * Description  : 型号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "型号", allowEmptyValue = false, position = 12 )
    private String model;
    
    /**
     * Description  : 规格
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "规格", allowEmptyValue = false, position = 13 )
    private String specification;
    
    /**
     * Description  : 制造厂商
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "制造厂商", allowEmptyValue = false, position = 14 )
    private String manufacturerName;
    
    /**
     * Description  : 出厂编号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "出厂编号", allowEmptyValue = true, position = 15 )
    private String serialNumber;
    
    /**
     * Description  : 该检定是否已处理
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "该检定是否已处理", allowEmptyValue = true, position = 16 )
    private Boolean isProcessed;
    
    /**
     * Description  : 检定结论是否合格
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "检定结论是否合格", allowEmptyValue = true, position = 17 )
    private Boolean isQualified;
    
    /**
     * Description  : 检定是否调修校准
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "检定是否调修校准", allowEmptyValue = true, position = 18 )
    private Boolean isAdjusted;
    
    /**
     * Description  : 超时期限
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "超时期限", allowEmptyValue = true, position = 19 )
    private Date timeoutAt;
    
    /**
     * Description  : 检定时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "检定时间", allowEmptyValue = true, position = 20 )
    private Date verifiedAt;
    
    /**
     * Description  : 有效期至
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "有效期至", allowEmptyValue = true, position = 21 )
    private Date expiredAt;
    
    /**
     * Description  : 准确度
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "准确度", allowEmptyValue = true, position = 22 )
    private String accuracy;
    
    /**
     * Description  : 等级
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "等级", allowEmptyValue = true, position = 23 )
    private String lever;
    
    /**
     * Description  : 测量范围
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "测量范围", allowEmptyValue = true, position = 24 )
    private String range;
    
    /**
     * Description  : 检定依据:[regulationName, regulationName...]
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     * DefaultValue : []
     */
    @ApiModelProperty( value = "检定依据:[regulationName, regulationName...]", allowEmptyValue = true, position = 25 )
    private String verificationRegulationsJson;
    
    /**
     * Description  : 检定证书(合格)/检定结果通知书(不合格)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "检定证书(合格)/检定结果通知书(不合格)", allowEmptyValue = true, position = 26 )
    private String verificationFile;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 27 )
    private String remark;
    
}
