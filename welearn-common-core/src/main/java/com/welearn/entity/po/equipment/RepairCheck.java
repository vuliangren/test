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
 * Persistent Object : ryme_equipment : repair_check
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/6 16:12:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairCheck", description = "RepairCheck 领域实体")
public class RepairCheck extends BasePersistant
{
    /**
     * Description  : 报修id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "报修id", allowEmptyValue = false, position = 4 )
    private String requestId;
    
    /**
     * Description  : 内部派工id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "内部派工id", allowEmptyValue = false, position = 5 )
    private String dispatchId;
    
    /**
     * Description  : 工程师id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师id", allowEmptyValue = false, position = 6 )
    private String engineerId;
    
    /**
     * Description  : 工程师名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师名称", allowEmptyValue = false, position = 7 )
    private String engineerName;
    
    /**
     * Description  : 验收人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "验收人id", allowEmptyValue = false, position = 8 )
    private String acceptorId;
    
    /**
     * Description  : 验收人名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "验收人名称", allowEmptyValue = false, position = 9 )
    private String acceptorName;
    
    /**
     * Description  : 是否可用：0-失败，1-成功
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     */
    @NotNull
    @ApiModelProperty( value = "是否可用：0-失败，1-成功", allowEmptyValue = false, position = 10 )
    private Boolean result;
    
    /**
     * Description  : 备注(验收失败时的原因)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注(验收失败时的原因)", allowEmptyValue = true, position = 11 )
    private String remark;
    
    /**
     * Description  : 签名id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "签名id", allowEmptyValue = true, position = 12 )
    private String signatureId;
    
}
