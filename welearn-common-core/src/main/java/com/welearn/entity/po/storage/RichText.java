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
 * Persistent Object : ryme_storage : rich_text
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/30 9:36:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RichText extends BasePersistant
{
    /**
     * Description  : 富文本编辑用raw字符串
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_storage:text][SIZE:65535]
     */
    private String raw;
    
    /**
     * Description  : 富文本展示用html字符串
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_storage:text][SIZE:65535]
     */
    private String html;
    
    /**
     * Description  : 创建人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_storage:varchar][SIZE:128]
     */
    @NotBlank
    private String creatorId;
    
    /**
     * Description  : 富文本内容类型简述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_storage:varchar][SIZE:512]
     */
    private String outlook;
    
}
