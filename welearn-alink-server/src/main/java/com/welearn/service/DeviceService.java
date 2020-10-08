package com.welearn.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.iot.model.v20180120.InvokeThingServiceResponse;
import com.welearn.entity.po.alink.Device;
import com.welearn.entity.qo.alink.DeviceQueryCondition;
import com.welearn.validate.annotation.common.EntityCheck;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Description : DeviceService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface DeviceService extends BaseService<Device, DeviceQueryCondition>{

    /**
     * 设备的认证检查
     * @param productKey 产品key
     * @param deviceName 设备名称
     * @param deviceSecret 设备密钥
     * @return 设备信息
     */
    Device secretLogin(String productKey, String deviceName, String deviceSecret);

    /**
     * 根据 iotId 查询设备
     * @param iotId 设备iotId
     * @return Device
     */
    Device selectByIotId(@NotBlank String iotId);

    /**
     * 选择性的更新设备信息, 可自动根据 id 或 iotId 更新
     * @param device 设备信息
     */
    void updateSelective(@EntityCheck Device device);

    /**
     * 获取所有设备的 iotId 列表
     * @return iotId 列表
     */
    List<String> selectIotIds();

    /**
     * 获取所有设备的简略信息列表
     * @return 设备简略信息
     */
    List<Device> selectOutlook();

    /**
     * 设置设备的单个属性值
     *
     * @param iotId      设备iotId
     * @param properties      设备属性标识与值
     */
    void setProperty(String iotId, Map<String, Object> properties);

    /**
     * 根据 产品key 和 设备名称 获取设备
     * @param productKey 产品key
     * @param deviceName 设备名称
     * @return 设备信息
     */
    Device selectByProductKeyAndDeviceName(@NotBlank String productKey, @NotBlank String deviceName);

    /**
     * 调用设备的某个服务
     * @param iotId 设备iotId
     * @param identifier 服务的标识
     * @param args 调用服务时的参数
     */
    InvokeThingServiceResponse runService(String iotId, String identifier, Map<String, Object> args);


    /**
     * 获取设备的配置文件
     *
     * @param productKey 产品的key
     * @param deviceName 设备的名称
     * @return JSON 对象
     */
    JSONObject getConfiguration(@NotBlank String productKey, @NotBlank String deviceName);

    /**
     * 向阿里云注册设备, 并更新设备列表
     * @param device 设备
     */
    void register(Device device);

    /**
     * 从阿里云更新设备信息
     * @param iotId 设备标识id
     * @return 设备信息
     */
    Device updateDeviceFromAlink(String iotId);

    /**
     * 更新设备的备注名称
     * @param device 设备
     */
    void updateNickname(Device device);
}