package com.welearn.entity.po.storage;

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
 * Persistent Object : ryme_storage : statistics_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:39:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "StatisticsRecord", description = "StatisticsRecord 领域实体")
public class StatisticsRecord extends BasePersistant
{
    /**
     * Description  : 统计开始时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "统计开始时间", allowEmptyValue = true, position = 4 )
    private Date startAt;
    
    /**
     * Description  : 统计结束时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "统计结束时间", allowEmptyValue = true, position = 5 )
    private Date endAt;
    
    /**
     * Description  : 统计所在服务
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "统计所在服务", allowEmptyValue = false, position = 6 )
    private String service;
    
    /**
     * Description  : 统计数据类型
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "统计数据类型", allowEmptyValue = false, position = 7 )
    private String type;
    
    /**
     * Description  : 统计数据关联id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "统计数据关联id", allowEmptyValue = false, position = 8 )
    private String refId;
    
    /**
     * Description  : 统计数据JSON格式存储
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "统计数据JSON格式存储", allowEmptyValue = false, position = 9 )
    private String dataJson;
    
    /**
     * Description  : 备注
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:256]
     */
    @NotBlank
    @ApiModelProperty( value = "备注", allowEmptyValue = false, position = 10 )
    private String remark;
    
}
