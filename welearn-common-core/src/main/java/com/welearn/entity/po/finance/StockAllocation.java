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
 * Persistent Object : ryme_finance : stock_allocation
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockAllocation extends BasePersistant
{
    /**
     * Description  : 库存仓库id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String stockPlaceId;
    
    /**
     * Description  : 坐标系x
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer x;
    
    /**
     * Description  : 坐标系y
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer y;
    
    /**
     * Description  : 坐标系z
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer z;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    @NotBlank
    private String name;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    private String description;
    
}
