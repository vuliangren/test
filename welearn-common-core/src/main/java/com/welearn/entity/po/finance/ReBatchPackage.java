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
 * Persistent Object : ryme_finance : re_batch_package
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReBatchPackage extends BasePersistant
{
    /**
     * Description  : 批次id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String batchId;
    
    /**
     * Description  : 状态:0-在库 1-离库 2-出库
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer status;
    
    /**
     * Description  : 创建人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String creatorId;
    
    /**
     * Description  : 更新人id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String updateUserId;
    
    /**
     * Description  : 货物包装SSCC
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    @NotBlank
    private String sscc;
    
}
