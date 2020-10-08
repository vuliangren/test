package com.welearn.entity.po.notify;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_notify : email_html_template
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/27 10:16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmailHtmlTemplate extends BasePersistant
{
    /**
     * Description  : 模板类型
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:512]
     */
    private String code;
    
    /**
     * Description  : 模板名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:256]
     */
    private String name;
    
    /**
     * Description  : 模板描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:1024]
     */
    private String description;
    
    /**
     * Description  : 模板内容
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:text][SIZE:65535]
     */
    @NotBlank
    private String html;
    
    /**
     * Description  : 参数描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:text][SIZE:65535]
     */
    private String args;
    
}
