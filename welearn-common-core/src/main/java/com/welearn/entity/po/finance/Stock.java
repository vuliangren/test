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
 * Persistent Object : ryme_finance : stock
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/19 10:20:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Stock extends BasePersistant
{
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String companyId;
    
    /**
     * Description  : 类型:0-公司仓库 1-部门仓库
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer type;
    
    /**
     * Description  : 父库存id 部门库存使用
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String parentId;
    
    /**
     * Description  : 部门id 用于标记属于哪个部门, 对于公司仓库为空
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String departmentId;
    
    /**
     * Description  : 库存类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String stockTypeId;
    
    /**
     * Description  : 在途入库数量(内含数量 表示将有货物运进来)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer inTransitInCount;
    
    /**
     * Description  : 在途出库数量(内含数量 表示将有货物运出去)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer inTransitOutCount;
    
    /**
     * Description  : 总购买量(内含数量)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer sumPurchaseCount;
    
    /**
     * Description  : 计划总消耗量(内含数量) 医院库存出库 到 部门库存时登记该值 (部门库存时则与总购买量一致)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer sumPlanConsumeCount;
    
    /**
     * Description  : 实际总消耗量(内含数量) 部门库存出库 时 登记该值
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer sumRealConsumeCount;
    
    /**
     * Description  : 最近一次实际消耗时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @NotNull
    private Date lastConsumeAt;
    
    /**
     * Description  : 安全库存量(加急订货耗时天数 * 平均日消耗量)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 0.00
     */
    @NotNull
    private Double safeCount;
    
    /**
     * Description  : 最高库存量(到达订货存量点时, 订购 (最高库存量 - 安全库存量)/包装内含数量 包装数量 的货物 )
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 0.00
     */
    @NotNull
    private Double maxCount;
    
    /**
     * Description  : 订货存量点(平均订货耗时天数 * 平均日消耗量 + 安全库存量)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 0.00
     */
    @NotNull
    private Double orderCount;
    
    /**
     * Description  : 平均订货耗时(天, 计算最近一年的平均值, 每月更新一次)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 0.00
     */
    @NotNull
    private Double orderAvgCostDay;
    
    /**
     * Description  : 平均每日消耗(内含数量, 计算最近三月的平均值, 每月更新一次)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 0.00
     */
    @NotNull
    private Double dayAvgCostCount;
    
    /**
     * Description  : 库存持有成本占比(0-100.00)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 0.00
     */
    @NotNull
    private Double holdingCostRatio;
    
    /**
     * Description  : 执行: 0-按计划执行(批量采购) 1-按实际执行(零库存, 但部门出库登记需及时)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 1
     */
    @NotNull
    private Integer executeType;
    
}
