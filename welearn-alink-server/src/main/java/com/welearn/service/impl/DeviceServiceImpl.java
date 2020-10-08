package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.iot.model.v20180120.*;
import com.welearn.dictionary.alink.DeviceStatusConst;
import com.welearn.entity.po.alink.Device;
import com.welearn.entity.qo.alink.DeviceQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.DeviceMapper;
import com.welearn.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.*;

/**
 * Description : DeviceService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device,DeviceQueryCondition,DeviceMapper>
        implements DeviceService{
    
    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DefaultAcsClient client;

    @Override
    DeviceMapper getMapper() {
        return deviceMapper;
    }

    @Override
    public void update(Device device) {
        deviceMapper.updateByPKSelective(device);
    }

    /**
     * 设备的认证检查
     *
     * @param productKey   产品key
     * @param deviceName   设备名称
     * @param deviceSecret 设备密钥
     * @return 设备信息
     */
    @Override
    public Device secretLogin(String productKey, String deviceName, String deviceSecret) {
        DeviceQueryCondition condition = new DeviceQueryCondition();
        condition.setIsEnable(true);
        condition.setProductKey(productKey);
        condition.setDeviceName(deviceName);
        condition.setDeviceSecret(deviceSecret);
        return this.search(condition).stream().findFirst().orElse(null);
    }

    /**
     * 根据 iotId 查询设备
     *
     * @param iotId 设备iotId
     * @return Device
     */
    @Override
    public Device selectByIotId(String iotId) {
        return deviceMapper.selectByIotId(iotId);
    }

    /**
     * 选择性的更新设备信息, 可自动根据 id 或 iotId 更新
     * @param device 设备信息
     */
    @Override
    public void updateSelective(Device device) {
        if (StringUtils.isBlank(device.getId()) && StringUtils.isBlank(device.getIotId()))
            throw new BusinessVerifyFailedException("updateSelective 时 id 或 iotId 必须有一个");
        deviceMapper.updateSelective(device);
    }

    /**
     * 获取所有设备的 iotId 列表
     *
     * @return iotId 列表
     */
    @Override
    public List<String> selectIotIds() {
        return deviceMapper.selectIotIds();
    }

    @Override
    public List<Device> selectOutlook() {
        return deviceMapper.selectOutlook();
    }

    /**
     * 设置设备的单个属性值
     *
     * @param iotId      设备iotId
     * @param properties      设备属性标识与值
     */
    @Override
    public void setProperty(String iotId, Map<String, Object> properties) {
        SetDevicePropertyRequest request = new SetDevicePropertyRequest();
        request.setIotId(iotId);
        request.setItems(JSON.toJSONString(properties));
        try {
            SetDevicePropertyResponse response = client.getAcsResponse(request);
            if (!response.getSuccess()) {
                throw new BusinessVerifyFailedException("设置设备属性 失败, 响应:" + JSON.toJSONString(response));
            }
        } catch (ServerException e) {
            throw new BusinessVerifyFailedException(e, "设置设备属性 服务器错误:" + JSON.toJSONString(e));
        } catch (ClientException e) {
            throw new BusinessVerifyFailedException(e, "设置设备属性 客户端错误:" + JSON.toJSONString(e));
        }
    }

    /**
     * 根据 产品key 和 设备名称 获取设备
     *
     * @param productKey 产品key
     * @param deviceName 设备名称
     * @return 设备信息
     */
    @Override
    public Device selectByProductKeyAndDeviceName(String productKey, String deviceName) {
        DeviceQueryCondition condition = new DeviceQueryCondition();
        condition.setProductKey(productKey);
        condition.setDeviceName(deviceName);
        List<Device> search = this.search(condition);
        if (Objects.nonNull(search) && !search.isEmpty()) {
            return search.get(0);
        } else {
            return null;
        }
    }

    /**
     * 调用设备的某个服务
     *
     * @param iotId      设备iotId
     * @param identifier 服务的标识
     * @param args       调用服务时的参数
     */
    @Override
    public InvokeThingServiceResponse runService(String iotId, String identifier, Map<String, Object> args) {
        if (Objects.isNull(args))
            args = new HashMap<>();
        InvokeThingServiceRequest request = new InvokeThingServiceRequest();
        request.setIotId(iotId);
        request.setIdentifier(identifier);
        request.setArgs(JSON.toJSONString(args));
        try {
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new BusinessVerifyFailedException(e, "调用设备服务失败");
        }
    }

    /**
     * 获取设备的配置文件
     *
     * @param productKey 产品的key
     * @param deviceName 设备的名称
     * @return JSON 对象
     */
    @Override
    public JSONObject getConfiguration(String productKey, String deviceName) {
        DeviceQueryCondition condition = new DeviceQueryCondition();
        condition.setProductKey(productKey);
        condition.setDeviceName(deviceName);
        List<Device> search = this.search(condition);
        if (Objects.nonNull(search) && !search.isEmpty()) {
            Device device = search.get(0);
            log.info("设备{} 成功获取配置文件", device.getDeviceName());
            return JSON.parseObject(device.getConfigurationJson());
        }
        else
            throw new BusinessVerifyFailedException("无法找到匹配的设备信息");
    }

    /**
     * 向阿里云注册设备, 并更新设备列表
     *
     * @param device 设备
     */
    @Override
    public void register(Device device) {
        RegisterDeviceRequest request = new RegisterDeviceRequest();
        request.setProductKey(device.getProductKey());
        request.setDeviceName(device.getDeviceName());
        request.setNickname(device.getNickname());
        try {
            RegisterDeviceResponse response = client.getAcsResponse(request);
            String iotId = response.getData().getIotId();
            this.updateDeviceFromAlink(iotId);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static Date parseUTC(String strDate) {
        if (StringUtils.isNotBlank(strDate))
            return ISODateTimeFormat.dateTime().parseDateTime(strDate).toDate();
        else
            return null;
    }

    public Device updateDeviceFromAlink(String iotId){
        try {
            // 查询每个设备的详情信息
            QueryDeviceDetailResponse.Data deviceDetail = queryDeviceDetail(iotId);
            // 并从得到的信息更新本地
            return this.updateFromDeviceDetail(deviceDetail);
        } catch (ParseException e) {
            throw new BusinessVerifyFailedException(e, "时间格式转换错误");
        } catch (ClientException e) {
            throw new BusinessVerifyFailedException(e, "客户端的请求异常");
        }
    }

    /**
     * 更新设备的备注名称
     *
     * @param device 设备
     */
    @Override
    public void updateNickname(Device device) {
        if (StringUtils.isBlank(device.getIotId()))
            throw new BusinessVerifyFailedException("iotId 非法");
        BatchUpdateDeviceNicknameRequest request = new BatchUpdateDeviceNicknameRequest();
        List<BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo> nicknameInfos = new ArrayList<>();
        BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo deviceNicknameInfo = new BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo();
        deviceNicknameInfo.setIotId(device.getIotId());
        deviceNicknameInfo.setNickname(device.getNickname());
        nicknameInfos.add(deviceNicknameInfo);
        request.setDeviceNicknameInfos(nicknameInfos);
        try {
            // 请求物联云更新设备的备注
            client.getAcsResponse(request);
            // 更新服务器的设备备注名称
            Device updatedDevice = new Device();
            updatedDevice.setIotId(device.getIotId());
            updatedDevice.setNickname(device.getNickname());
            this.updateSelective(updatedDevice);
        } catch (ClientException e) {
            throw new BusinessVerifyFailedException(e, "更改设备备注名称失败");
        }
    }



    /**
     * 向阿里物联网平台查询设备详情
     * @param iotId 设备标识id
     * @return 设备详情
     * @throws ClientException
     */
    private QueryDeviceDetailResponse.Data queryDeviceDetail(String iotId) throws ClientException {
        QueryDeviceDetailRequest request = new QueryDeviceDetailRequest();
        request.setIotId(iotId);
        return client.getAcsResponse(request).getData();
    }

    private Device updateFromDeviceDetail(QueryDeviceDetailResponse.Data deviceDetail) throws ParseException {
        Device deviceInDb = this.selectByIotId(deviceDetail.getIotId());
        Device device = new Device();
        device.setProductKey(deviceDetail.getProductKey());
        device.setProductName(deviceDetail.getProductName());
        device.setDeviceName(deviceDetail.getDeviceName());
        device.setNickname(deviceDetail.getNickname());
        device.setDeviceSecret(deviceDetail.getDeviceSecret());
        device.setIotId(deviceDetail.getIotId());
        device.setCreatedAt(parseUTC(deviceDetail.getUtcCreate()));
        device.setActivedAt(parseUTC(deviceDetail.getUtcActive()));
        device.setLastOnlineAt(parseUTC(deviceDetail.getUtcOnline()));
        device.setStatus(DeviceStatusConst.getByStr(deviceDetail.getStatus()).ordinal());
        device.setFirmwareVersion(deviceDetail.getFirmwareVersion());
        device.setIpAddress(deviceDetail.getIpAddress());
        device.setNodeType(deviceDetail.getNodeType());
        device.setRegion(deviceDetail.getRegion());
        if (Objects.nonNull(deviceInDb)) {
            device.setId(deviceInDb.getId());
            this.updateSelective(device);
            return device;
        } else {
            device.setConfigurationJson("{}");
            device.setPropertySnapshotJson("{}");
            device.setDocumentFileJson("[]");
            device.setShowConfigJson("{}");
            device.setLastStatusChangedAt(parseUTC(deviceDetail.getUtcOnline()));
            log.info("新增设备: {}", device.getDeviceName());
            return this.create(device);
        }
    }
}
