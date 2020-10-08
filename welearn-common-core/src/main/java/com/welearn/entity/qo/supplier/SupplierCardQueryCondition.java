package com.welearn.entity.qo.supplier;

import com.welearn.entity.po.supplier.SupplierCard;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : SupplierCard Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/8 18:20:24
 * @see com.welearn.entity.po.supplier.SupplierCard
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "SupplierCardQueryCondition", description = "SupplierCard 查询条件")
public class SupplierCardQueryCondition extends SupplierCard {

}
