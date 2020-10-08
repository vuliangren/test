package com.welearn.entity.po.export;

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
 * Persistent Object : ryme_export : export_task
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/11 11:39:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ExportTask", description = "ExportTask 领域实体")
public class ExportTask extends BasePersistant
{
    /**
     * Description  : 任务接手时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "任务接手时间", allowEmptyValue = true, position = 4 )
    private Date receivedAt;
    
    /**
     * Description  : 任务完成时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:timestamp]
     */
    @ApiModelProperty( value = "任务完成时间", allowEmptyValue = true, position = 5 )
    private Date finishedAt;
    
    /**
     * Description  : 任务状态: 0-队列中 1-处理中 2-待上传 3-导出成功 4-导出失败
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "任务状态: 0-队列中 1-处理中 2-待上传 3-导出成功 4-导出失败", allowEmptyValue = true, position = 6 )
    private Integer status;
    
    /**
     * Description  : 导出类型: 0-模板导出 1-JSON导出 2-系统导出
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "导出类型: 0-模板导出 1-JSON导出 2-系统导出", allowEmptyValue = false, position = 7 )
    private Integer type;
    
    /**
     * Description  : 导出请求参数: 根据类型决定 JSON存储
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:mediumtext][SIZE:4194303]
     */
    @NotNull
    @ApiModelProperty( value = "导出请求参数: 根据类型决定 JSON存储", allowEmptyValue = false, position = 8 )
    private String argsJson;
    
    /**
     * Description  : 导出返回结果(一般为UploadRecord 对象)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:text][SIZE:16383]
     */
    @ApiModelProperty( value = "导出返回结果(一般为UploadRecord 对象)", allowEmptyValue = true, position = 9 )
    private String resultJson;
    
    /**
     * Description  : 导出结果文件大小(单位kb)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:int][PRECISION:10]
     */
    @ApiModelProperty( value = "导出结果文件大小(单位kb)", allowEmptyValue = true, position = 10 )
    private Integer resultSize;
    
    /**
     * Description  : 任务名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "任务名称", allowEmptyValue = false, position = 11 )
    private String name;
    
    /**
     * Description  : 任务创建人公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "任务创建人公司id", allowEmptyValue = false, position = 12 )
    private String companyId;
    
    /**
     * Description  : 任务创建人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "任务创建人id", allowEmptyValue = false, position = 13 )
    private String creatorId;
    
    /**
     * Description  : 任务创建人名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "任务创建人名称", allowEmptyValue = false, position = 14 )
    private String creatorName;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 15 )
    private String remark;
    
}
