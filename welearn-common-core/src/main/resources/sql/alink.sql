CREATE TABLE `product` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `productKey` varchar(64) NOT NULL COMMENT '隶属的产品Key',
  `dataFormat` int NOT NULL DEFAULT 1 COMMENT '数据类型: 0-串口数据格式 1-AlinkJSON',
  `productName` varchar(128) NOT NULL COMMENT '产品名称',
  `prpoductSecret` varchar(128) NOT NULL COMMENT '产品秘钥',
  `description` varchar(256) NULL COMMENT '产品描述',
  `deviceCount` int NOT NULL DEFAULT 0 COMMENT '设备数量',
  `nodeType` int NOT NULL DEFAULT 0 COMMENT '高级版产品的节点类型 0-设备 1-网关',
  `categoryName` varchar(128) NULL COMMENT '高级版产品的设备类型',
  `categoryKey` varchar(128) NULL COMMENT '高级版产品的英文标识',
  `platformType` int NOT NULL DEFAULT 1 COMMENT '平台类型: 0-物联网平台基础版 1-物联网平台高级版',
  `id2` tinyint(1) NOT NULL default 0 COMMENT '是否使用ID²认证: 0-否 1-是',
  `productStatus`int NOT NULL default 0 COMMENT '产品状态: 0-开发中 1-已发布',
  `netType` int NOT NULL DEFAULT 3 COMMENT '联网方式: 0-WIFI 1-Cellular (2G/3G/4G)蜂窝网 2-Ethernet 以太网 3-其他',
  `tslJson` MEDIUMTEXT NOT NULL COMMENT '物模型 Thing Specification Language JSON格式存储',
  `showConfigJson` text NOT NULL COMMENT '数据显示用 JSON格式配置文件',
  `statisitcsConfigJson` text NOT NULL COMMENT '数据分析用 JSON格式配置文件',
  PRIMARY KEY (`id`)
) COMMENT '物联产品';

CREATE TABLE `device` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `companyId` varchar(36) NULL COMMENT '所属公司id',
  `companyName` varchar(36) NULL COMMENT '所属公司名称',
  `addressId` varchar(36) NULL COMMENT '所在位置id',
  `productKey` varchar(64) NOT NULL COMMENT '隶属的产品Key',
  `productName` varchar(128) NOT NULL COMMENT '隶属的产品名称',
  `deviceName` varchar(128) NOT NULL COMMENT '名称',
  `nickname` varchar(256) NULL COMMENT '备注名称',
  `deviceSecret` varchar(128) NOT NULL COMMENT '密钥',
  `iotId` varchar(128) NOT NULL COMMENT 'IoT平台为该设备颁发的ID，作为该设备的唯一标识符',
  `activedAt` timestamp NULL COMMENT '​​激活时间',
  `lastOnlineAt` timestamp NULL COMMENT '最近一次上线的时间',
  `status` int NOT NULL COMMENT '状态: 0-OFFLINE设备离线 1-ONLINE设备在线 2-UNACTIVE设备未激活 3-DISABLE设备已禁用',
  `lastStatusChangedAt` timestamp NULL COMMENT '最近状态改变的时间(处理状态消息错序问题)',
  `firmwareVersion` varchar(36) NULL COMMENT '固件版本号',
  `ipAddress` varchar(64) NULL COMMENT 'IP地址',
  `nodeType` int NOT NULL default 0 COMMENT '节点类型: 0-设备 1-网关',
  `region` varchar(64) NOT NULL COMMENT '所在地区',
  `propertySnapshotJson` text NOT NULL COMMENT '属性快照JSON格式存储, {key(与物模型的属性名对应): value}',
  `configurationJson` text NOT NULL COMMENT '配置文件JSON格式存储, {key: value ...}',
  `documentFileJson` text NOT NULL COMMENT '文档相关文件JSON格式存储, [{name, description, files:[{...}]}]',
  `showConfigJson` text NULL COMMENT '数据显示用 JSON格式配置文件',
  PRIMARY KEY (`id`), UNIQUE (`iotId`)
) COMMENT '物联设备';

