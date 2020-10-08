package com.welearn.alink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.iot.model.v20180120.*;
import com.welearn.dictionary.alink.*;
import com.welearn.dictionary.notify.SmsSignatureConst;
import com.welearn.dictionary.notify.SmsTemplateConst;
import com.welearn.entity.po.alink.Device;
import com.welearn.entity.po.alink.DeviceEvent;
import com.welearn.entity.po.alink.DevicePropertyRecord;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.alink.DeviceQueryCondition;
import com.welearn.entity.vo.request.notify.SendForUser;
import com.welearn.feign.notify.SmsSendFeignClient;
import com.welearn.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description : 服务器主动请求 获取设备状态,事件,属性信息
 * Created by Setsuna Jin on 2019/6/9.
 */
@Slf4j
@Service
public class ServerAutoRequest {

    @Autowired
    private DevicePropertyRecordService devicePropertyRecordService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DefaultAcsClient client;

    @Autowired
    private ProductService productService;

    @Autowired
    private DeviceEventService deviceEventService;

    @Autowired
    private SmsSendFeignClient smsSendFeignClient;

    @Autowired
    private DeviceGroupService deviceGroupService;

    private static final ExecutorService RequestExecutor = Executors.newFixedThreadPool(6);

    // IotId 对应 最近上报数据时间, 用于防止数据的无效更新
    private static final Map<String, Long> LastIotIdReportedAt = new ConcurrentHashMap<>();
    private static final Map<String, DeviceStatusConst> LastIotIdStatus = new ConcurrentHashMap<>();


    /**
     * 自动更新所有设备的属性快照
     */
    @Scheduled(initialDelay = 1000*15, fixedRate = 5000L)
    public void autoRequestProperty() {
        List<String> iotIds = deviceService.selectIotIds();
        Map<String, JSONObject> statisticConfig = productService.getStatisticConfigJson();
        iotIds.forEach(iotId -> {
            RequestExecutor.execute(()-> {
                QueryDevicePropertyStatusRequest request = new QueryDevicePropertyStatusRequest();
                request.setIotId(iotId);
                try {
                    QueryDevicePropertyStatusResponse response = client.getAcsResponse(request);
                    long updatedAtTimestamp = 0L;
                    HashMap<String, String> properties = new HashMap<>();
                    for (QueryDevicePropertyStatusResponse.Data.PropertyStatusInfo propertyStatusInfo : response.getData().getList()) {
                        if (Objects.nonNull(propertyStatusInfo.getTime())) {
                            long time = Long.parseLong(propertyStatusInfo.getTime());
                            if (time > updatedAtTimestamp)
                                updatedAtTimestamp = time;
                        }
                        properties.put(propertyStatusInfo.getIdentifier(), propertyStatusInfo.getValue());
                    }
                    if (updatedAtTimestamp == 0L)
                        return;
                    // 检查是否需要忽略
                    Long lastReportedAt = LastIotIdReportedAt.get(iotId);
                    if (Objects.isNull(lastReportedAt)) {
                        LastIotIdReportedAt.put(iotId, updatedAtTimestamp);
                    } else if (updatedAtTimestamp <= lastReportedAt) {
                        return;
                    } else {
                        LastIotIdReportedAt.put(iotId, updatedAtTimestamp);
                    }
                    // 获取需要备份的属性列表
                    JSONObject config = statisticConfig.get(iotId);
                    JSONArray savedProperty = config.getJSONArray("statisticProperty");
                    if (Objects.isNull(savedProperty) || savedProperty.isEmpty())
                        return;
                    List<String> savedPropertyValues = new ArrayList<>();
                    for (Object key : savedProperty) {
                        String value = properties.get(key.toString());
                        if (StringUtils.isBlank(value))
                            value = "0";
                        savedPropertyValues.add(value);
                    }
                    // 添加设备属性记录
                    DevicePropertyRecord record = new DevicePropertyRecord();
                    record.setIotId(iotId);
                    record.setType(DevicePropertyRecordTypeConst.UPLOAD_PROPERTY.ordinal());
                    record.setDataJson(JSON.toJSONString(savedPropertyValues));
                    record.setReportedAt(new Date(updatedAtTimestamp));
                    devicePropertyRecordService.create(record);
                    // 更新设备属性信息
                    Device device = new Device();
                    device.setIotId(iotId);
                    device.setUpdatedAt(new Date(updatedAtTimestamp));
                    device.setPropertySnapshotJson(JSON.toJSONString(properties));
                    deviceService.updateSelective(device);
                } catch (ServerException e) {
                    log.error("自动更新设备属性 服务器错误:", e);
                } catch (ClientException e) {
                    log.error("自动更新设备属性 客户端错误:", e);
                } catch (Exception e) {
                    log.error("自动更新设备属性 代码级错误:", e);
                }
            });
        });
    }

    /**
     * 自动更新所有设备的事件信息
     * 20秒执行一次, 每次获取之前40秒-前20秒的设备数据
     *
     * 由于阿里云的事件存在一定的时间延时, 经常收不到事件信息
     * 因此此部分功能改为由设备直接上报事件到此服务器, 事件并发较小
     */
    @Deprecated
//    @Scheduled(initialDelay = 1000*15, fixedRate = 60*60*1000L)
    public void autoRequestEvent() {
        Date endTime = new Date();
        List<Device> devices = deviceService.selectOutlook();
        devices.forEach(device -> {
            RequestExecutor.execute(()-> {
                for (EventIdentifierConst identifier : EventIdentifierConst.values()) {
                    QueryDeviceEventDataRequest request = new QueryDeviceEventDataRequest();
                    request.setIotId(device.getIotId());
                    // 十五秒前的时间
                    request.setStartTime(endTime.getTime() - 90*60*1000L);
                    request.setEndTime(endTime.getTime() - 30*60*1000L);
                    request.setIdentifier(identifier.name());
                    try {
                        QueryDeviceEventDataResponse response = client.getAcsResponse(request);
                        response.getData().getList().forEach(eventInfo -> {
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
                            EventTypeConst type = EventTypeConst.getByStr(eventInfo.getEventType());
                            event.setType(type.ordinal());
                            if (!type.equals(EventTypeConst.INFO))
                                event.setShouldHandle(true);
                            // 创建设备事件
                            deviceEventService.create(event);
                        });
                    } catch (ServerException e) {
                        log.error("自动更新设备事件 服务器错误:" + JSON.toJSONString(e), e);
                    } catch (ClientException e) {
                        log.error("自动更新设备事件 客户端错误:" + JSON.toJSONString(e), e);
                    }
                }
            });
        });
    }

