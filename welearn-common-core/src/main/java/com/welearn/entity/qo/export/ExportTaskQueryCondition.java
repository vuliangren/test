package com.welearn.entity.qo.export;

import com.welearn.entity.po.export.ExportTask;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : ExportTask Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/11 11:39:02
 * @see com.welearn.entity.po.export.ExportTask
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "ExportTaskQueryCondition", description = "ExportTask 查询条件")
public class ExportTaskQueryCondition extends ExportTask {

}
