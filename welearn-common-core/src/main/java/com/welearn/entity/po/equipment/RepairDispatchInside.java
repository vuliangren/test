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
 * Persistent Object : ryme_equipment : repair_dispatch_inside
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 19:46:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairDispatchInside", description = "RepairDispatchInside 领域实体")
public class RepairDispatchInside extends BasePersistant
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
     * Description  : 工程师id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师id", allowEmptyValue = false, position = 5 )
    private String engineerId;
    
    /**
     * Description  : 工程师姓名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师姓名", allowEmptyValue = false, position = 6 )
    private String engineerName;
    
    /**
     * Description  : 状态: 0-待领工 1-已领工 2-已重派
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-待领工 1-已领工 2-已重派", allowEmptyValue = true, position = 7 )
    private Integer status;
    
    /**
     * Description  : 领工时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    @ApiModelProperty( value = "领工时间", allowEmptyValue = true, position = 8 )
    private Date receivedAt;
    
    /**
     * Description  : 工作时间段JSON
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "工作时间段JSON", allowEmptyValue = true, position = 9 )
    private String workTimeJson;
    
    /**
     * Description  : 是否重派
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否重派", allowEmptyValue = true, position = 10 )
    private Boolean isReDispatch;
    
    /**
     * Description  : 申请重派说明(申请重派时填写)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "申请重派说明(申请重派时填写)", allowEmptyValue = true, position = 11 )
    private String reDispatchDescription;
    
    /**
     * Description  : 报修重派原因(重新派工时填写)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "报修重派原因(重新派工时填写)", allowEmptyValue = true, position = 12 )
    private String reDispatchReason;
    
}
