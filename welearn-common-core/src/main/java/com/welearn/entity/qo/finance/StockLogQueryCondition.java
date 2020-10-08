package com.welearn.entity.qo.finance;

import com.welearn.entity.po.finance.StockLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : StockLog Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:56
 * @see StockLog
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StockLogQueryCondition extends StockLog {
    private Date startTime;
    private Date endTime;
}
