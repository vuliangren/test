CREATE TABLE `repair_precept` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `serveType` int NOT NULL COMMENT '适用类型: 0-类型 1-产品 2-设备 3-全部',
  `serveId` varchar(36) NULL COMMENT '适用 类型/产品/设备id',
  `serveName` varchar(128) NOT NULL COMMENT '适用类型名称',
  `outlook` varchar(128) NOT NULL COMMENT '故障简述',
  `detail` varchar(512) NOT NULL COMMENT '故障详述',
  `lever` int NOT NULL default 0 COMMENT '紧急程度(同报修紧急程度)',
  `reporterAdvice` varchar(512) NULL COMMENT '报修方建议',
  `engineerAdvice` varchar(512) NULL COMMENT '维修方建议',
  `companyId` varchar(36) NOT NULL COMMENT '所属公司id',
  `hitCount` int NOT NULL default 0 COMMENT '命中次数',
  `engineerId` varchar(36) NULL COMMENT '创建工程师id',
  `engineerName` varchar(64) NULL COMMENT '创建工程师姓名',
  PRIMARY KEY (`id`)
) COMMENT '报修预案';

CREATE TABLE `repair_request` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `equipmentName` varchar(128) NOT NULL COMMENT '设备名称',
  `equipmentTypeId` varchar(36) NOT NULL COMMENT '设备类型id',
  `equipmentTypeName` varchar(128) NOT NULL COMMENT '设备类型名称',
  `guaranteeRepairExpiredAt` timestamp NULL COMMENT '过保日期',
  `reporterId` varchar(36) NOT NULL COMMENT '报修人id',
  `reporterName` varchar(64) NOT NULL COMMENT '报修人名称',
  `reporterPhone` varchar(11) NOT NULL COMMENT '报修人电话',
  `reporterDepartmentId` varchar(36) NOT NULL COMMENT '报修人部门id',
  `reporterDepartmentName` varchar(64) NOT NULL COMMENT '报修人部门名称',
  `reporterCompanyId` varchar(36) NOT NULL COMMENT '报修人公司id',
  `reporterCompanyName` varchar(64) NOT NULL COMMENT '报修人公司名称',
  `serveCompanyId` varchar(36) NOT NULL COMMENT '服务公司id',
  `serveCompanyName` varchar(64) NOT NULL COMMENT '服务公司名称',
  `preceptId` varchar(36) NOT NULL COMMENT '故障预案',
  `description` varchar(512) NOT NULL COMMENT '故障描述',
  `picture` text NULL COMMENT '故障图片',
  `lever` int NOT NULL default 0 COMMENT '紧急程度: 0-一般 1-严重 2-紧急',
  `sort` int NOT NULL default 0 COMMENT '优先级排序',
  `status` int NOT NULL default 0 COMMENT '状态: 0-待派工 1-待重派 2-待领工 3-待维修 4-维修中 5-待验收 6-待评价 7-已完成 8-待取消 9-已取消 10-已中止 11-待厂商维修 12-待外援维修 13-待中止',
  `isReal` tinyint(1) NOT NULL default 1 COMMENT '报修是否属实',
  `isCancel` tinyint(1) NOT NULL default 0 COMMENT '报修是否取消',
  `isPartRepalce` tinyint(1) NOT NULL default 0 COMMENT '是否更换配件',
  `isPartWaiting` tinyint(1) NOT NULL default 0 COMMENT '是否等待配件',
  `isHelpApply` tinyint(1) NOT NULL default 0 COMMENT '是否申请外派',
  `isWarranty` tinyint(1) NOT NULL default 0 COMMENT '是否厂家保修',
  `isSummarize` tinyint(1) NOT NULL default 0 COMMENT '是否提交总结',
  `isSuggestScrap` tinyint(1) NOT NULL default 0 COMMENT '是否建议报废',
  `isDegrade` tinyint(1) NOT NULL default 0 COMMENT '是否降级使用',
  `remark` text NULL COMMENT '备注:取消说明/中止说明',
  `finishedAt` timestamp NULL COMMENT '完成时间(用户确认维修完成的时间)',
  PRIMARY KEY (`id`)
) COMMENT '报修申请';