CREATE TABLE `device_event` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `identifier` varchar(128) NOT NULL COMMENT '事件标识',
  `type` int NOT NULL COMMENT '事件级别: 0-通知 1-警告 2-报警',
  `iotId` varchar(128) NOT NULL COMMENT '设备唯一标识符',
  `namePrefix` varchar(128) NOT NULL COMMENT '事件名称',
  `name` varchar(128) NOT NULL COMMENT '异常信息',
  `nameSuffix` varchar(128) NOT NULL COMMENT '异常部位',
  `productKey` varchar(64) NOT NULL COMMENT '产品Key',
  `deviceName` varchar(128) NOT NULL COMMENT '设备编号',
  `reportAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上报时间',
  `detailJson` text NOT NULL COMMENT '时间详情JSON, {key(与物模型事件的配置对应): value}',
  `shouldHandle` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否需要处理：0-不需要，1-需要',
  `handleStatus` int NOT NULL default 0 COMMENT '处理状态: 0-待领工 1-待处理 2-已处理',
  `handleUserId` varchar(36) NULL COMMENT '处理人员id',
  `handleReportJson` text NULL COMMENT '处理报告JSON格式存储',
  PRIMARY KEY (`id`)
) COMMENT '设备事件';

CREATE TABLE `device_monitor_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `type` varchar(64) NOT NULL COMMENT '监测记录类型 前端定义',
  `productKey` varchar(64) NOT NULL COMMENT '设备唯一标识符',
  `iotId` varchar(128) NOT NULL COMMENT '设备唯一标识符',
  `dataJson` text NOT NULL COMMENT '监测记录数据JSON格式存储',
  `monitorName` varchar(64) NOT NULL COMMENT '监测方名称',
  `monitorResult` varchar(64) NOT NULL COMMENT '监测结果',
  `monitorAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '监测时间',
  `creatorId` varchar(36) NOT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`)
) COMMENT '设备监测记录';

CREATE TABLE `device_property_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `iotId` varchar(128) NOT NULL COMMENT '设备唯一标识符',
  `dataJson` text NOT NULL COMMENT '属性数据JSON格式存储',
  `reportedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '属性上报时间',
  `type` int NOT NULL default 0 COMMENT '属性记录类型: 0-上报属性记录, 1-运行时间记录',
  PRIMARY KEY (`id`)
) COMMENT '设备属性记录';

CREATE TABLE `device_group` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(64) NOT NULL COMMENT '组名称',
  `type` int NOT NULL COMMENT '组类型: 0-巡检组 1-处理组 2-监察组',
  `description` varchar(256) NOT NULL COMMENT '组描述',
  PRIMARY KEY (`id`)
) COMMENT '设备分组';

CREATE TABLE `re_device_group_device` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `groupId` varchar(36) NOT NULL COMMENT '组id',
  `iotId` varchar(36) NOT NULL COMMENT '设备id',
  PRIMARY KEY (`id`)
) COMMENT '设备分组与设备的关联';

CREATE TABLE `re_device_group_user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `groupId` varchar(36) NOT NULL COMMENT '组id',
  `userId` varchar(36) NOT NULL COMMENT '用户id',
  `userName` varchar(64) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`)
) COMMENT '设备分组与设备的关联';

-- 流程监控业务相关数据 ---------------------------------------------------------------------------------------------------

CREATE TABLE `rfid_tag` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `no` varchar(64) NOT NULL COMMENT '标签编号',
  `type` int NOT NULL COMMENT '类型: 0-125kHz',
  `specification` varchar(64) NOT NULL COMMENT '规格: 如 蓝色 直径25mm标签 等',
  `image` text NULL COMMENT '图标',
  `refId` varchar(36) NULL COMMENT '关联ID',
  `refType` varchar(64) NULL COMMENT '关联类型: User, Equipment',
  `refName` varchar(128) NULL COMMENT '关联类型名称',
  `refDescription` varchar(128) NULL COMMENT '关联类型描述',
  PRIMARY KEY (`id`)
) COMMENT 'RFID标签';

CREATE TABLE `re_rfid_tag_device` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `iotId` varchar(128) NOT NULL COMMENT '设备id',
  `tagId` varchar(36) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`)
) COMMENT 'RFID标签与设备的关联';
