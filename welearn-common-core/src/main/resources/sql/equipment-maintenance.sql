CREATE TABLE `maintenance_method` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `serveType` int NOT NULL COMMENT '适用类型: 0-类型 1-产品 2-设备 3-全部',
  `serveId` varchar(36) NULL COMMENT '类型/产品/设备id',
  `serveName` varchar(128) NOT NULL COMMENT '适用类型名称',
  `minInterval` int NOT NULL DEFAULT 1 COMMENT '最短维护间隔(分钟)',
  `score` int NOT NULL DEFAULT 1 COMMENT '维护分值(用于计算工程师绩效)',
  `name` varchar(64) NOT NULL COMMENT '维护方式名称',
  `description` varchar(128) NULL COMMENT '维护方式简述',
  `stepInfoId` varchar(36) NULL COMMENT '维护方式详述id',
  `warningInfoId` varchar(36) NULL COMMENT '维护注意事项id',
  `dataTypeJson` text NULL COMMENT '维护数据记录格式JSON',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  PRIMARY KEY (`id`)
) COMMENT '维护方式';

-- 维护任务
CREATE TABLE `maintenance_task` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `serveType` int NULL COMMENT '任务类型: 0-类型 1-产品 2-设备 3-全部',
  `serveId` varchar(36) NULL COMMENT '类型/产品/设备id',
  `serveName` varchar(128) NOT NULL COMMENT '适用类型名称',
  `orderType` int NOT NULL DEFAULT 0 COMMENT '任务领取类型: 0-指派 1-抢单',
  `taskType` int NOT NULL DEFAULT 0 COMMENT '维护计划类型: 0-预防性维护 1-定期保养',
  `priority` int NOT NULL DEFAULT 1 COMMENT '任务优先级(决定处理优先级的基础高度)',
  `startAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务开始时间',
  `interval` int NOT NULL DEFAULT 0 COMMENT '任务时间间隔(分钟)(0 表示不会循环为单次任务)',
  `timeUnit` int NOT NULL DEFAULT 1 COMMENT '时间单位 1-分 60-时 1440-天 10080-周',
  `name` varchar(64) NOT NULL COMMENT '任务名称',
  `description` varchar(128) NULL COMMENT '任务描述',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `remark` varchar(128) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '维护计划';

-- 具体到每个设备的任务分配信息
CREATE TABLE `task_assignment` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '维护设备id',
  `equipmentDescription` varchar(256) NOT NULL COMMENT '维护设备描述',
  `equipmentPosition` varchar(256) NOT NULL COMMENT '维护设备位置',
  `taskType` int NOT NULL DEFAULT 0 COMMENT '维护计划类型: 0-预防性维护 1-定期保养',
  `taskId` varchar(36) NOT NULL COMMENT '维护任务id',
  `engineerId` varchar(36) NOT NULL COMMENT '维护人员id',
  `engineerName` varchar(64) NOT NULL COMMENT '维护人员名称',
  `priority` int NOT NULL DEFAULT 1 COMMENT '处理优先级(任务优先级决定基础高度, 催促或延期执行后, 处理优先级会在基础值上升高)',
  `status` int NOT NULL DEFAULT 0 COMMENT '任务状态: 0-待领取 1-待处理 2-处理中 3-已处理 4-已取消 5-部分完成',
  `recordId` varchar(36) NULL COMMENT '维护记录id',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `remark` varchar(128) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '维护任务分配(根据维护计划生成)';

CREATE TABLE `maintenance_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '维护设备id',
  `equipmentDescription` varchar(256) NOT NULL COMMENT '维护设备描述',
  `equipmentPosition` varchar(256) NOT NULL COMMENT '维护设备位置',
  `taskId` varchar(36) NULL COMMENT '维护任务id',
  `engineerId` varchar(36) NOT NULL COMMENT '维护人员id',
  `engineerName` varchar(64) NOT NULL COMMENT '维护人员姓名',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `remark` varchar(128) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '维护记录';

-- 每个维护任务可关联多个维护方式
CREATE TABLE `re_maintenance_task_method` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `taskId` varchar(36) NOT NULL COMMENT '维护任务id',
  `methodId` varchar(36) NOT NULL COMMENT '维护方式id',
  PRIMARY KEY (`id`)
) COMMENT '维护任务与维护方式关联';

-- 维护任务分配后, 如存在多个任务同时关联同一个维护方式
-- 当其中一个维护任务处理后,其余维护任务则不需要再处理该维护方式
CREATE TABLE `re_task_assignment_method` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `taskAssignmentId` varchar(36) NOT NULL COMMENT '维护任务分配id',
  `methodId` varchar(36) NOT NULL COMMENT '维护方式id',
  `isProcessed` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已经处理: 0-未处理 1-已处理',
  PRIMARY KEY (`id`)
) COMMENT '维护任务分配与维护方式关联';

-- 每个维护任务可关联多个维护人员
CREATE TABLE `re_maintenance_task_user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `taskId` varchar(36) NOT NULL COMMENT '维护任务id',
  `engineerId` varchar(36) NOT NULL COMMENT '维护人员id',
  `engineerName` varchar(64) NOT NULL COMMENT '维护人员名称',
  PRIMARY KEY (`id`)
) COMMENT '维护任务与维护人员关联';

-- 单个维护记录可能同时与多个维护方式关联
CREATE TABLE `re_maintenance_record_method` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '维护设备id',
  `recordId` varchar(36) NOT NULL COMMENT '维护记录id',
  `methodId` varchar(36) NOT NULL COMMENT '维护方式id',
  `dataJson` text NULL COMMENT '维护数据JSON',
  PRIMARY KEY (`id`)
) COMMENT '维护记录与维护方式关联';

CREATE TABLE `maintenance_cost_item` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `taskId` varchar(36) NOT NULL COMMENT '维护任务id',
  `taskAssignmentId` varchar(36) NOT NULL COMMENT '任务分配id',
  `engineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '工程师名称',
  `type` int NOT NULL DEFAULT 0 COMMENT '类型:0-配件 1-服务 2-其它',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `specification` varchar(64) NULL COMMENT '规格',
  `description` varchar(128) NULL COMMENT '描述',
  `price` decimal(14,2) NOT NULL COMMENT '单价',
  `count` int NOT NULL default 1 COMMENT '数量',
  `sumPrice` decimal(14,2) NOT NULL COMMENT '合计',
  PRIMARY KEY (`id`)
) COMMENT '维护消费项目(针对派工)';