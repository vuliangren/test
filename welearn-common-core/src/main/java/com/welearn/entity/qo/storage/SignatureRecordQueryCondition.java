package com.welearn.entity.qo.storage;

import com.welearn.entity.po.storage.SignatureRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : SignatureRecord Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/25 10:48:55
 * @see com.welearn.entity.po.storage.SignatureRecord
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "SignatureRecordQueryCondition", description = "SignatureRecord 查询条件")
public class SignatureRecordQueryCondition extends SignatureRecord {

}
