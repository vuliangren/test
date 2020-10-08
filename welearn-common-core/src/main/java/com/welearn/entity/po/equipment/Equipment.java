package com.welearn.entity.po.equipment;

import java.util.Date;

import com.welearn.dictionary.equipment.ManagementLeverConst;
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
 * Persistent Object : ryme_equipment : equipment
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/5 10:23:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Equipment", description = "Equipment 领域实体")
public class Equipment extends BasePersistant
{

    /**
     * 当更新 产品信息 时 设备的以下信息会跟随 产品 变动
     * @param product 产品信息
     */
    public void updateWithProduct(EquipmentProduct product) {
        this.setCommodityName(product.getCommodityName());
        this.setEquipmentTypeId(product.getEquipmentTypeId());
        this.setEquipmentTypeName(product.getEquipmentTypeName());
        this.setManagementLever(product.getManagementLever());
        this.setSpecification(product.getSpecification());
        this.setModelNumber(product.getModelNumber());
        this.setManufacturerName(product.getManufacturerName());
        this.setIsImportProduct(product.getIsImportProduct());
        this.setIsLargeEquipment(product.getIsLargeEquipment());
        this.setIsSterileProvide(product.getIsSterileProvide());
        this.setShouldInspection(product.getIsInspection());
    }

    /**
     * 获取设备简述信息
     * @return 设备简述信息
     */
    public String description(){
        // UDI/[管理级别]设备类型-规格/生产厂商-型号
        return String.format("%s / [%s]%s-%s / %s-%s",
                this.getUdi(),
                ManagementLeverConst.values()[this.getManagementLever()].getLeverName(),
                this.getEquipmentTypeName(),
                this.getSpecification(),
                this.getManufacturerName(),
                this.getModelNumber());
    }

