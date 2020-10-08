package com.welearn.service;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.alink.DeviceEvent;
import com.welearn.entity.qo.alink.DeviceEventQueryCondition;
import com.welearn.entity.vo.response.alink.DeviceEventTypeCount;
import com.welearn.generator.ControllerGenerator;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Description : DeviceEventService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface DeviceEventService extends BaseService<DeviceEvent, DeviceEventQueryCondition>{

    /**
     * 通过 JSON 格式的数据创建设备事件
     * @param productKey 产品key
     * @param deviceName 设备名称
     * @param base64JsonData 事件信息
     */
    void createFromJsonData(@NotBlank String productKey, @NotBlank String deviceName, @NotBlank String base64JsonData);

    /**
     * 根据 IotId 和 类型 统计事件数量
     * @param typeGreaterThan 类型大于等于的值
     * @param shouldHandle 是否需要处理
     * @param handleStatus 处理状态
     * @param iotId        设备id
     * @return 统计数量结果
     */
    Map<String, Map<Integer, Long>> count(@NotNull @Range(min = 0, max = 2) Integer typeGreaterThan,
                                          @NotNull Boolean shouldHandle,
                                          @NotNull @Range(min = 0, max = 2) Integer handleStatus,
                                          String iotId);

    /**
     * 标记为全部不需要处理
     * @param iotId 设备id
     */
    void updateAllNoNeedHandle(@NotBlank String iotId);
}