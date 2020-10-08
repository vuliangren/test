package com.welearn.entity.qo.common;

import com.welearn.entity.po.common.Department;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description : Department Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/13 11:40:41
 * @see com.welearn.entity.po.common.Department
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DepartmentQueryCondition extends Department {
    private List<String> tagIds;
}
