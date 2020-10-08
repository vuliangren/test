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
 * Persistent Object : ryme_equipment : equipment_product
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/7 10:24:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "EquipmentProduct", description = "EquipmentProduct 领域实体")
public class EquipmentProduct extends BasePersistant
{
    /**
     * Description  : 商品名称
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "商品名称", allowEmptyValue = false, position = 4 )
    private String commodityName;

    /**
     * Description  : 设备类型id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型id", allowEmptyValue = false, position = 5 )
    private String equipmentTypeId;

    /**
     * Description  : 设备类型名称
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型名称", allowEmptyValue = false, position = 6 )
    private String equipmentTypeName;

    /**
     * Description  : 管理级别:0-Ⅰ 1-Ⅱ 2-Ⅲ
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "管理级别:0-Ⅰ 1-Ⅱ 2-Ⅲ", allowEmptyValue = false, position = 7 )
    private Integer managementLever;

    /**
     * Description  : 设备规格
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备规格", allowEmptyValue = false, position = 8 )
    private String specification;

    /**
     * Description  : 设备型号
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备型号", allowEmptyValue = false, position = 9 )
    private String modelNumber;

    /**
     * Description  : 生产厂商名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "生产厂商名称", allowEmptyValue = true, position = 10 )
    private String manufacturerName;

    /**
     * Description  : DI(GTIN)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "DI(GTIN)", allowEmptyValue = true, position = 11 )
    private String gtin;

    /**
     * Description  : 品牌
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "品牌", allowEmptyValue = true, position = 12 )
    private String brand;

    /**
     * Description  : 无菌提供：0-否，1-是
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "无菌提供：0-否，1-是", allowEmptyValue = true, position = 13 )
    private Boolean isSterileProvide;

    /**
     * Description  : 进口产品：0-否，1-是
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "进口产品：0-否，1-是", allowEmptyValue = true, position = 14 )
    private Boolean isImportProduct;

    /**
     * Description  : 大型医疗设备：0-非大型医疗设备，1-甲类大型医疗设备 2-乙类大型医疗设备
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "大型医疗设备：0-非大型医疗设备，1-甲类大型医疗设备 2-乙类大型医疗设备", allowEmptyValue = true, position = 15 )
    private Integer isLargeEquipment;

    /**
     * Description  : 预计使用年限
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @ApiModelProperty( value = "预计使用年限", allowEmptyValue = true, position = 16 )
    private Integer expectedUsefulLife;

    /**
     * Description  : 预计总工作量
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @ApiModelProperty( value = "预计总工作量", allowEmptyValue = true, position = 17 )
    private Integer sumWorkloadExpectancy;

    /**
     * Description  : 技术指标
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "技术指标", allowEmptyValue = true, position = 18 )
    private String technicalIndicatorJson;

    /**
     * Description  : 功能描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "功能描述", allowEmptyValue = true, position = 19 )
    private String functionsJson;

    /**
     * Description  : 医疗器械注册证id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "医疗器械注册证id", allowEmptyValue = true, position = 20 )
    private String mdRegistrationCertificateId;

    /**
     * Description  : 医疗器械生产企业许可证id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "医疗器械生产企业许可证id", allowEmptyValue = true, position = 21 )
    private String mdProductionCertificateId;

    /**
     * Description  : 其它证明复印件
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "其它证明复印件", allowEmptyValue = true, position = 22 )
    private String otherCertificatePhotoCopy;

    /**
     * Description  : 备件清单
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "备件清单", allowEmptyValue = false, position = 23 )
    private String sparePartsListJson;

    /**
     * Description  : 附件清单
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "附件清单", allowEmptyValue = false, position = 24 )
    private String accessoryListJson;

    /**
     * Description  : 主要部件
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "主要部件", allowEmptyValue = false, position = 25 )
    private String mainComponentsJson;

    /**
     * Description  : 设备主体拆分说明
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "设备主体拆分说明", allowEmptyValue = false, position = 26 )
    private String deliveryComponentsJson;

    /**
     * Description  : 电子文档
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "电子文档", allowEmptyValue = false, position = 27 )
    private String electronicDocumentStorage;

    /**
     * Description  : 安装类型：0-设备无须进行安装，1-销售方负责安装(不含运行环境施工) 2-销售方负责安装(包含运行环境施工) 3-购买方按说明自行安装
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "安装类型：0-设备无须进行安装，1-销售方负责安装(不含运行环境施工) 2-销售方负责安装(包含运行环境施工) 3-购买方按说明自行安装", allowEmptyValue = true, position = 28 )
    private Integer installType;

    /**
     * Description  : 安装说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "安装说明", allowEmptyValue = true, position = 29 )
    private String installationInfoId;

    /**
     * Description  : 运行环境说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "运行环境说明", allowEmptyValue = true, position = 30 )
    private String environmentInfoId;

    /**
     * Description  : 维护说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "维护说明", allowEmptyValue = true, position = 31 )
    private String maintenanceInfoId;

    /**
     * Description  : 维修说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "维修说明", allowEmptyValue = true, position = 32 )
    private String repairInfo;

    /**
     * Description  : 维修密码
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "维修密码", allowEmptyValue = true, position = 33 )
    private String maintenancePassword;

    /**
     * Description  : 故障代码表
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "故障代码表", allowEmptyValue = false, position = 34 )
    private String faultCodeMeterJson;

    /**
     * Description  : 产品描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "产品描述", allowEmptyValue = true, position = 35 )
    private String descriptionId;

    /**
     * Description  : 使用说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "使用说明", allowEmptyValue = true, position = 36 )
    private String instructionId;

    /**
     * Description  : 特别说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "特别说明", allowEmptyValue = true, position = 37 )
    private String warningInfoId;

    /**
     * Description  : 存放说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "存放说明", allowEmptyValue = true, position = 38 )
    private String storageInfoId;

    /**
     * Description  : 存放说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "存放说明", allowEmptyValue = true, position = 39 )
    private String wasteTreatmentInfoId;

    /**
     * Description  : 废物分类
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @ApiModelProperty( value = "废物分类", allowEmptyValue = true, position = 40 )
    private Integer wasteClassification;

    /**
     * Description  : 其它说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "其它说明", allowEmptyValue = true, position = 41 )
    private String otherInfoId;

    /**
     * Description  : 供应商公司id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "供应商公司id", allowEmptyValue = false, position = 42 )
    private String companyId;

    /**
     * Description  : 状态:0-审批中 1-审批失败 2-审批通过 3-未上架 4-已上架
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @ApiModelProperty( value = "状态:0-审批中 1-审批失败 2-审批通过 3-未上架 4-已上架", allowEmptyValue = true, position = 43 )
    private Integer status;

    /**
     * Description  : 当前产品id 对产品进行修改时使用
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "当前产品id 对产品进行修改时使用", allowEmptyValue = true, position = 44 )
    private String currentId;

    /**
     * Description  : 申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "申请id", allowEmptyValue = true, position = 45 )
    private String applicationId;

    /**
     * Description  : 产品相关图片
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "产品相关图片", allowEmptyValue = true, position = 46 )
    private String photos;

    /**
     * Description  : 强检设备：0-否，1-是
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "强检设备：0-否，1-是", allowEmptyValue = true, position = 47 )
    private Boolean isInspection;

    /**
     * Description  : 供应方公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "供应方公司id", allowEmptyValue = true, position = 48 )
    private String supplierCompanyId;

    /**
     * Description  : 供应方公司名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "供应方公司名称", allowEmptyValue = true, position = 49 )
    private String supplierCompanyName;
}
