package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairDispatchInside;
import com.welearn.entity.qo.equipment.RepairDispatchInsideQueryCondition;
import com.welearn.entity.vo.response.equipment.RepairDispatchInfo;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : RepairDispatchInsideService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairDispatchInsideService extends BaseService<RepairDispatchInside, RepairDispatchInsideQueryCondition>{
    /**
     * 查询报修派工详情信息
     * @param queryCondition 查询条件
     * @return 报修派工详情列表
     */
    List<RepairDispatchInfo> searchInfo(RepairDispatchInsideQueryCondition queryCondition);

    /**
     * 报修派工
     * 允许同时给多个工程师派工, 当其中一个工程师领工后则取消其它工程师的派工
     * @param requestId 报修id
     * @param engineerIdList 派工工程师id列表
     */
    void createDispatch(@NotBlank String requestId, @NotEmpty List<String> engineerIdList);

    /**
     * 取消派工
     * 在派工未被领取前 或 工程师申请重派后 可调用, 并更新报修状态 至 0-待派工 或 1-待重派
     * @param dispatchInsideId 派工id
     * @param reason 取消派工原因
     */
    void cancelDispatch(@NotBlank String dispatchInsideId, String reason);

    /**
     * 申请重派
     * 收到派工信息时 工程师可主动申请重派
     * @param dispatchInsideId 派工id
     * @param reason 申请重派原因
     */
    void applyReDispatch(@NotBlank String dispatchInsideId, @NotBlank String reason);

    /**
     * 领取派工
     * 派工如同时发给多个工程师 则会取消其余的派工信息, 并更新报修状态 至 3-待维修
     * @param dispatchInsideId 派工id
     */
    void receiveDispatch(@NotBlank String dispatchInsideId);

    /**
     * 开始维修
     * @param dispatchInsideId 内部派工id
     * @param isTrue 报修是否属实
     * @param reason 取消原因
     */
    void startRepair(@NotBlank String dispatchInsideId, @NotNull Boolean isTrue, String reason);

    /**
     * 结束维修
     * @param dispatchInsideId 内部派工id
     * @param result 维修结果:0-维修成功 1-等待配件 2-维修失败
     * @param reason 选填 维修失败时 进行申请重派需该字段
     */
    void endRepair(@NotBlank String dispatchInsideId, @NotNull Integer result, String reason);

    /**
     * 继续维修
     * @param dispatchInsideId 内部派工id
     */
    void continueRepair(String dispatchInsideId);

    /**
     * 请求援助
     * 根据是否保修期内决定是厂商报修 还是 外部援助
     * @param dispatchInsideId 派工id
     */
    ApprovalApplication askHelp(@NotBlank String dispatchInsideId, String description, @EntityCheck(checkId = true) User user);

    /**
     * 维修验收成功
     * @param requestId 报修id
     */
    void check(String requestId, Boolean isSuccess);

    /**
     * 尝试取消内部派工申请的配件更换
     * @param dispatchInside 内部派工
     */
    void cancelDispatchInsideReplacement(RepairDispatchInside dispatchInside);
}