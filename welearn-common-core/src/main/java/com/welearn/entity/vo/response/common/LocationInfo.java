package com.welearn.entity.vo.response.common;

import com.welearn.entity.dto.common.IdAndName;
import com.welearn.entity.po.common.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfo implements Serializable {
    private IdAndName company;
    private IdAndName area;
    private IdAndName address;
    private IdAndName building;
    private IdAndName floor;
    private IdAndName room;

    // 调用 toString 得到的位置拼接信息
    private String outlook;

    @Override
    public String toString() {
        List<String> detail = new ArrayList<>();
        List<String> location = new ArrayList<>();
        if (Objects.nonNull(building))
            detail.add(building.getName());
        if (Objects.nonNull(floor))
            detail.add(floor.getName());
        if (Objects.nonNull(room))
            detail.add(room.getName());
        if (!detail.isEmpty())
            location.add(StringUtils.join(detail, " - "));
        if (Objects.nonNull(company))
            location.add(company.getName());
        if (Objects.nonNull(address))
            location.add(address.getName());
        // 楼 - 层 - 部门 / 公司 / 地址
        return StringUtils.join(location, " / ");
    }
}
