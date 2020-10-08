package com.welearn.entity.qo.finance;

import com.welearn.entity.po.finance.FixedAssets;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : FixedAssets Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/14 17:43:06
 * @see com.welearn.entity.po.finance.FixedAssets
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "FixedAssetsQueryCondition", description = "FixedAssets 查询条件")
public class FixedAssetsQueryCondition extends FixedAssets {

}
