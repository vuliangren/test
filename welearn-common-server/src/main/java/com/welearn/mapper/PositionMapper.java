package com.welearn.mapper;

import com.welearn.entity.po.common.Position;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.PositionQueryCondition;
import com.welearn.entity.vo.response.common.PositionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Position Mapper Interface : ryme_common : position
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/13 10:55:58
 * @see com.welearn.entity.po.common.Position
 */
@Mapper 
public interface PositionMapper extends BaseMapper<Position, PositionQueryCondition> {

    /**
     * 公司职位信息查询
     * @param companyType 公司类型
     * @param companyId 公司id
     * @return 职位及关联用户信息
     */
    List<PositionInfo> companyPositionInfo(@Param("companyType") Integer companyType, @Param("companyId") String companyId);

    /**
     * 公司职位信息查询
     * @param userId 用户id
     * @return 职位及关联用户信息
     */
    List<PositionInfo> userPositionInfo(@Param("userId") String userId);

    /**
     * 获取用户的职位列表
     *
     * @param userId 用户id
     * @return 职位列表
     */
    List<Position> userPositions(@Param("userId") String userId);

    /**
     * 查找 职位关联 的人
     * @param companyId 公司id 必填
     * @param departmentId 部门id 选填
     * @param positionId 职位id 和 code 二选一
     * @param code code 和 职位id 二选一
     * @return 用户列表
     */
    List<User> userSearch(@Param("companyId") String companyId, @Param("departmentId") String departmentId,
                          @Param("positionId") String positionId, @Param("code") String code);

    /**
     * 查找可用的职位
     * @param companyId 公司id
     * @param departmentId 部门id
     * @return 职位列表
     */
    List<Position> available(@Param("visitorCompanyType") Integer visitorCompanyType, @Param("companyId") String companyId,
                             @Param("departmentId") String departmentId);
}