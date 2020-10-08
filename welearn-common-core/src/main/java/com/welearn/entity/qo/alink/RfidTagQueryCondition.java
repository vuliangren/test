package com.welearn.entity.qo.alink;

import com.welearn.entity.po.alink.RfidTag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : RfidTag Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/16 16:51:57
 * @see RfidTag
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "RfidTagQueryCondition", description = "RfidTag 查询条件")
public class RfidTagQueryCondition extends RfidTag {
    private String iotId;
}
