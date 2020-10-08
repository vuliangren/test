package com.welearn.alink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.welearn.dictionary.alink.DeviceStatusConst;
import com.welearn.dictionary.alink.EventTypeConst;
import com.welearn.entity.po.alink.Device;
import com.welearn.entity.po.alink.DeviceEvent;
import com.welearn.entity.qo.alink.DeviceQueryCondition;
import com.welearn.service.DeviceEventService;
import com.welearn.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description : 阿里物联云服务器订阅
 * 因消息存在一定的延时性, 延时大于5秒, 故将改为服务器定时主动请求阿里云获取设备状态信息
 * Created by Setsuna Jin on 2019/5/31.
 * 暂时停用此类的使用
 * Created by Setsuna Jin on 2019/6/09.
 */
@Slf4j
//@Service
public class ServerSubscription {

    @Value("${debug}")
    private boolean isInDebugModel;

    @Autowired
    private MessageClient messageClient;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceEventService deviceEventService;

    private static final Map<String, Long> LastUpdatedAtFlag = new ConcurrentHashMap<>();

//    @PostConstruct
    public void postConstruct(){
        if (isInDebugModel)
            return;
        log.info("阿里云物联网平台 服务订阅 初始化执行");
        // 监听 设备属性上报
        messageClient.setMessageListener("/a1WLPTsAths/#/thing/event/property/post", (message) -> {
            String payload = new String(message.getMessage().getPayload());
            try {
                this.handleDevicePropertyPost(JSONObject.parseObject(payload));
            } catch (Exception e) {
                log.error("阿里云物联网平台 服务订阅 设备属性上报 消息消费失败", e);
                return MessageCallback.Action.CommitFailure;
            }
            return MessageCallback.Action.CommitSuccess;
        });

        // 监听 设备事件上报
        messageClient.setMessageListener("/a1WLPTsAths/#/thing/event/#/post", (message) -> {
            String payload = new String(message.getMessage().getPayload());
            try {
                this.handleDeviceEventPost(JSONObject.parseObject(payload));
            } catch (Exception e) {
                log.error("阿里云物联网平台 服务订阅 设备事件上报 消息消费失败", e);
                return MessageCallback.Action.CommitFailure;
            }
            return MessageCallback.Action.CommitSuccess;
        });

        // 监听 设备状态变动
        messageClient.setMessageListener("/as/mqtt/status/a1WLPTsAths/#", (message) -> {
            String payload = new String(message.getMessage().getPayload());
            try {
                log.info("设备状态改变通知: {}", payload);
                this.handleDeviceStatusChange(JSONObject.parseObject(payload));
            } catch (Exception e) {
                log.error("阿里云物联网平台 服务订阅 设备状态变动 消息消费失败", e);
                return MessageCallback.Action.CommitFailure;
            }
            return MessageCallback.Action.CommitSuccess;
        });

        // 开始创建连接 并设置通用消息监听器
        messageClient.connect((message) -> {
            log.info("暂不支持处理的消息: TOPIC:{} \n PAYLOAD: {}", message.getMessage().getTopic(), new String(message.getMessage().getPayload()));
            return MessageCallback.Action.CommitSuccess;
        });
    }

    /**
     * 处理设备属性上报
     * payload JSON 格式: {
     *   "deviceType": "None",
     *   "iotId": "J33QvkL1kjpEXGcs8PPc000100",
     *   "productKey": "a1WLPTsAths",
     *   "gmtCreate": 1559293492534,
     *   "deviceName": "STP002_001",
     *   "items": {
     *     "STERILIZER_PROTECTOR_UNDER_OR_OVER_VOLTAGE": {
     *       "value": {
     *         "type": "bool",
     *         "value": 0
     *       },
     *       "time": 1559293492539
     *     },
     *   }
     * }
     * @param payload JSONObject
     */
    private void handleDevicePropertyPost(JSONObject payload){
        Long createdAt = Long.parseLong(payload.getString("gmtCreate"));
        // 如果消息是5分钟以前的直接丢弃
        if (System.currentTimeMillis() - createdAt > 300000)
            return;
        String iotId = payload.getString("iotId");
        // 防止因消息的时间顺序获取不同, 导致的错序覆盖问题
        Long lastUpdatedAt = LastUpdatedAtFlag.get(iotId);
        if (Objects.nonNull(lastUpdatedAt)) {
            if (createdAt > lastUpdatedAt)
                LastUpdatedAtFlag.put(iotId, createdAt);
            else {
                log.info("设备属性上报已被忽略, lastUpdatedAt: {}, createdAt: {}", lastUpdatedAt, createdAt);
                return;
            }
        } else {
            LastUpdatedAtFlag.put(iotId, createdAt);
        }
        
        JSONObject propertyItems = payload.getJSONObject("items");
        HashMap<String, Object> properties = new HashMap<>();
        propertyItems.keySet().forEach(key -> {
            properties.put(key, propertyItems.getJSONObject(key).getJSONObject("value").get("value"));
        });
        // 更新设备属性快照信息
        Device device = new Device();
        device.setIotId(iotId);
        device.setUpdatedAt(new Date(createdAt));
        device.setPropertySnapshotJson(JSON.toJSONString(properties));
        deviceService.updateSelective(device);
    }