CREATE TABLE `repair_dispatch_inside` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `engineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '工程师姓名',
  `status` int NOT NULL default 0 COMMENT '状态: 0-待领工 1-已领工 2-已重派 3-已取消 4-已完工',
  `receivedAt` timestamp NULL COMMENT '领工时间',
  `workTimeJson` text NULL COMMENT '工作时间段JSON',
  `isReDispatch` tinyint(1) NOT NULL default 0 COMMENT '是否重派',
  `reDispatchDescription` varchar(512) NULL COMMENT '申请重派说明(申请重派时填写)',
  `reDispatchReason` varchar(512) NULL COMMENT '报修重派原因(重新派工时填写)',
  PRIMARY KEY (`id`)
) COMMENT '报修内部派工';

CREATE TABLE `repair_dispatch_outside` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `contractId` varchar(36) NULL COMMENT '与服务方签订的合同id',
  `companyId` varchar(36) NULL COMMENT '服务方公司id',
  `companyName` varchar(128) NOT NULL COMMENT '服务方公司名称',
  `workTimeJson` text NULL COMMENT '工作时间段JSON',
  `method` int NOT NULL default 0 COMMENT '维修方式: 0-上门维修 1-邮寄维修',
  `mailStatus` int NOT NULL default 0 COMMENT '快递状态: 0-待寄件 1-已寄件 2-已确认 3-待收件 4-已收件',
  `mailSendInfo` text NULL COMMENT '快递寄件信息',
  `mailReceiveInfo` text NULL COMMENT '快递收件信息',
  `isArrive` tinyint(1) NOT NULL default 0 COMMENT '是否到达',
  `isFinish` tinyint(1) NOT NULL default 0 COMMENT '是否完成',
  `isBreach` tinyint(1) NOT NULL default 0 COMMENT '是否违约',
  `result` int NULL COMMENT '维修结果:0-维修成功 1-等待配件 2-维修失败 3-已取消',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '报修外部派工';

CREATE TABLE `repair_dispatch_outside_detail` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `dispatchOutsideId` varchar(36) NULL COMMENT '外部派工id',
  `engineerId` varchar(36) NULL COMMENT '工程师id',
  `engineerName` varchar(64) NULL COMMENT '工程师名称',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '报修外部派工详情';

CREATE TABLE `repair_cost_item` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `dispatchId` varchar(36) NOT NULL COMMENT '派工id',
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
) COMMENT '维修消费项目(针对派工)';

CREATE TABLE `repair_check` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `dispatchId` varchar(36) NOT NULL COMMENT '内部派工id',
  `engineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '工程师名称',
  `acceptorId` varchar(36) NOT NULL COMMENT '验收人id',
  `acceptorName` varchar(64) NOT NULL COMMENT '验收人名称',
  `result` tinyint(1) NOT NULL COMMENT '是否可用：0-失败，1-成功',
  `remark` varchar(256) NULL COMMENT '备注(验收失败时的原因)',
  `signatureId` varchar(36) NULL COMMENT '签名id',
  PRIMARY KEY (`id`)
) COMMENT '报修验收记录';

CREATE TABLE `repair_interrupt` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `applyId` varchar(36) NULL COMMENT '申请id',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `dispatchId` varchar(36) NOT NULL COMMENT '内部派工id',
  `engineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '工程师名称',
  `reason` varchar(512) NOT NULL COMMENT '中止原因',
  `result` tinyint(1) NULL COMMENT '审批结果：0-通过 1-驳回',
  PRIMARY KEY (`id`)
) COMMENT '报修中止申请';

CREATE TABLE `repair_evaluation` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `engineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '工程师姓名',
  `punctual` decimal(5,2) NOT NULL default 5.0 COMMENT '守时评价',
  `attitude` decimal(5,2) NOT NULL default 5.0 COMMENT '态度评价',
  `service` decimal(5,2) NOT NULL default 5.0 COMMENT '服务评价',
  `comment` varchar(256) NULL COMMENT '评语',
  `appraiserid` varchar(36) NOT NULL COMMENT '评价人id',
  `appraiserName` varchar(64) NOT NULL COMMENT '评价人姓名',
  `appraiserDepartmentId` varchar(36) NOT NULL COMMENT '评价人部门id',
  `appraiserDepartmentName` varchar(64) NOT NULL COMMENT '评价人部门名称',
  PRIMARY KEY (`id`)
) COMMENT '维修服务评价(针对工程师)';

