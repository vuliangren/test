package com.welearn.entity.qo.finance;

import com.welearn.entity.po.finance.Stock;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description : Stock Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:52
 * @see Stock
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StockQueryCondition extends Stock {
    private Integer contentType;
    private String itemTypeId;
    private String specification;
}