    /**
     * 处理设备事件上报
     * payload JSON 格式: {
     *   "deviceType": "None",
     *   "identifier": "ERROR_INFO",
     *   "iotId": "J33QvkL1kjpEXGcs8PPc000100",
     *   "name": "通知级别异常",
     *   "time": 1559298608776,
     *   "type": "info",
     *   "productKey": "a1WLPTsAths",
     *   "deviceName": "STP002_001",
     *   "value": {
     *     "ERROR_MESSAGE": "超过30秒仍无法启动",
     *     "CREATED_AT": "1559298608658",
     *     "DEVICE_NAME": "STP002_001",
     *     "ERROR_PART": "WATER_PUMP_PROTECTOR",
     *     "ERROR_DETAIL": "{\"WATER_PUMP_PROTECTOR_PHASE_C_CURRENT\":{\"type\":\"double\",\"value\":0.0}}"
     *   }
     * }
     * @param payload JSONObject
     */
    private void handleDeviceEventPost(JSONObject payload){
        JSONObject value = payload.getJSONObject("value");
        DeviceEvent event = new DeviceEvent();
        event.setIotId(payload.getString("iotId"));
        event.setNamePrefix(payload.getString("name"));
        event.setName(value.getString("ERROR_MESSAGE"));
        event.setNameSuffix(value.getString("ERROR_PART"));
        event.setIdentifier(payload.getString("identifier"));
        event.setProductKey(payload.getString("productKey"));
        event.setDeviceName(payload.getString("deviceName"));
        EventTypeConst type = EventTypeConst.getByStr(payload.getString("type"));
        event.setType(type.ordinal());
        event.setReportAt(payload.getDate("time"));
        event.setDetailJson(value.getString("ERROR_DETAIL"));
        if (!type.equals(EventTypeConst.INFO))
            event.setShouldHandle(true);
        // 创建设备事件
        deviceEventService.create(event);
        // TODO: 发送消息通知 相关人员
    }

    /**
     * 处理设备状态改变
     * payload JSON 格式: {
     *   "lastTime": "2019-05-31 17:28:47.529",
     *   "utcLastTime": "2019-05-31T09:28:47.529Z",
     *   "clientIp": "112.32.218.8",
     *   "utcTime": "2019-05-31T09:30:54.031Z",
     *   "time": "2019-05-31 17:30:54.031",
     *   "productKey": "a1WLPTsAths",
     *   "deviceName": "STP002_001",
     *   "status": "offline"
     * }
     * @param payload JSONObject
     */
    private synchronized void handleDeviceStatusChange(JSONObject payload){
        DeviceQueryCondition condition = new DeviceQueryCondition();
        condition.setProductKey(payload.getString("productKey"));
        condition.setDeviceName(payload.getString("deviceName"));
        List<Device> search = deviceService.search(condition);
        if (Objects.nonNull(search) && search.size() == 1) {
            DeviceStatusConst status = DeviceStatusConst.getByStr(payload.getString("status"));
            Device device = new Device();
            Date changedAt = payload.getDate("time");
            Device deviceOldInfo = search.get(0);
            // 防止因上下线消息错序导致的状态变更异常问题
            if (deviceOldInfo.getLastStatusChangedAt().getTime() > changedAt.getTime())
                return;
            device.setLastStatusChangedAt(changedAt);
            device.setIotId(deviceOldInfo.getIotId());
            device.setStatus(status.ordinal());
            device.setIpAddress(payload.getString("clientIp"));
            // 如果是上线则记录上线时间
            if (status.equals(DeviceStatusConst.ONLINE))
                device.setLastOnlineAt(changedAt);
            // 更新设备状态
            deviceService.updateSelective(device);
            log.info("DEBUG: {}", JSON.toJSONString(device));
        }
    }

    @PreDestroy
    public void preDestroy(){
        if (messageClient.isConnected()) {
            messageClient.disconnect();
        }
    }
}