CREATE TABLE `repair_summary` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `engineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '工程师名称',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `equipmentName` varchar(128) NOT NULL COMMENT '设备名称',
  `description` varchar(36) NOT NULL COMMENT '故障描述(富文本)',
  `reason` varchar(36) NOT NULL COMMENT '起因分析(富文本)',
  `method` int NOT NULL COMMENT '维修方式: 0-厂家保修 1-自主维修 2-外援维修 3-其它',
  `handleRecord` varchar(36) NOT NULL COMMENT '处理方法(富文本)',
  `engineerAdvice` varchar(512) NULL COMMENT '给维修方建议',
  `reporterAdvice` varchar(512) NULL COMMENT '给报修方建议',
  PRIMARY KEY (`id`)
) COMMENT '维修总结';

CREATE TABLE `repair_replacement` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `dispatchId` varchar(36) NOT NULL COMMENT '派工id',
  `engineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '工程师名称',
  `outsideDispatchId` varchar(36) NULL COMMENT '外部派工id',
  `outsideEngineerId` varchar(36) NULL COMMENT '外部工程师id',
  `outsideEngineerName` varchar(64) NULL COMMENT '外部工程师名称',
  `type` int NOT NULL COMMENT '配件类型: 0-廉价配件 1-低价配件 2-高价配件 3-超额配件',
  `status` int NOT NULL default 0 COMMENT '状态: 0-待审批 1-待采购 2-待入库 3-待出库 4-待更换 5-审批失败 6-待取消 7-已取消 8-已更换',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `specification` varchar(128) NOT NULL COMMENT '规格',
  `description` varchar(256) NULL COMMENT '描述',
  `price` decimal(14,2) NOT NULL COMMENT '预计单价',
  `count` int NOT NULL default 1 COMMENT '预计数量',
  `sumPrice` decimal(14,2) NOT NULL COMMENT '预计总价',
  `applyId` varchar(36) NULL COMMENT '配件更换申请id',
  `procurementId` varchar(36) NULL COMMENT '采购项目id',
  `procurementDetailId` varchar(36) NULL COMMENT '采购项目详情id',
  `sparePartOutRepairApplyId` varchar(36) NULL COMMENT '出库申请id',
  `sparePartOutBillId` varchar(36) NULL COMMENT '出库单id',
  `sparePartOutItemId` varchar(36) NULL COMMENT '出库项id',
  `signatureId` varchar(36) NOT NULL COMMENT '工程师签名',
  PRIMARY KEY (`id`)
) COMMENT '维修配件更换';

CREATE TABLE `repair_help_apply` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `dispatchId` varchar(36) NOT NULL COMMENT '派工id',
  `engineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '工程师名称',
  `applyId` varchar(36) NULL COMMENT '申请id',
  `description` varchar(512) NULL COMMENT '外援申请说明',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态：0-待审批 1-审批通过 2-审批失败',
  PRIMARY KEY (`id`)
) COMMENT '维修外援申请';

CREATE TABLE `repair_help_quotation_approval` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '报修id',
  `dispatchId` varchar(36) NOT NULL COMMENT '派工id',
  `engineerId` varchar(36) NOT NULL COMMENT '工程师id',
  `engineerName` varchar(64) NOT NULL COMMENT '工程师名称',
  `outsideDispatchId` varchar(36) NULL COMMENT '外部派工id',
  `outsideEngineerId` varchar(36) NULL COMMENT '外部工程师id',
  `outsideEngineerName` varchar(64) NULL COMMENT '外部工程师名称',
  `signatureId` varchar(36) NOT NULL COMMENT '工程师签名',
  `price` decimal(14,2) NOT NULL COMMENT '价格',
  `description` varchar(512) NULL COMMENT '报价说明',
  `applyId` varchar(36) NULL COMMENT '申请id',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态：0-待审批 1-审批通过 2-审批失败',
  PRIMARY KEY (`id`)
) COMMENT '维修外援报价申请';