CREATE TABLE `spare_part_usage` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `spareTypeId` varchar(36) NOT NULL COMMENT '备件类型id',
  `usageType` int NOT NULL DEFAULT 0 COMMENT '用途类型: 0-通用 1-类型适用 2-产品适用 3-设备专用',
  `usageRefId` varchar(36) NULL COMMENT '用途关联的id(用途类型: 1-设备类型id, 2-设备产品id, 3-设备id)',
  `usageDescription` varchar(128) NULL COMMENT '用途描述',
  PRIMARY KEY (`id`)
) COMMENT '备件用途';

-- 2018/12/06
CREATE TABLE `spare_part_type` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `specification` varchar(128) NOT NULL COMMENT '规格',
  `description` varchar(256) NULL COMMENT '描述',
  `priceType` int NOT NULL COMMENT '价格类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件',
  `priceAvg` decimal(14,2)  NOT NULL default 0 COMMENT '平均单价',
  `unit` varchar(36) NULL COMMENT '单位',
  `picture` text NULL COMMENT '图片',
  `document` text NULL COMMENT '文档',
  `minCount` int NOT NULL DEFAULT 0 COMMENT '最小库存数量(低于报警) 0-不限制',
  `maxCount` int NOT NULL DEFAULT 0 COMMENT '最大库存数量(高于报警) 0-不限制',
  `inTransitInCount` int NOT NULL DEFAULT 0 COMMENT '在途入库数量(内含数量 表示将有货物运进来)',
  `inTransitOutCount` int NOT NULL DEFAULT 0 COMMENT '在途出库数量(内含数量 表示将有货物运出去)',
  `retailPurchases` int NOT NULL DEFAULT 0 COMMENT '进货量',
  `consumption` int NOT NULL DEFAULT 0 COMMENT '消耗量',
  PRIMARY KEY (`id`)
) COMMENT '备件类型';

-- 2018/12/06
CREATE TABLE `spare_part` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `origin` int NOT NULL COMMENT '来源:0-原厂备件 1-采购配件 2-回收配件',
  `typeId` varchar(36) NOT NULL COMMENT '类型id',
  `equipmentId` varchar(36) NULL COMMENT '来源为原厂备件时表明属于哪个设备',
  `price` decimal(14,2) NOT NULL DEFAULT 0 COMMENT '单价',
  `count` int NOT NULL COMMENT '总量',
  `remain` int NOT NULL COMMENT '余量',
  `inTransitOutCount` int NOT NULL DEFAULT 0 COMMENT '在途出库数量(内含数量 表示将有货物运出去)',
  `manufacturerName` varchar(128) NULL COMMENT '生产厂商名称',
  `model` varchar(64) NULL COMMENT '型号',
  `timeCost` int NOT NULL DEFAULT 0 COMMENT '采购耗时(小时)',
  `serviceLife` int NOT NULL DEFAULT 5 COMMENT '使用寿命长短(0-10)',
  `procurementId` varchar(36) NULL COMMENT '采购项目id',
  `supplierId` varchar(36) NULL COMMENT '供应商id',
  `maintenanceEngineerId` varchar(36) NULL COMMENT '回收维修工程师id',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '备件库存(按批次存储)';

