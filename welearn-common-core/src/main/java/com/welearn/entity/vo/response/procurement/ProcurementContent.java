package com.welearn.entity.vo.response.procurement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description : 采购项目 中间生成的 待装箱内容
 * Created by Setsuna Jin on 2018/11/23.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcurementContent {
    private String id;
    private String name;
    private String description;
    private Integer count;
    private Integer remain;
    private String detailId;
    private String detailOutlook;
    private Integer type;
}
