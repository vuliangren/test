package com.welearn.entity.po.storage;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_storage : upload_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/30 9:36:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UploadRecord extends BasePersistant
{
    /**
     * Description  : 文件名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_storage:varchar][SIZE:128]
     */
    private String name;
    
    /**
     * Description  : 文件大小
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_storage:int][PRECISION:10]
     */
    private Integer size;
    
    /**
     * Description  : 文件后缀
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:128]
     */
    private String suffix;
    
    /**
     * Description  : MIME类型
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:128]
     */
    private String mimeType;
    
    /**
     * Description  : 文件key
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:128]
     */
    private String key;
    
    /**
     * Description  : 文件hash
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_storage:varchar][SIZE:512]
     */
    private String hash;
    
    /**
     * Description  : 所在空间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_storage:varchar][SIZE:512]
     */
    private String bucket;
    
    /**
     * Description  : 创建人id
     * 可为空字符串, 当为空字符串时表示是未注册用户上传的图片, 如进行注册申请中的用户
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:128]
     */
    @NotNull
    private String creatorId;
    
}
