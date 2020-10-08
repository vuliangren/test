package com.welearn.entity.qo.alink;

import com.welearn.entity.po.alink.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : Product Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:38:45
 * @see Product
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "ProductQueryCondition", description = "Product 查询条件")
public class ProductQueryCondition extends Product {

}
