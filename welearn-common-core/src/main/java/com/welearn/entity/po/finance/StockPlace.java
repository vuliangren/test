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
 * Persistent Object : ryme_finance : stock_place
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockPlace extends BasePersistant
{
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String companyId;

    /**
     * Description  : 公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String departmentId;
    
    /**
     * Description  : 建筑id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String buildingId;
    
    /**
     * Description  : 楼层id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String floorId;
    
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
    
    /**
     * Description  : 类型:0-公司仓库 1-部门仓库
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer type;
    
}
