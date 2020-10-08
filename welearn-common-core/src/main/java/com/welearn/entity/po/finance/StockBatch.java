package com.welearn.entity.po.finance;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_finance : stock_batch
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:59:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockBatch extends BasePersistant
{
    /**
     * Description  : 库存id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String stockId;
    
    /**
     * Description  : 库存类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String stockTypeId;
    
    /**
     * Description  : 父批次id (批次追踪)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String parentId;
    
    /**
     * Description  : 包装单位
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String packageUnit;
    
    /**
     * Description  : 总量(内含数量)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer sumCount;
    
    /**
     * Description  : 余量(内含数量)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer realRemainCount;
    
    /**
     * Description  : 计划余量(内含数量, 默认与余量等值, 如有相关库存任务在处理时 被取出数量 则会从计划余量中扣除)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer planRemainCount;
    
    /**
     * Description  : 单价(内含数量)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:14]
     * DefaultValue : 0.00
     */
    @NotNull
    private Double price;
    
    /**
     * Description  : 订货耗时(天)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer orderCostDay;
    
    /**
     * Description  : 生产日期
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:timestamp]
     */
    private Date producedAt;
    
    /**
     * Description  : 保质期限(截止时间)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:timestamp]
     */
    private Date expiredAt;
    
    /**
     * Description  : 状态: 0-正常 1-封存中 2-待退换 3-待报废
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer status;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    private String remark;
    
    /**
     * Description  : 描述(必填)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:1024]
     */
    @NotBlank
    private String description;
    
    /**
     * Description  : 物品型号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    @NotBlank
    private String model;
    
    /**
     * Description  : 生产厂商名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    @NotBlank
    private String manufacturerName;
    
    /**
     * Description  : 采购项目id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String procurementId;
    
    /**
     * Description  : 供应商id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String supplierId;
    
}
