package com.welearn.entity.qo.finance;

import com.welearn.entity.po.finance.StockBatch;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : StockBatch Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:55
 * @see StockBatch
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StockBatchQueryCondition extends StockBatch {
    private Integer contentType;
    private String itemTypeId;
    private String specification;
    private Date sCreatedAt;
    private Date eCreatedAt;
}
