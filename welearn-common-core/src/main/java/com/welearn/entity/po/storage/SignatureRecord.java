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
 * Persistent Object : ryme_storage : signature_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/25 10:48:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SignatureRecord", description = "SignatureRecord 领域实体")
public class SignatureRecord extends BasePersistant
{
    /**
     * Description  : 用户id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "用户id", allowEmptyValue = false, position = 4 )
    private String userId;
    
    /**
     * Description  : 用户名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "用户名称", allowEmptyValue = false, position = 5 )
    private String userName;
    
    /**
     * Description  : 签字文件(DataUrl形式的PNG图片)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "签字文件(DataUrl形式的PNG图片)", allowEmptyValue = false, position = 6 )
    private String signature;
    
}
