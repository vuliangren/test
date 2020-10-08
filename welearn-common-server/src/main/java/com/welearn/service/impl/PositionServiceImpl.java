package com.welearn.service.impl;

import com.welearn.dictionary.common.PositionTypeConst;
import com.welearn.entity.po.common.Position;
import com.welearn.entity.po.common.ReUserPosition;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.PositionQueryCondition;
import com.welearn.entity.qo.common.ReUserPositionQueryCondition;
import com.welearn.entity.vo.response.common.PositionInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.PositionMapper;
import com.welearn.mapper.UserMapper;
import com.welearn.service.PositionService;
import com.welearn.service.ReUserPositionService;
import com.welearn.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description : PositionService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class PositionServiceImpl extends BaseServiceImpl<Position,PositionQueryCondition,PositionMapper>
        implements PositionService{
    
    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReUserPositionService reUserPositionService;

    @Override
    PositionMapper getMapper() {
        return positionMapper;
    }

    /**
     * 公司职位信息查询
     *
     * @param companyType 公司类型
     * @param companyId   公司id
     * @return 职位及关联用户信息
     */
    @Override
    public List<PositionInfo> companyPositionInfo(Integer companyType, String companyId) {
        return positionMapper.companyPositionInfo(companyType, companyId);
    }

    /**
     * 查找 企业 的 某个职位 的人
     * 查找 企业 某个部门 的 某个职位 的人
     * 查找 企业 特殊职位 的人
     *
     * @param companyId    公司id 必填
     * @param departmentId 部门id 选填
     * @param positionId   职位id 和 code 二选一 都为空则是此部门或公司用户
     * @param code         code 和 职位id 二选一 都为空则是此部门或公司用户
     * @return 用户列表
     */
    @Override
    public List<User> userSearch(String companyId, String departmentId, String positionId, String code) {
        return positionMapper.userSearch(companyId, departmentId, positionId, code);
    }

    /**
     * 查找可用的职位
     * 系统职位 + 公司职位(有公司id) + 部门职位(有部门id)
     * @param visitorCompanyType  公司类型
     * @param companyId  公司id
     * @param departmentId 部门id
     * @return 职位列表
     */
    @Override
    public List<Position> available(Integer visitorCompanyType, String companyId, String departmentId) {
        return positionMapper.available(visitorCompanyType, companyId, departmentId);
    }

    /**
     * 获取用户的职位列表
     *
     * @param userId 用户id
     * @return 职位列表
     */
    @Override
    public List<Position> userPositions(String userId) {
        return positionMapper.userPositions(userId);
    }

    /**
     * 给员工分配职位
     * @param userId       员工id
     * @param positionId   职位id
     * @param departmentId 部门id
     * @param operator     操作人id
     */
    @Override @Transactional
    public void allot(String userId, String departmentId, String positionId, User operator) throws BusinessVerifyFailedException {
        // 获取职位信息
        Position position = this.select(positionId);
        User user = userMapper.selectByPK(userId);
        // 验证记录
        if (Objects.isNull(position) || Objects.isNull(user))
            throw new BusinessVerifyFailedException("接口参数业务验证失败");
        boolean isDepartmentPosition = this.isDepartmentPosition(position.getType());
        // 检查是否已分配过
        ReUserPositionQueryCondition condition = new ReUserPositionQueryCondition();
        condition.setIsEnable(true);
        condition.setPositionId(positionId);
        condition.setUserId(userId);
        if (isDepartmentPosition)
            condition.setDepartmentId(departmentId);
        List<ReUserPosition> search = reUserPositionService.search(condition);
        if (Objects.nonNull(search) && !search.isEmpty())
            throw new BusinessVerifyFailedException("该用户已分配该职位");
        // 创建职务分配记录
        ReUserPosition reUserPosition = new ReUserPosition();
        reUserPosition.setCompanyId(user.getCompanyId());
        // 如果为部门类型则 携带部门id
        if (isDepartmentPosition)
            reUserPosition.setDepartmentId(departmentId);
        reUserPosition.setCreatorId(operator.getId());
        reUserPosition.setUserId(userId);
        reUserPosition.setAllotAt(new Date());
        reUserPosition.setPositionId(positionId);
        reUserPositionService.create(reUserPosition);
    }

    private boolean isDepartmentPosition(Integer type) {
        return type == PositionTypeConst.USER_DEFINED_POSITION_DEPARTMENT.ordinal() || type == PositionTypeConst.SYSTEM_DEFINED_POSITION_DEPARTMENT.ordinal();
    }

    /**
     * 给员工撤销职位
     * @param userId       员工id
     * @param departmentId   部门id
     * @param positionId   职位id
     * @param operator     操作人id
     */
    @Override @Transactional
    public void takeBack(String userId, String departmentId, String positionId, User operator) throws BusinessVerifyFailedException {
        // 获取职位信息
        Position position = this.select(positionId);
        if (Objects.isNull(position))
            throw new BusinessVerifyFailedException("positionId 非法");
        // 获取职务分配记录
        ReUserPositionQueryCondition condition = new ReUserPositionQueryCondition();
        condition.setUserId(userId);
        // 如果属于部门职位 (系统/用户 定义的) 则查询时带部门id
        if (this.isDepartmentPosition(position.getType()))
            condition.setDepartmentId(departmentId);
        condition.setPositionId(positionId);
        condition.setIsEnable(true);
        List<ReUserPosition> reUserPositions = reUserPositionService.search(condition);
        if (Objects.isNull(reUserPositions) || reUserPositions.size() != 1)
            throw new BusinessVerifyFailedException("接口参数业务验证失败");
        ReUserPosition reUserPosition = reUserPositions.get(0);
        // 禁用掉职务分配信息
        reUserPosition.setTakeBackAt(new Date());
        reUserPosition.setIsEnable(false);
        reUserPositionService.update(reUserPosition);
    }

    /**
     * 给员工撤销所有职位
     *
     * @param userId    员工id
     * @param companyId 公司id
     * @param operator  操作人id
     */
    @Override
    public void takeBackCompany(String userId, String companyId, User operator) {
        ReUserPositionQueryCondition condition = new ReUserPositionQueryCondition();
        condition.setIsEnable(true);
        condition.setUserId(userId);
        condition.setCompanyId(companyId);
        List<ReUserPosition> reUserPositions = reUserPositionService.search(condition);
        for (ReUserPosition reUserPosition : reUserPositions) {
            reUserPosition.setTakeBackAt(new Date());
            reUserPosition.setIsEnable(false);
            reUserPositionService.update(reUserPosition);
        }
        log.info("User:{} 撤销了 User:{} 的所有 公司:{} 职位", operator.getId(), userId, companyId);
    }

    /**
     * 给员工撤销所有部门职位
     *
     * @param userId       员工id
     * @param departmentId 部门id
     * @param operator     操作人id
     */
    @Override
    public void takeBackDepartment(String userId, String departmentId, User operator) {
        ReUserPositionQueryCondition condition = new ReUserPositionQueryCondition();
        condition.setIsEnable(true);
        condition.setUserId(userId);
        condition.setDepartmentId(departmentId);
        List<ReUserPosition> reUserPositions = reUserPositionService.search(condition);
        for (ReUserPosition reUserPosition : reUserPositions) {
            reUserPosition.setTakeBackAt(new Date());
            reUserPosition.setIsEnable(false);
            reUserPositionService.update(reUserPosition);
        }
        log.info("User:{} 撤销了 User:{} 的所有 部门:{} 职位", operator.getId(), userId, departmentId);
    }
}