-- 2018/12/06
CREATE TABLE `spare_part_replacement` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `typeId` varchar(36) NOT NULL COMMENT '类型id',
  `repairDispatchId` varchar(36) NOT NULL COMMENT '维修派工id',
  `maintenanceEngineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `maintenanceEngineerName` varchar(64) NOT NULL COMMENT '工程师名称',
  `status` int NOT NULL COMMENT '状态:0-待报废 1-待维修 2-已报废 3-已回收',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '替换下的旧配件';

CREATE TABLE `spare_part_out_repair_apply` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `dispatchId` varchar(36) NOT NULL COMMENT '内部派工id',
  `engineerId` varchar(36) NOT NULL COMMENT '内部派工工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '内部派工工程师名称',
  `dispatchOutsideId` varchar(36) NULL COMMENT '外部派工id',
  `remark` varchar(128) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '维修配件出库申请';

CREATE TABLE `spare_part_out_bill` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `outlook` varchar(128) NOT NULL COMMENT '出库简述',
  `status` int NOT NULL default 0 COMMENT '状态: 0-待出库 1-已出库 2-已取消',
  `recipientId` varchar(36) NOT NULL COMMENT '接收人id',
  `recipientName` varchar(64) NOT NULL COMMENT '接收人名称',
  `recipientSignatureId` varchar(36) NULL COMMENT '接收人签名id',
  `recipientDepartmentId` varchar(36) NOT NULL COMMENT '接收方部门id',
  `recipientDepartmentName` varchar(64) NOT NULL COMMENT '接收方部门名称',
  `recipientCompanyId` varchar(36) NOT NULL COMMENT '接收方公司id',
  `recipientCompanyName` varchar(128) NOT NULL COMMENT '接收方公司名称',
  `sumCount` int NOT NULL COMMENT '总数量',
  `sumPrice` decimal(14,2) NOT NULL COMMENT '总金额(小写)',
  `sumPriceChinese` varchar(128) NOT NULL COMMENT '总金额(大写)',
  `approverId` varchar(36) NOT NULL COMMENT '核验人id',
  `approverName` varchar(64) NOT NULL COMMENT '核验人名称',
  `approverDepartmentId` varchar(36) NOT NULL COMMENT '库存方部门id',
  `approverDepartmentName` varchar(64) NOT NULL COMMENT '库存方部门名称',
  `approverCompanyId` varchar(36) NOT NULL COMMENT '库存方公司id',
  `approverCompanyName` varchar(128) NOT NULL COMMENT '库存方公司名称',
  `remark` varchar(128) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '配件出库单';

CREATE TABLE `spare_part_out_item` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `priceType` int NOT NULL COMMENT '价格类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `specification` varchar(128) NOT NULL COMMENT '规格',
  `unit` varchar(36) NULL COMMENT '单位',
  `price` decimal(14,2) NOT NULL COMMENT '单价',
  `count` int NOT NULL COMMENT '数量',
  `sumPrice` decimal(14,2) NOT NULL COMMENT '金额',
  `sort` int NOT NULL default 0 COMMENT '排序',
  `status` int NOT NULL default 0 COMMENT '状态:0-申请中 1-待出库 2-待确认 2-已出库 3-已取消',
  `repairReplacementId` varchar(36) NULL COMMENT '维修配件更换id',
  `sparePartOutApplyId` varchar(36) NULL COMMENT '出库申请id',
  `sparePartOutApplyType` varchar(64) NULL COMMENT '出库申请类型',
  `sparePartOutBillId` varchar(36) NULL COMMENT '出库单id',
  `manufacturerName` varchar(128) NULL COMMENT '生产厂商名称',
  `model` varchar(64) NULL COMMENT '型号',
  `sparePartId` varchar(36) NOT NULL COMMENT '配件批次id',
  `remark` varchar(128) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '配件出库项';

CREATE TABLE `spare_part_in_bill` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `outlook` varchar(128) NOT NULL COMMENT '入库简述',
  `origin` int NOT NULL COMMENT '来源:0-原厂备件 1-采购配件 2-回收配件',
  `applyId` varchar(36) NULL COMMENT '申请id',
  `status` int NOT NULL default 0 COMMENT '状态: 0-待入库 1-已入库 2-已取消',
  `senderId` varchar(36) NOT NULL COMMENT '送货人id',
  `senderName` varchar(64) NOT NULL COMMENT '送货人名称',
  `senderDepartmentId` varchar(36) NOT NULL COMMENT '送货方部门id',
  `senderDepartmentName` varchar(64) NOT NULL COMMENT '送货方部门名称',
  `senderCompanyId` varchar(36) NOT NULL COMMENT '送货方公司id',
  `senderCompanyName` varchar(128) NOT NULL COMMENT '送货方公司名称',
  `sumCount` int NOT NULL COMMENT '总数量',
  `sumPrice` decimal(14,2) NOT NULL COMMENT '总金额(小写)',
  `sumPriceChinese` varchar(128) NOT NULL COMMENT '总金额(大写)',
  `approverId` varchar(36) NULL COMMENT '核验人id',
  `approverName` varchar(64) NULL COMMENT '核验人名称',
  `approverSignatureId` varchar(36) NULL COMMENT '核验人签名id',
  `approverDepartmentId` varchar(36) NULL COMMENT '库存方部门id',
  `approverDepartmentName` varchar(64) NULL COMMENT '库存方部门名称',
  `approverCompanyId` varchar(36) NULL COMMENT '库存方公司id',
  `approverCompanyName` varchar(128) NULL COMMENT '库存方公司名称',
  `remark` varchar(128) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '配件入库单';

CREATE TABLE `spare_part_in_item` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `priceType` int NOT NULL COMMENT '价格类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `specification` varchar(128) NOT NULL COMMENT '规格',
  `unit` varchar(36) NULL COMMENT '单位',
  `price` decimal(14,2) NOT NULL COMMENT '单价',
  `count` int NOT NULL COMMENT '数量',
  `sumPrice` decimal(14,2) NOT NULL COMMENT '金额',
  `sort` int NOT NULL default 0 COMMENT '排序',
  `status` int NOT NULL default 0 COMMENT '状态:0-申请中 1-待入库 2-待确认 2-已入库 3-已取消',
  `sparePartInBillId` varchar(36) NULL COMMENT '入库单id',
  `repairReplacementId` varchar(36) NULL COMMENT '维修配件更换id',
  `procurementId` varchar(36) NULL COMMENT '采购项目id',
  `procurementDetailId` varchar(36) NULL COMMENT '采购详情id',
  `manufacturerName` varchar(128) NULL COMMENT '生产厂商名称',
  `model` varchar(64) NULL COMMENT '型号',
  `sparePartId` varchar(36) NULL COMMENT '入库后的批次id',
  `remark` varchar(128) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '配件入库项';