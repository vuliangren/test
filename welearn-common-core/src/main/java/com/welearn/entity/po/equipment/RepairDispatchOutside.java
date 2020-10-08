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
 * Persistent Object : ryme_equipment : repair_dispatch_outside
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/11 13:49:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairDispatchOutside", description = "RepairDispatchOutside 领域实体")
public class RepairDispatchOutside extends BasePersistant
{
    /**
     * Description  : 报修id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "报修id", allowEmptyValue = false, position = 4 )
    private String requestId;
    
    /**
     * Description  : 与服务方签订的合同id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "与服务方签订的合同id", allowEmptyValue = true, position = 5 )
    private String contractId;
    
    /**
     * Description  : 服务方公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "服务方公司id", allowEmptyValue = true, position = 6 )
    private String companyId;
    
    /**
     * Description  : 服务方公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "服务方公司名称", allowEmptyValue = false, position = 7 )
    private String companyName;
    
    /**
     * Description  : 工作时间段JSON
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "工作时间段JSON", allowEmptyValue = true, position = 8 )
    private String workTimeJson;
    
    /**
     * Description  : 维修方式: 0-上门维修 1-邮寄维修
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "维修方式: 0-上门维修 1-邮寄维修", allowEmptyValue = true, position = 9 )
    private Integer method;
    
    /**
     * Description  : 快递状态: 0-待寄件 1-已寄件 2-已确认 3-待收件 4-已收件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "快递状态: 0-待寄件 1-已寄件 2-已确认 3-待收件 4-已收件", allowEmptyValue = true, position = 10 )
    private Integer mailStatus;
    
    /**
     * Description  : 快递寄件信息
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "快递寄件信息", allowEmptyValue = true, position = 11 )
    private String mailSendInfo;
    
    /**
     * Description  : 快递收件信息
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "快递收件信息", allowEmptyValue = true, position = 12 )
    private String mailReceiveInfo;
    
    /**
     * Description  : 是否到达
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否到达", allowEmptyValue = true, position = 13 )
    private Boolean isArrive;
    
    /**
     * Description  : 是否完成
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否完成", allowEmptyValue = true, position = 14 )
    private Boolean isFinish;
    
    /**
     * Description  : 是否违约
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否违约", allowEmptyValue = true, position = 15 )
    private Boolean isBreach;
    
    /**
     * Description  : 维修结果:0-维修成功 1-等待配件 2-维修失败 3-已取消
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @ApiModelProperty( value = "维修结果:0-维修成功 1-等待配件 2-维修失败 3-已取消", allowEmptyValue = true, position = 16 )
    private Integer result;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 17 )
    private String remark;
    
}
