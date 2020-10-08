package com.welearn.entity.qo.common;

import com.welearn.entity.po.common.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * Description : Address Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/16 18:24:17
 * @see com.welearn.entity.po.common.Address
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "AddressQueryCondition", description = "Address 查询条件")
public class AddressQueryCondition extends Address {
    private List<String> ids;
}
