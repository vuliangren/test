package com.welearn.entity.qo.common;

import com.welearn.entity.po.common.Position;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : Position Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/17 14:50:54
 * @see com.welearn.entity.po.common.Position
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "PositionQueryCondition", description = "Position 查询条件")
public class PositionQueryCondition extends Position {

    private Boolean isSystemDefined;
}
