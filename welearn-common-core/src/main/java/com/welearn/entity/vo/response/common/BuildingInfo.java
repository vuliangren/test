package com.welearn.entity.vo.response.common;

import com.welearn.entity.po.common.Building;
import com.welearn.entity.po.common.Floor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/15.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BuildingInfo extends Building {
    private List<Floor> floors;

    public BuildingInfo(Building building){
        this.setCompanyId(building.getCompanyId());
        this.setCreatedAt(building.getCreatedAt());
        this.setCreatorId(building.getCreatorId());
        this.setDescription(building.getDescription());
        this.setId(building.getId());
        this.setIsEnable(building.getIsEnable());
        this.setName(building.getName());
        this.setUpdatedAt(building.getUpdatedAt());
    }
}