    /**
     * Description  : 统一设备编号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "统一设备编号", allowEmptyValue = true, position = 1 )
    private String udi;

    /**
     * Description  : 供应商id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "供应商id", allowEmptyValue = false, position = 5 )
    private String supplierId;

    /**
     * Description  : 位置id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "位置id", allowEmptyValue = true, position = 6 )
    private String locationId;

    /**
     * Description  : 采购项目id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "采购项目id", allowEmptyValue = true, position = 7 )
    private String procurementId;

    /**
     * Description  : 固定资产id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "固定资产id", allowEmptyValue = true, position = 8 )
    private String fixedAssetId;

    /**
     * Description  : 采购项目详情id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "采购项目详情id", allowEmptyValue = true, position = 9 )
    private String procurementDetailId;

    /**
     * Description  : 供应商设备产品id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "供应商设备产品id", allowEmptyValue = false, position = 10 )
    private String equipmentProductId;

    /**
     * Description  : 供应商设备产品数据快照
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "供应商设备产品数据快照", allowEmptyValue = true, position = 11 )
    private String equipmentProductJson;

    /**
     * Description  : 商品名称
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "商品名称", allowEmptyValue = false, position = 12 )
    private String commodityName;

    /**
     * Description  : 设备类型id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型id", allowEmptyValue = false, position = 13 )
    private String equipmentTypeId;

    /**
     * Description  : 设备类型名称
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型名称", allowEmptyValue = false, position = 14 )
    private String equipmentTypeName;

    /**
     * Description  : 管理级别:0-Ⅰ 1-Ⅱ 2-Ⅲ
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "管理级别:0-Ⅰ 1-Ⅱ 2-Ⅲ", allowEmptyValue = false, position = 15 )
    private Integer managementLever;

    /**
     * Description  : 设备规格
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备规格", allowEmptyValue = false, position = 16 )
    private String specification;

    /**
     * Description  : 设备型号
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备型号", allowEmptyValue = false, position = 17 )
    private String modelNumber;

    /**
     * Description  : 验收日期
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "验收日期", allowEmptyValue = true, position = 18 )
    private Date acceptanceCheckedAt;

    /**
     * Description  : 过保日期
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "过保日期", allowEmptyValue = true, position = 19 )
    private Date guaranteeRepairExpiredAt;

    /**
     * Description  : 生产厂商名称
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "生产厂商名称", allowEmptyValue = false, position = 20 )
    private String manufacturerName;

    /**
     * Description  : 无菌提供：0-否，1-是
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "无菌提供：0-否，1-是", allowEmptyValue = true, position = 21 )
    private Boolean isSterileProvide;

    /**
     * Description  : 进口产品：0-否，1-是
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "进口产品：0-否，1-是", allowEmptyValue = true, position = 22 )
    private Boolean isImportProduct;

    /**
     * Description  : 大型医疗设备：0-非大型医疗设备，1-甲类大型医疗设备 2-乙类大型医疗设备
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "大型医疗设备：0-非大型医疗设备，1-甲类大型医疗设备 2-乙类大型医疗设备", allowEmptyValue = true, position = 23 )
    private Integer isLargeEquipment;

    /**
     * Description  : 大型医疗设备装配许可证id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "大型医疗设备装配许可证id", allowEmptyValue = true, position = 24 )
    private String certificateId;

    /**
     * Description  : 设备状态: 0-待初始化, 1-空闲, 2-运行, 3-故障, 4-报废, 5-遗失, 6-维护, 7-封存
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "设备状态: 0-待初始化, 1-空闲, 2-运行, 3-故障, 4-报废, 5-遗失, 6-维护, 7-封存", allowEmptyValue = true, position = 25 )
    private Integer equipmentStatus;

    /**
     * Description  : 是否需要强检：0-不需要，1-需要
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否需要强检：0-不需要，1-需要", allowEmptyValue = true, position = 26 )
    private Boolean shouldInspection;

    /**
     * Description  : 生产序列号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "生产序列号", allowEmptyValue = true, position = 27 )
    private String serialNumber;

    /**
     * Description  : 功能状态:0-正常 1-降级使用 2-改造升级
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "功能状态:0-正常 1-降级使用 2-改造升级", allowEmptyValue = true, position = 28 )
    private Integer functionStatus;

    /**
     * Description  : 设备相关图片
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "设备相关图片", allowEmptyValue = true, position = 29 )
    private String photos;

    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 30 )
    private String remark;

    /**
     * Description  : 是否可借用：0-不可借用，1-可借用
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否可借用：0-不可借用，1-可借用", allowEmptyValue = true, position = 31 )
    private Boolean canBorrow;

    /**
     * Description  : 借用状态：0-正常 1-借用待审批 2-借用待归还
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "借用状态：0-正常 1-借用待审批 2-借用待归还", allowEmptyValue = true, position = 32 )
    private Integer borrowStatus;

    /**
     * Description  : 调剂状态：0-正常 1-调剂待审批 2-调剂待封存 3-调剂待处理
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "调剂状态：0-正常 1-调剂待审批 2-调剂待封存 3-调剂待处理", allowEmptyValue = true, position = 33 )
    private Integer adjustStatus;

    /**
     * Description  : 维修状态：0-正常 1-报修待派工 2-报修待领工 3-报修待维修 4-维修待验收
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "维修状态：0-正常 1-报修待派工 2-报修待领工 3-报修待维修 4-维修待验收", allowEmptyValue = true, position = 34 )
    private Integer repairStatus;

    /**
     * Description  : 维护状态：0-正常 1-维护待完成
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "维护状态：0-正常 1-维护待完成", allowEmptyValue = true, position = 35 )
    private Integer maintainStatus;

    /**
     * Description  : 报废状态：0-正常 1-报废待审批 2-报废待封存 3-报废待处理
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "报废状态：0-正常 1-报废待审批 2-报废待封存 3-报废待处理", allowEmptyValue = true, position = 36 )
    private Integer scrapStatus;

    /**
     * Description  : 生产日期
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "生产日期", allowEmptyValue = true, position = 37 )
    private Date producedAt;

}
