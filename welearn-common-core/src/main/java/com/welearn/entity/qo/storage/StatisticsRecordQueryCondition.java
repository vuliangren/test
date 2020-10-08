package com.welearn.entity.qo.storage;

import com.welearn.entity.po.storage.StatisticsRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : StatisticsRecord Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:39:11
 * @see com.welearn.entity.po.storage.StatisticsRecord
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "StatisticsRecordQueryCondition", description = "StatisticsRecord 查询条件")
public class StatisticsRecordQueryCondition extends StatisticsRecord {

}
