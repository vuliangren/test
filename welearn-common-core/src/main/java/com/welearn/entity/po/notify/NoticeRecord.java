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
 * Persistent Object : ryme_notify : notice_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/11 15:11:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NoticeRecord extends BasePersistant
{
    /**
     * Description  : 用户id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @NotBlank
    private String userId;
    
    /**
     * Description  : 用户名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:256]
     */
    @NotBlank
    private String userName;
    
    /**
     * Description  : 公告id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @NotBlank
    private String noticeId;
    
    /**
     * Description  : 站内信是否已读：0-未读，1-已读
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:tinyint][PRECISION:3]
     * DefaultValue : 1
     */
    @NotNull
    private Boolean isViewed;
    
}
