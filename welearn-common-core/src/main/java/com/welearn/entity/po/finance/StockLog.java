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
 * Persistent Object : ryme_finance : stock_log
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/19 10:20:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockLog extends BasePersistant
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
     * Description  : 总购买量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer sumPurchaseCount;
    
    /**
     * Description  : 计划总消耗量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer sumPlanConsumeCount;
    
    /**
     * Description  : 实际总消耗量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer sumRealConsumeCount;
    
    /**
     * Description  : 平均订货耗时
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     */
    @NotNull
    private Double orderAvgCostDay;
    
    /**
     * Description  : 平均每日消耗
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     */
    @NotNull
    private Double dayAvgCountCost;
    
    /**
     * Description  : 库存持有成本占比(0-100.00)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 0.00
     */
    @NotNull
    private Double holdingCostRatio;
    
}
