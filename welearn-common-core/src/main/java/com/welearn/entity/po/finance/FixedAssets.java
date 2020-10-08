package com.welearn.entity.po.finance;

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
 * Persistent Object : ryme_finance : fixed_assets
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/14 17:43:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FixedAssets", description = "FixedAssets 领域实体")
public class FixedAssets extends BasePersistant
{
    /**
     * Description  : 物品类型: 0-医疗设备
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "物品类型: 0-医疗设备", allowEmptyValue = false, position = 4 )
    private Integer itemType;
    
    /**
     * Description  : 物品id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "物品id", allowEmptyValue = false, position = 5 )
    private String itemId;
    
    /**
     * Description  : 物品名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "物品名称", allowEmptyValue = false, position = 6 )
    private String itemName;
    
    /**
     * Description  : 物品规格
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "物品规格", allowEmptyValue = true, position = 7 )
    private String itemSpecification;
    
    /**
     * Description  : 物品型号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "物品型号", allowEmptyValue = false, position = 8 )
    private String itemModel;
    
    /**
     * Description  : 物品生产厂商名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "物品生产厂商名称", allowEmptyValue = false, position = 9 )
    private String itemManufacturer;
    
    /**
     * Description  : 固定资产编号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "固定资产编号", allowEmptyValue = true, position = 10 )
    private String no;
    
    /**
     * Description  : 固定资产类型: 0-生产用固定资产 1-非生产用固定资产 2-租出固定资产 3-未使用固定资产 4-不需用固定资产 5-融资租赁固定资产 6-接受捐赠固定资产
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "固定资产类型: 0-生产用固定资产 1-非生产用固定资产 2-租出固定资产 3-未使用固定资产 4-不需用固定资产 5-融资租赁固定资产 6-接受捐赠固定资产", allowEmptyValue = true, position = 11 )
    private Integer type;
    
    /**
     * Description  : 资产来源: 0-数据导入 1-采购添加 
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "资产来源: 0-数据导入 1-采购添加 ", allowEmptyValue = true, position = 12 )
    private Integer origin;
    
    /**
     * Description  : 状态: 0-使用中的固定资产 1-未使用的固定资产 2-不需用的固定资产
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-使用中的固定资产 1-未使用的固定资产 2-不需用的固定资产", allowEmptyValue = true, position = 13 )
    private Integer status;
    
    /**
     * Description  : 入账时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:timestamp]
     */
    @ApiModelProperty( value = "入账时间", allowEmptyValue = true, position = 14 )
    private Date accountingAt;
    
    /**
     * Description  : 原值
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "原值", allowEmptyValue = false, position = 15 )
    private Double originalValue;
    
    /**
     * Description  : 残值
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "残值", allowEmptyValue = false, position = 16 )
    private Double residualValue;
    
    /**
     * Description  : 预计净残值率(0-100.00)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 5.00
     */
    @ApiModelProperty( value = "预计净残值率(0-100.00)", allowEmptyValue = true, position = 17 )
    private Double estimatedNetSalvageRate;
    
    /**
     * Description  : 预计使用年限
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @ApiModelProperty( value = "预计使用年限", allowEmptyValue = true, position = 18 )
    private Integer expectedUsefulLife;
    
    /**
     * Description  : 预计总工作量
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @ApiModelProperty( value = "预计总工作量", allowEmptyValue = true, position = 19 )
    private Integer sumWorkloadExpectancy;
    
    /**
     * Description  : 是否折旧：0-不折旧，1-折旧
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否折旧：0-不折旧，1-折旧", allowEmptyValue = true, position = 20 )
    private Boolean isDepreciation;
    
    /**
     * Description  : 折旧方式: 0-平均年限折旧法Ⅰ 1-平均年限折旧法Ⅱ 2-年数总和法 3-双倍余额递减法 4-工作量法
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "折旧方式: 0-平均年限折旧法Ⅰ 1-平均年限折旧法Ⅱ 2-年数总和法 3-双倍余额递减法 4-工作量法", allowEmptyValue = true, position = 21 )
    private Integer depreciationMethod;
    
    /**
     * Description  : 已提月份
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "已提月份", allowEmptyValue = true, position = 22 )
    private Integer monthsOfDepreciation;
    
    /**
     * Description  : 累计折旧
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:14]
     * DefaultValue : 0.00
     */
    @ApiModelProperty( value = "累计折旧", allowEmptyValue = true, position = 23 )
    private Double accumulatedDepreciation;
    
    /**
     * Description  : 部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "部门id", allowEmptyValue = false, position = 24 )
    private String departmentId;
    
    /**
     * Description  : 部门名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "部门名称", allowEmptyValue = false, position = 25 )
    private String departmentName;
    
    /**
     * Description  : 资产位置
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "资产位置", allowEmptyValue = false, position = 26 )
    private String address;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "公司id", allowEmptyValue = false, position = 27 )
    private String companyId;
    
    /**
     * Description  : 公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "公司名称", allowEmptyValue = false, position = 28 )
    private String companyName;
    
    /**
     * Description  : 创建人id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "创建人id", allowEmptyValue = true, position = 29 )
    private String creatorId;
    
    /**
     * Description  : 创建人名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "创建人名称", allowEmptyValue = true, position = 30 )
    private String creatorName;
    
    /**
     * Description  : 是否入账确认：0-未确认 1-已确认
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否入账确认：0-未确认 1-已确认", allowEmptyValue = true, position = 31 )
    private Boolean isCheck;
    
    /**
     * Description  : 入账申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "入账申请id", allowEmptyValue = true, position = 32 )
    private String applyId;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 33 )
    private String remark;
    
}
