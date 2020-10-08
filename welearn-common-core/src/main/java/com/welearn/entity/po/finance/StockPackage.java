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
 * Persistent Object : ryme_finance : stock_package
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/18 16:19:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockPackage extends BasePersistant
{
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String remark;
    
    /**
     * Description  : 关联包装id, 在包装拆分或合并时会使用
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String rePackageId;
    
    /**
     * Description  : 包装创建时内含数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer amount;
    
    /**
     * Description  : 包装当前的内含数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer count;
    
    /**
     * Description  : 货物包装SSCC(与 id 一一对应)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    @NotBlank
    private String sscc;
    
    /**
     * Description  : 创建人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String creatorId;
    
    /**
     * Description  : 库存类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String stockTypeId;
    
    /**
     * Description  : 状态: 0-虚拟态(系统创建了该SSCC条码) 1-现实态(库存人员将该SSCC条码打印并贴在箱子上)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer status;
    
}
