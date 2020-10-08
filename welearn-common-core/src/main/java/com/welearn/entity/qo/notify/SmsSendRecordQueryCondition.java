package com.welearn.entity.qo.notify;

import com.welearn.entity.po.notify.SmsSendRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : SmsSendRecord Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/18 17:42:38
 * @see com.welearn.entity.po.notify.SmsSendRecord
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "SmsSendRecordQueryCondition", description = "SmsSendRecord 查询条件")
public class SmsSendRecordQueryCondition extends SmsSendRecord {

}
