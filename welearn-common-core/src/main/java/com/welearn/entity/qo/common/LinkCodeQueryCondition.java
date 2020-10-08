package com.welearn.entity.qo.common;

import com.welearn.entity.po.common.LinkCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : LinkCode Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/4/26 9:05:40
 * @see LinkCode
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "LinkCodeQueryCondition", description = "LinkCode 查询条件")
public class LinkCodeQueryCondition extends LinkCode {

}