    /**
     * 自动更新所有设备的状态信息
     * @auther Setsuna Jin 2019/10/05 添加 设备离线时 发送 短信给 处理组
     */
    @Scheduled(initialDelay = 1000*15, fixedRate = 5000L)
    public void autoRequestStatus() {
        List<String> iotIds = deviceService.selectIotIds();
        iotIds.forEach(iotId -> {
            RequestExecutor.execute(()-> {
                GetDeviceStatusRequest request = new GetDeviceStatusRequest();
                request.setIotId(iotId);
                try {
                    GetDeviceStatusResponse response = client.getAcsResponse(request);
                    Device device = new Device();
                    device.setIotId(iotId);
                    DeviceStatusConst thisStatus = DeviceStatusConst.getByStr(response.getData().getStatus());
                    device.setStatus(thisStatus.ordinal());
                    // 获取上个时刻的状态
                    DeviceStatusConst lastStatus = LastIotIdStatus.get(iotId);
                    if (!thisStatus.equals(lastStatus)){
                        device.setLastStatusChangedAt(new Date());
                        if (thisStatus.equals(DeviceStatusConst.ONLINE))
                            device.setLastOnlineAt(new Date());
                        else if (thisStatus.equals(DeviceStatusConst.OFFLINE)) {
                            // 获取相关设备
                            Device deviceData = deviceService.selectByIotId(iotId);
                            // 获取相关用户
                            List<User> users = deviceGroupService.selectGroupUserByIotIdAndGroupType(iotId, DeviceGroupTypeConst.MAINTENANCE.ordinal());
                            users.forEach(user -> {
                                // 发送短信通知处理组人员
                                SendForUser sendForUser = new SendForUser();
                                sendForUser.setCode(SmsTemplateConst.DEVICE_OFFLINE.getCode());
                                sendForUser.setSignature(SmsSignatureConst.RUIYANG_MEDICAL);
                                sendForUser.setUser(user);
                                sendForUser.setParams(SmsTemplateConst.DEVICE_OFFLINE.getParams(deviceData.getNickname(), deviceData.getProductName()));
                                smsSendFeignClient.sendForUser(sendForUser);
                            });
                        }
                    }
                    // 更新设备的状态记录
                    LastIotIdStatus.put(iotId, thisStatus);
                    // 更新设备信息
                    deviceService.updateSelective(device);
                } catch (ServerException e) {
                    log.error("自动更新设备状态 服务器错误:" + JSON.toJSONString(e), e);
                } catch (ClientException e) {
                    log.error("自动更新设备状态 客户端错误:" + JSON.toJSONString(e), e);
                }
            });
        });
    }

    /**
     * 自动更新设备的每小时各个部件的运行时间
     * 每小时开始时执行
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void autoRequestRunningTime() {
        // 获取上个小时的起始时间
        DateTime dateTime = new DateTime().withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).minusHours(1);
        DeviceQueryCondition condition = new DeviceQueryCondition();
        condition.setIsEnable(true);
        condition.setStatus(DeviceStatusConst.ONLINE.ordinal());
        List<Device> iotIds = deviceService.search(condition);
        Map<String, JSONObject> statisticConfig = productService.getStatisticConfigJson();
        iotIds.forEach(device -> {
            // 获取需要备份的属性列表
            JSONObject config = statisticConfig.get(device.getIotId());
            JSONArray keys = config.getJSONArray("statisticRunTimeKey");
            if (Objects.isNull(keys) || keys.isEmpty())
                return;
            RequestExecutor.execute(()-> {
                InvokeThingServiceResponse response = deviceService.runService(device.getIotId(), ServiceIdentifierConst.RUNNING_TIME.name(), new HashMap<>());
                String result = response.getData().getResult();
                log.info("获取设备:{} 的上个小时的运行时间统计JSON: {}", device.getDeviceName(), result);
                JSONObject object = JSON.parseObject(StringUtils.isNotBlank(result) ? result : "{}");
                // 判断是否有返回结果
                List<Long> runningSeconds = new ArrayList<>();
                try {
                    JSONObject value = JSON.parseObject(object.getJSONObject("JSON_DATA").getString("value"));
                    keys.forEach(key -> {
                        Long seconds = value.getLong(key.toString());
                        // 如果无记录则运行时间按 0s 来处理
                        runningSeconds.add(Objects.isNull(seconds) ? 0L : seconds);
                    });
                } catch (Exception e) {
                    log.error("处理设备上报的运行时间统计JSON出错", e);
                }
                // 创建运行时间记录
                DevicePropertyRecord record = new DevicePropertyRecord();
                record.setIotId(device.getIotId());
                record.setType(DevicePropertyRecordTypeConst.RUNNING_TIME.ordinal());
                record.setDataJson(JSON.toJSONString(runningSeconds));
                record.setReportedAt(dateTime.toDate());
                devicePropertyRecordService.create(record);
            });
        });
    }
}
