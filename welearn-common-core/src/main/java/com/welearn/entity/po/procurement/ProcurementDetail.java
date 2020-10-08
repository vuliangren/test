package com.welearn.entity.po.procurement;

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
 * Persistent Object : ryme_procurement : procurement_detail
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/31 17:38:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProcurementDetail", description = "ProcurementDetail 领域实体")
public class ProcurementDetail extends BasePersistant
{
    /**
     * Description  : 类别:0-医疗设备 1-医用耗材 2-设备配件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "类别:0-医疗设备 1-医用耗材 2-设备配件", allowEmptyValue = false, position = 4 )
    private Integer classification;
    
    /**
     * Description  : 类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "类型id", allowEmptyValue = false, position = 5 )
    private String typeId;
    
    /**
     * Description  : 类型名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "类型名称", allowEmptyValue = false, position = 6 )
    private String typeName;
    
    /**
     * Description  : 生产厂商
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "生产厂商", allowEmptyValue = true, position = 7 )
    private String manufacturer;
    
    /**
     * Description  : 原生产地
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "原生产地", allowEmptyValue = true, position = 8 )
    private String nativeOrigin;
    
    /**
     * Description  : 规格
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "规格", allowEmptyValue = false, position = 9 )
    private String specification;
    
    /**
     * Description  : 型号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "型号", allowEmptyValue = true, position = 10 )
    private String model;
    
    /**
     * Description  : 数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "数量", allowEmptyValue = false, position = 11 )
    private Integer count;
    
    /**
     * Description  : 预计单价
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:decimal][SCALE:2][PRECISION:14]
     */
    @ApiModelProperty( value = "预计单价", allowEmptyValue = true, position = 12 )
    private Double expectedPrice;
    
    /**
     * Description  : 实际单价
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:decimal][SCALE:2][PRECISION:14]
     */
    @ApiModelProperty( value = "实际单价", allowEmptyValue = true, position = 13 )
    private Double actualPrice;
    
    /**
     * Description  : 合计金额
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:decimal][SCALE:2][PRECISION:14]
     */
    @ApiModelProperty( value = "合计金额", allowEmptyValue = true, position = 14 )
    private Double sumPrice;
    
    /**
     * Description  : 保修期限(单位:月)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     */
    @ApiModelProperty( value = "保修期限(单位:月)", allowEmptyValue = true, position = 15 )
    private Integer guaranteeTime;
    
    /**
     * Description  : 资金来源
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "资金来源", allowEmptyValue = false, position = 16 )
    private Integer capitalSource;
    
    /**
     * Description  : 申请人科室id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人科室id", allowEmptyValue = false, position = 17 )
    private String departmentId;
    
    /**
     * Description  : 申请人科室名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人科室名称", allowEmptyValue = false, position = 18 )
    private String departmentName;
    
    /**
     * Description  : 申请人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人id", allowEmptyValue = false, position = 19 )
    private String applicantId;
    
    /**
     * Description  : 申请人姓名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "申请人姓名", allowEmptyValue = false, position = 20 )
    private String applicantName;
    
    /**
     * Description  : 状态:0-待进行采购 1-采购进行中 2-已完成采购
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态:0-待进行采购 1-采购进行中 2-已完成采购", allowEmptyValue = true, position = 21 )
    private Integer status;
    
    /**
     * Description  : 采购项目id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "采购项目id", allowEmptyValue = true, position = 22 )
    private String procurementId;
    
    /**
     * Description  : 供应商设备产品id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "供应商设备产品id", allowEmptyValue = true, position = 23 )
    private String productId;
    
    /**
     * Description  : 采购方公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "采购方公司id", allowEmptyValue = false, position = 24 )
    private String companyId;
    
    /**
     * Description  : 申请id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "申请id", allowEmptyValue = false, position = 25 )
    private String applicationId;
    
    /**
     * Description  : 申请类型编码
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "申请类型编码", allowEmptyValue = false, position = 26 )
    private String applicationType;
    
    /**
     * Description  : 采购详情的特殊内容 JSON存储
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:text][SIZE:16383]
     */
    @ApiModelProperty( value = "采购详情的特殊内容 JSON存储", allowEmptyValue = true, position = 27 )
    private String contentJson;
    
}
