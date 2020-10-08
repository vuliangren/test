package com.welearn.entity.qo.common;

import com.welearn.entity.po.common.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : Tag Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/16 18:24:41
 * @see com.welearn.entity.po.common.Tag
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "TagQueryCondition", description = "Tag 查询条件")
public class TagQueryCondition extends Tag {

}
