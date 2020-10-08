package com.welearn.service.impl;

import com.welearn.entity.po.common.Building;
import com.welearn.entity.po.common.Floor;
import com.welearn.entity.po.common.Room;
import com.welearn.entity.qo.common.BuildingQueryCondition;
import com.welearn.entity.qo.common.FloorQueryCondition;
import com.welearn.entity.qo.common.RoomQueryCondition;
import com.welearn.entity.vo.response.common.BuildingInfo;
import com.welearn.entity.vo.response.common.Option;
import com.welearn.mapper.BuildingMapper;
import com.welearn.service.BuildingService;
import com.welearn.service.FloorService;
import com.welearn.service.RoomService;
import com.welearn.util.PaginateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description : BuildingService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class BuildingServiceImpl extends BaseServiceImpl<Building,BuildingQueryCondition,BuildingMapper>
        implements BuildingService{
    
    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private FloorService floorService;

    @Autowired
    private RoomService roomService;

    @Override
    BuildingMapper getMapper() {
        return buildingMapper;
    }

    /**
     * 分页查询建筑信息
     *
     * @param companyId 公司id
     * @return 建筑信息列表
     */
    @Override
    public List<BuildingInfo> searchInfo(String companyId) {
        BuildingQueryCondition condition = new BuildingQueryCondition();
        condition.setCompanyId(companyId);
        condition.setIsEnable(true);
        // 分页查询
        List<Building> buildings = PaginateUtil.page(()->this.search(condition));
        // 获取楼层信息
        return buildings.stream().map(building -> {
            BuildingInfo buildingInfo = new BuildingInfo(building);
            FloorQueryCondition floorQueryCondition = new FloorQueryCondition();
            floorQueryCondition.setBuildingId(building.getId());
            floorQueryCondition.setCompanyId(companyId);
            floorQueryCondition.setIsEnable(true);
            buildingInfo.setFloors(floorService.search(floorQueryCondition));
            return buildingInfo;
        }).collect(Collectors.toList());
    }

    /**
     * 分页查询建筑信息 用于 Option 选择使用
     *
     * @param companyId 公司id
     * @return 建筑信息列表
     */
    @Override
    public List<Option<String, String>> searchOptionInfo(String companyId) {
        // 获取所有建筑信息
        BuildingQueryCondition buildingCondition = new BuildingQueryCondition();
        buildingCondition.setCompanyId(companyId);
        buildingCondition.setIsEnable(true);
        List<Building> buildings = this.search(buildingCondition);
        // 获取所有楼层信息
        FloorQueryCondition floorCondition = new FloorQueryCondition();
        floorCondition.setIsEnable(true);
        floorCondition.setCompanyId(companyId);
        List<Floor> floors = floorService.search(floorCondition);
        // 获取所有房间信息
        RoomQueryCondition roomCondition = new RoomQueryCondition();
        roomCondition.setCompanyId(companyId);
        roomCondition.setIsEnable(true);
        List<Room> rooms = roomService.search(roomCondition);
        return buildings.stream().map(building -> new Option<>(building.getId(), building.getName(), floors.stream()
                .filter(floor -> floor.getBuildingId().equals(building.getId())).map(floor -> new Option<>(floor.getId(), floor.getName(), rooms.stream()
                        .filter(room -> room.getFloorId().equals(floor.getId())).map(room -> new Option<>(room.getId(), room.getName(), null))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
