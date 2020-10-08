package com.welearn.entity.qo.notify;

import com.welearn.entity.po.notify.SmsSendTemplate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : SmsSendTemplate Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/18 17:42:40
 * @see com.welearn.entity.po.notify.SmsSendTemplate
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "SmsSendTemplateQueryCondition", description = "SmsSendTemplate 查询条件")
public class SmsSendTemplateQueryCondition extends SmsSendTemplate {

}
