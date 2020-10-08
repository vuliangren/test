package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.iot.model.v20180120.QueryDeviceEventDataResponse;
import com.welearn.dictionary.alink.DeviceGroupTypeConst;
import com.welearn.dictionary.alink.EventTypeConst;
import com.welearn.dictionary.notify.NoticeMethodConst;
import com.welearn.dictionary.notify.NoticeRefTypeConst;
import com.welearn.dictionary.notify.NoticeTypeConst;
import com.welearn.entity.po.alink.Device;
import com.welearn.entity.po.alink.DeviceEvent;
import com.welearn.entity.po.alink.DeviceGroup;
import com.welearn.entity.po.alink.ReDeviceGroupUser;
import com.welearn.entity.po.notify.Notice;
import com.welearn.entity.qo.alink.DeviceEventQueryCondition;
import com.welearn.entity.qo.alink.DeviceGroupQueryCondition;
import com.welearn.entity.vo.response.alink.DeviceEventTypeCount;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.notify.NoticeFeignClient;
import com.welearn.mapper.DeviceEventMapper;
import com.welearn.mapper.ReDeviceGroupUserMapper;
import com.welearn.service.DeviceEventService;
import com.welearn.service.DeviceGroupService;
import com.welearn.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Description : DeviceEventService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class DeviceEventServiceImpl extends BaseServiceImpl<DeviceEvent,DeviceEventQueryCondition,DeviceEventMapper>
        implements DeviceEventService{
    
    @Autowired
    private DeviceEventMapper deviceEventMapper;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ReDeviceGroupUserMapper reDeviceGroupUserMapper;

    @Autowired
    private NoticeFeignClient noticeFeignClient;

    @Override
    DeviceEventMapper getMapper() {
        return deviceEventMapper;
    }

    /**
     * 通过 JSON 格式的数据创建设备事件
     *
     * @param productKey     产品key
     * @param deviceName     设备名称
     * @param jsonData 事件信息
     */
    @Override
    public void createFromJsonData(String productKey, String deviceName, String jsonData) {
        Device device = deviceService.selectByProductKeyAndDeviceName(productKey, deviceName);
        if (Objects.isNull(device))
            throw new BusinessVerifyFailedException("无法找到匹配的设备信息");

        // 针对设备端提交的 被保护:无保护 的 ALERT 事件, 进行 降级 为 INFO 事件
        boolean isUnknownProtectEvent = jsonData.contains("无保护");
        jsonData = jsonData.replaceAll("无保护", "未知原因");

        QueryDeviceEventDataResponse.Data.EventInfo eventInfo = JSON.parseObject(jsonData, QueryDeviceEventDataResponse.Data.EventInfo.class);
        DeviceEvent event = new DeviceEvent();
        event.setIotId(device.getIotId());
        event.setProductKey(device.getProductKey());
        event.setDeviceName(device.getDeviceName());
        event.setNamePrefix(eventInfo.getName());
        event.setReportAt(new Date(Long.parseLong(eventInfo.getTime())));
        event.setIdentifier(eventInfo.getIdentifier());
        event.setDetailJson(eventInfo.getOutputData());
        // 解析事件详情
        JSONObject detail = JSON.parseObject(eventInfo.getOutputData());
        event.setName(detail.getString("ERROR_MESSAGE"));
        event.setNameSuffix(detail.getString("ERROR_PART"));
        // 设置事件类型
        EventTypeConst type = isUnknownProtectEvent ? EventTypeConst.INFO : EventTypeConst.getByStr(eventInfo.getEventType());
        event.setType(type.ordinal());
        if (!type.equals(EventTypeConst.INFO))
            event.setShouldHandle(true);
        // 创建设备事件
        this.create(event);
        // 判断是否需要发送通知
        if (!type.equals(EventTypeConst.INFO)) {
            this.sendEventNotice(device, event);
        }
    }

    /**
     * 根据设备和事件向相关处理组人员发送消息通知
     * @param device 设备
     * @param event 事件
     */
    @Async
    public void sendEventNotice(Device device, DeviceEvent event){
        List<ReDeviceGroupUser> reDeviceGroupUsers = reDeviceGroupUserMapper.selectByIotIdAndGroupType(device.getIotId(),
                DeviceGroupTypeConst.MAINTENANCE.ordinal());
        for (ReDeviceGroupUser reDeviceGroupUser : reDeviceGroupUsers) {
            Notice notice = new Notice();
            notice.setType(NoticeTypeConst.USER_NOTICE.ordinal());
            notice.setMethod(NoticeMethodConst.MESSAGE.ordinal());
            notice.setTitle(String.format("[%s] %s - %s", event.getNamePrefix(), event.getName(), device.getNickname()));
            notice.setReceiverId(reDeviceGroupUser.getUserId());
            notice.setReceiverName(reDeviceGroupUser.getUserName());
            notice.setRefId(device.getId());
            notice.setRefType(NoticeRefTypeConst.DEVICE.name());
            notice.setRemark(device.getDeviceName());
            noticeFeignClient.create(notice);
        }
    }

    /**
     * 根据 IotId 和 类型 统计事件数量
     *
     * @param typeGreaterThan 类型大于等于的值
     * @param shouldHandle    是否需要处理
     * @param handleStatus    处理状态
     * @param iotId           设备id
     * @return 统计数量结果
     */
    @Override
    public Map<String, Map<Integer, Long>> count(Integer typeGreaterThan, Boolean shouldHandle, Integer handleStatus, String iotId) {
        List<DeviceEventTypeCount> counts = deviceEventMapper.count(typeGreaterThan, shouldHandle, handleStatus, iotId);
        Map<String, Map<Integer, Long>> result = new HashMap<>();
        counts.forEach(count -> {
            Map<Integer, Long> items = result.get(count.getIotId());
            if (Objects.isNull(items)) {
                items = new HashMap<>();
                for (int i = typeGreaterThan; i< EventTypeConst.values().length; i++) {
                    items.put(i, 0L);
                }
                result.put(count.getIotId(), items);
            }
            items.put(count.getType(), count.getCount());
        });
        return result;
    }

    @Override
    public void updateAllNoNeedHandle(String iotId) {
        deviceEventMapper.updateAllNoNeedHandle(iotId);
    }
}
