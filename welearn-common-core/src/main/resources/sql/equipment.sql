-- 2018/10/23 添加
CREATE TABLE `equipment_product` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `commodityName` varchar(128) NOT NULL COMMENT '商品名称',
  `equipmentTypeId` varchar(36) NOT NULL COMMENT '设备类型id',
  `equipmentTypeName` varchar(128) NOT NULL COMMENT '设备类型名称',
  `managementLever` int NOT NULL COMMENT '管理级别:0-Ⅰ 1-Ⅱ 2-Ⅲ',
  `specification` varchar(128) NOT NULL COMMENT '设备规格',
  `modelNumber` varchar(128) NOT NULL COMMENT '设备型号',
  `manufacturerName` varchar(64) NULL COMMENT '生产厂商名称',
  `gtin` varchar(128) NULL COMMENT 'DI(GTIN)',
  `brand` varchar(128) NULL COMMENT '品牌',
  `isSterileProvide` tinyint(1) NOT NULL DEFAULT 0 COMMENT '无菌提供：0-否，1-是',
  `isImportProduct` tinyint(1) NOT NULL DEFAULT 0 COMMENT '进口产品：0-否，1-是',
  `isLargeEquipment` int NOT NULL DEFAULT 0 COMMENT '大型医疗设备：0-非大型医疗设备，1-甲类大型医疗设备 2-乙类大型医疗设备',
  `expectedUsefulLife` int NULL COMMENT '预计使用年限',
  `sumWorkloadExpectancy` int NULL COMMENT '预计总工作量',
  `technicalIndicatorJson` text NULL COMMENT '技术指标',
  `functionsJson` text NULL COMMENT '功能描述',
  `mdRegistrationCertificateId` varchar(36) NULL COMMENT '医疗器械注册证id',
  `mdProductionCertificateId` varchar(36) NULL COMMENT '医疗器械生产企业许可证id',
  `otherCertificatePhotoCopy` text NULL COMMENT '其它证明复印件',
  `sparePartsListJson` text NOT NULL COMMENT '备件清单',
  `accessoryListJson` text NOT NULL COMMENT '附件清单',
  `mainComponentsJson` text NOT NULL COMMENT '主要部件',
  `deliveryComponentsJson` text NOT NULL COMMENT '设备主体拆分说明',
  `electronicDocumentStorage` text NOT NULL COMMENT '电子文档',
  `installType` int NOT NULL DEFAULT 0 COMMENT '安装类型：0-设备无须进行安装，1-销售方负责安装(不含运行环境施工) 2-销售方负责安装(包含运行环境施工) 3-购买方按说明自行安装',
  `installationInfoId` varchar(36) NULL COMMENT '安装说明',
  `environmentInfoId` varchar(36) NULL COMMENT '运行环境说明',
  `maintenanceInfoId` varchar(36) NULL COMMENT '维护说明',
  `repairInfo` varchar(36) NULL COMMENT '维修说明',
  `maintenancePassword` varchar(128) NULL COMMENT '维修密码',
  `faultCodeMeterJson` text NOT NULL COMMENT '故障代码表',
  `descriptionId` varchar(36) NULL COMMENT '产品描述',
  `instructionId` varchar(36) NULL COMMENT '使用说明',
  `warningInfoId` varchar(36) NULL COMMENT '特别说明',
  `storageInfoId` varchar(36) NULL COMMENT '存放说明',
  `wasteTreatmentInfoId` varchar(36) NULL COMMENT '存放说明',
  `wasteClassification` int NULL COMMENT '废物分类',
  `otherInfoId` text NULL COMMENT '其它说明',
  `companyId` varchar(36) NOT NULL COMMENT '创建人公司id',
  `status` int NULL COMMENT '状态:0-审批中 1-审批失败 2-审批通过 3-未上架 4-已上架',
  `currentId` varchar(36) NULL COMMENT '当前产品id 对产品进行修改时使用',
  `applicationId` varchar(36) NULL COMMENT '申请id',
  `photos` text NULL COMMENT '产品相关图片',
  `isInspection` tinyint(1) NOT NULL DEFAULT 0 COMMENT '强检设备：0-否，1-是',
  `supplierCompanyId` varchar(36) NULL COMMENT '供应方公司id',
  `supplierCompanyName` varchar(36) NULL COMMENT '供应方公司名称',
  PRIMARY KEY (`id`)
) COMMENT '设备产品';

CREATE TABLE `equipment` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `udi` varchar(128) NULL COMMENT '统一设备编号',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `supplierId` varchar(36) NOT NULL COMMENT '供应商id',
  `locationId` varchar(36)  NULL COMMENT '位置id',
  `procurementId` varchar(36) NULL COMMENT '采购项目id',
  `fixedAssetId` varchar(36) NULL COMMENT '固定资产id',
  `procurementDetailId` varchar(36) NULL COMMENT '采购项目详情id',
  `equipmentProductId` varchar(36) NOT NULL COMMENT '供应商设备产品id',
  `equipmentProductJson` text NULL COMMENT '供应商设备产品数据快照',
  `commodityName` varchar(128) NOT NULL COMMENT '商品名称',
  `equipmentTypeId` varchar(36) NOT NULL COMMENT '设备类型id',
  `equipmentTypeName` varchar(64) NOT NULL COMMENT '设备类型名称',
  `managementLever` int NOT NULL COMMENT '管理级别:0-Ⅰ 1-Ⅱ 2-Ⅲ',
  `specification` varchar(128) NOT NULL COMMENT '设备规格',
  `modelNumber` varchar(128) NOT NULL COMMENT '设备型号',
  `acceptanceCheckedAt` timestamp NULL COMMENT '验收日期',
  `producedAt` timestamp NULL COMMENT '生产日期',
  `guaranteeRepairExpiredAt` timestamp NULL COMMENT '过保日期',
  `manufacturerName` varchar(64) NOT NULL COMMENT '生产厂商名称',
  `isSterileProvide` tinyint(1) NOT NULL DEFAULT 0 COMMENT '无菌提供：0-否，1-是',
  `isImportProduct` tinyint(1) NOT NULL DEFAULT 0 COMMENT '进口产品：0-否，1-是',
  `isLargeEquipment` int NOT NULL DEFAULT 0 COMMENT '大型医疗设备：0-非大型医疗设备，1-甲类大型医疗设备 2-乙类大型医疗设备',
  `certificateId` varchar(36) NULL COMMENT '大型医疗设备装配许可证id',
  `equipmentStatus` int NOT NULL DEFAULT 0 COMMENT '设备状态: 0-待激活, 1-空闲, 2-运行, 3-故障, 4-报废, 5-遗失, 6-调剂, 7-封存',

  `shouldInspection` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否需要强检：0-不需要，1-需要',
  `serialNumber` varchar(128) NULL COMMENT '生产序列号',
  `functionStatus` int NOT NULL DEFAULT 0 COMMENT '功能状态:0-正常 1-降级使用 2-改造升级',

  `canBorrow` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否可借用：0-不可借用，1-可借用',
  `borrowStatus` int NOT NULL DEFAULT 0 COMMENT '借用状态：0-正常 1-借用待审批 2-借用待领取 3-借用待归还',
  `adjustStatus` int NOT NULL DEFAULT 0 COMMENT '调剂状态：0-正常 1-调剂待审批 2-调剂待封存 3-调剂待处理',
  `repairStatus` int NOT NULL DEFAULT 0 COMMENT '维修状态：0-正常 1-报修待派工 2-报修待领工 3-报修待维修完成 4-维修待验收',
  `maintainStatus` int NOT NULL DEFAULT 0 COMMENT '维护状态：0-正常 1-维护待完成',
  `scrapStatus` int NOT NULL DEFAULT 0 COMMENT '报废状态：0-正常 1-报废待审批 2-报废待封存 3-报废待处理',

  `photos` text NULL COMMENT '设备相关图片',
  `remark` text NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '设备';

-- 2018/10/24
CREATE TABLE `equipment_guarantee_repair_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `companyId` varchar(36) NULL COMMENT '维保公司id',
  `companyName` varchar(36) NOT NULL COMMENT '维保公司名称',
  `companyContact` varchar(16) NOT NULL COMMENT '维保公司联系方式',
  `startAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '保修开始时间',
  `endAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '保修结束时间',
  `type` int NOT NULL DEFAULT 0 COMMENT '保修类型: 0-出厂保修 1-原厂续保 2-第三方保修 3-其它保修',
  `contractId` varchar(36) NOT NULL COMMENT '合同id',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '设备保修记录';

CREATE TABLE `re_equipment_equipment` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `fromEquipmentId` varchar(36) NOT NULL COMMENT '从某个设备id',
  `toEquipmentId` varchar(36) NOT NULL COMMENT '到某个设备id',
  `relationName` varchar(256) NULL COMMENT '关系说明',
  `relationCode` varchar(256) NOT NULL default 'CORRELATION' COMMENT '关系编码',
  PRIMARY KEY (`id`)
) COMMENT '设备间关联关系';

-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

-- equipment-part.sql

-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

-- 2018/10/24
CREATE TABLE `equipment_accessory` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `type` int NULL COMMENT '类型:0-操作附件 1-设备文档',
  `canBorrow` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可借用：0-不可，1-可',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `specification` varchar(64) NULL COMMENT '规格',
  `description` varchar(256) NULL COMMENT '描述',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态: 0-待初始化 1-正常 2-遗失',
  `photos` text NULL COMMENT '图片',
  `remark` text NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '设备附件';

-- 2018/10/24
CREATE TABLE `equipment_permission` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `type` int NOT NULL COMMENT '类型: 0-企业 1-部门 2-员工',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `companyName` varchar(64) NOT NULL COMMENT '公司名称',
  `departmentId` varchar(36) NULL COMMENT '部门id',
  `departmentName` varchar(64) NULL COMMENT '部门名称',
  `employeeId` varchar(36) NULL COMMENT '员工id',
  `employeeName` varchar(64) NULL COMMENT '员工姓名',
  `permission` int NOT NULL COMMENT '所拥有的权力编号 0-...',
  `obtainAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '得权时间',
  `obtainReason` varchar(256) NOT NULL COMMENT '得权描述',
  `loseAt` timestamp NULL COMMENT '失权时间 失权后变为不可用',
  `loseReason` varchar(256) NULL COMMENT '失权描述',
  PRIMARY KEY (`id`)
) COMMENT '设备权限';

-- 2019/03/26
CREATE TABLE `equipment_borrow` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `equipmentTypeId` varchar(36) NOT NULL COMMENT '设备类型id',
  `fixedAssetId` varchar(36) NULL COMMENT '固定资产id',
  `status` int NOT NULL default 0 COMMENT '状态:0-借用待审批 1-借用待领取 2-借出待归还 3-待归还验收 4-验收通过 5-验收失败 6-审批失败 7-取消借用',
  `checkResult` int NULL COMMENT '设备验收结果: 0-正常 1-消耗损坏 2-人为损坏 4-缺失',
  `loanOutAt` timestamp NULL COMMENT '借出时间点',
  `giveBackPlanAt` timestamp NULL COMMENT '计划归还时间点',
  `giveBackRealAt` timestamp NULL COMMENT '实际归还时间点',
  `depreciationPrice` decimal(14,2) NOT NULL default 0.0 COMMENT '折旧单价(每天折旧的金额)',
  `multiplyingPower` decimal(14,2) NOT NULL default 1.0 COMMENT '借用倍率(默认 1.0)',
  `dayPrice` decimal(14,2) NOT NULL default 0.0 COMMENT '借用单价(单位:天 折旧单价 * 借用倍率)',
  `dayCount` decimal(14,2) NULL COMMENT '实际借用天数(精确到0.01天 )',
  `sumPrice` decimal(14,2) NULL COMMENT '借用总花费',
  `satisfaction` int NULL COMMENT '归还满意度',
  `borrowApplicationId` varchar(36) NULL COMMENT '借用申请id',
  `giveBackApplicationId` varchar(36) NULL COMMENT '归还申请id(暂保留)',
  `borrowDepartmentId` varchar(36) NOT NULL COMMENT '借入科室id',
  `borrowDepartmentName` varchar(64) NOT NULL COMMENT '借入科室名称',
  `borrowUserId` varchar(36) NOT NULL COMMENT '借入人员id',
  `borrowUserName` varchar(64) NOT NULL COMMENT '借入人员名称',
  `borrowUserSignatureId` varchar(36) NULL COMMENT '借入人员签字id',
  `giveBackUserId` varchar(36) NULL COMMENT '归还人员id',
  `giveBackUserName` varchar(64) NULL COMMENT '归还人员名称',
  `giveBackUserSignatureId` varchar(36) NULL COMMENT '归还人员签字id',
  `loanOutDepartmentId` varchar(36) NULL COMMENT '借出科室id',
  `loanOutDepartmentName` varchar(64) NULL COMMENT '借出科室名称',
  `loanOutUserId` varchar(36) NULL COMMENT '借出人员id',
  `loanOutUserName` varchar(64) NULL COMMENT '借出人员名称',
  `loanOutUserSignatureId` varchar(36) NULL COMMENT '借出人员签字id',
  `checkUserId` varchar(36) NULL COMMENT '验收人员id',
  `checkUserName` varchar(64) NULL COMMENT '验收人员名称',
  `checkUserSignatureId` varchar(36) NULL COMMENT '验收人员签字id',
  `accessoryCount` int NOT NULL default 0 COMMENT '借用设备附件数量',
  `loanOutBill` text NULL COMMENT '借出领用单',
  `giveBackBill` text NULL COMMENT '归还验收单',
  `borrowRemark` varchar(256) NULL COMMENT '借入备注',
  `loanOutRemark` varchar(256) NULL COMMENT '借出备注',
  `checkRemark` varchar(256) NULL COMMENT '验收备注',
  PRIMARY KEY (`id`)
) COMMENT '设备借用';

-- 2019/03/26
CREATE TABLE `equipment_borrow_accessory` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `borrowId` varchar(36) NOT NULL COMMENT '设备借用id',
  `accessoryId` varchar(36) NOT NULL COMMENT '设备附件id',
  `borrowUserId` varchar(36) NOT NULL COMMENT '借用人员id',
  `loanOutUserId` varchar(36) NULL COMMENT '借出人员id',
  `checkUserId` varchar(36) NULL COMMENT '验收人员id',
  `checkUserName` varchar(64) NULL COMMENT '验收人员名称',
  `status` int NOT NULL default 0 COMMENT '验收结果: 0-正常 1-损坏 2-缺失',
  `checkedAt` timestamp NULL COMMENT '验收时间',
  PRIMARY KEY (`id`)
) COMMENT '设备借用附件项';

CREATE TABLE `equipment_scrap_apply` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentTypeId` varchar(36) NOT NULL COMMENT '设备类型id',
  `productId` varchar(36) NOT NULL COMMENT '设备产品id',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `equipmentName` varchar(64) NOT NULL COMMENT '设备名称规格',
  `equipmentProductName` varchar(128) NOT NULL COMMENT '设备厂商型号',
  `originValue` decimal(14,2) NOT NULL COMMENT '设备单价',
  `applicantId` varchar(36) NOT NULL COMMENT '申请人id',
  `applicantName` varchar(32) NOT NULL COMMENT '申请人姓名',
  `applicantDepartmentId` varchar(36) NOT NULL COMMENT '申请人部门id',
  `applicantDepartmentName` varchar(64) NOT NULL COMMENT '申请人部门名称',
  `applicantCompanyId` varchar(36) NOT NULL COMMENT '申请人公司id',
  `applicantSignatureId` varchar(64) NOT NULL COMMENT '申请人签名id',
  `reasonTypeJson` varchar(128) NOT NULL default '[]' COMMENT '报废理由: [0,1,2...]',
  `reasonDetail` varchar(512) NULL COMMENT '报废理由备注',
  `description` varchar(512) NULL COMMENT '报废说明',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态: 0-待审批 1-审批通过 2-审批失败',
  `applicationId` varchar(36) NULL COMMENT '申请id',
  `equipmentDepartmentId` varchar(36) NOT NULL COMMENT '设备所属部门id',
  PRIMARY KEY (`id`)
) COMMENT '设备报废申请';

-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

CREATE TABLE `engineer` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `userId` varchar(36) NULL COMMENT '人员id',
  `userName` varchar(64) NOT NULL COMMENT '人员名称',
  `userCompanyId` varchar(36) NULL COMMENT '人员所属公司id',
  `userCompanyName` varchar(128) NULL COMMENT '人员所属公司名称',
  `userDepartmentId` varchar(36) NULL COMMENT '人员所属部门id',
  `userDepartmentName` varchar(128) NULL COMMENT '人员所属部门名称',
  `serveCompanyId` varchar(36) NOT NULL COMMENT '服务于公司id',
  `serveCompanyName` varchar(64) NOT NULL COMMENT '服务于公司名称',
  `telephone` varchar(16) NULL COMMENT '工程师电话',
  `email` varchar(64) NULL COMMENT '工程师邮箱',
  `qq` varchar(16) NULL COMMENT '工程师QQ',
  `wechat` varchar(64) NULL COMMENT '工程师微信',
  `type` int NOT NULL COMMENT '工程师类型: 0-内部工程师 1-外部工程师 2-临时工程师',
  `experience` int NOT NULL default 0 COMMENT '经验积分',
  `remark` varchar(128) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '工程师';
-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

-- 2018/10/19 添加
CREATE TABLE `equipment_type_description` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `dSort` varchar(8) NOT NULL COMMENT '描述编号',
  `uSort` varchar(8) NOT NULL COMMENT '用途编号',
  `description` varchar(1024) NOT NULL COMMENT '描述',
  `usage` varchar(512) NULL COMMENT '用途',
  PRIMARY KEY (`id`)
) COMMENT '设备类型描述';

CREATE TABLE `equipment_type_index` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `description` text NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) COMMENT '设备类型索引';

CREATE TABLE `equipment_type_item` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `indexId` varchar(36) NOT NULL COMMENT '索引id',
  `indexName` varchar(64) NOT NULL COMMENT '索引名称',
  `firstId` varchar(8) NOT NULL COMMENT '一级类别编号',
  `firstName` varchar(64) NOT NULL COMMENT '一级类别名称',
  `secondId` varchar(8) NOT NULL COMMENT '二级类别编号',
  `secondName` varchar(64) NOT NULL COMMENT '二级类别名称',
  `name` varchar(64) NULL COMMENT '品名',
  `managementCategory` varchar(36) NOT NULL COMMENT '管理级别:Ⅰ Ⅱ Ⅲ',
  PRIMARY KEY (`id`)
) COMMENT '设备类型品名';

CREATE TABLE `re_equipment_type_item_description` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `itemId` varchar(36) NOT NULL COMMENT '品名id',
  `descriptionId` varchar(36) NOT NULL COMMENT '描述id',
  PRIMARY KEY (`id`)
) COMMENT '设备类型品名描述关联';

-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

-- equipment-repair.sql

-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

-- 查询设备的最近一次检定记录时间, 根据检定周期 创建待检定记录
CREATE TABLE `inspection_plan` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `no` varchar(36) NOT NULL COMMENT '编号',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `companyName` varchar(64) NOT NULL COMMENT '公司名称',
  `equipmentTypeId` varchar(36) NOT NULL COMMENT '设备类型id',
  `equipmentTypeName` varchar(64) NOT NULL COMMENT '设备类型名称',
  `equipmentProductId` varchar(36) NULL COMMENT '设备产品id',
  `equipmentProductName` varchar(64) NULL COMMENT '设备产品名称',
  `description` varchar(128) NOT NULL COMMENT '简述',
  `accuracy` varchar(64) NULL COMMENT '准确度',
  `lever` varchar(64) NULL COMMENT '等级',
  `range` varchar(128) NULL COMMENT '测量范围',
  `effectiveAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '计划生效时间(当前时间大于生效时间计划才有效)',
  `period` int NOT NULL default 365 COMMENT '检定周期(天)',
  `remindDay` int NOT NULL default 30 COMMENT '提前提醒(天)',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '强检计划';

CREATE TABLE `inspection_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `inspectionPlanId` varchar(36) NULL COMMENT '计划id',
  `typeId` varchar(36) NOT NULL COMMENT '类型id',
  `productId` varchar(36) NOT NULL COMMENT '产品id',
  `equipmentId` varchar(36) NOT NULL COMMENT '设备id',
  `companyId` varchar(36) NOT NULL COMMENT '设备所属公司id',
  `no` varchar(36) NULL COMMENT '证书编号',
  `customerCompanyName` varchar(64) NULL COMMENT '委托方或申请方单位名称',
  `inspectionCompanyName` varchar(64) NULL COMMENT '发出证书的单位名称',
  `measuringDeviceName` varchar(64) NOT NULL COMMENT '被检定计量器具名称',
  `model` varchar(36) NOT NULL COMMENT '型号',
  `specification` varchar(36) NOT NULL COMMENT '规格',
  `manufacturerName` varchar(64) NOT NULL COMMENT '制造厂商',
  `serialNumber` varchar(36) NULL COMMENT '出厂编号',
  `isProcessed` tinyint(1) NOT NULL DEFAULT 0  COMMENT '该检定是否已处理',
  `isQualified` tinyint(1) NOT NULL DEFAULT 1  COMMENT '检定结论是否合格',
  `isAdjusted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '检定是否调修校准',
  `timeoutAt` timestamp NULL COMMENT '超时期限',
  `verifiedAt` timestamp NULL COMMENT '检定时间',
  `expiredAt` timestamp NULL COMMENT '有效期至',
  `accuracy` varchar(64) NULL COMMENT '准确度',
  `lever` varchar(64) NULL COMMENT '等级',
  `range` varchar(128) NULL COMMENT '测量范围',
  `verificationRegulationsJson` varchar(256) NOT NULL default '[]' COMMENT '检定依据:[regulationName, regulationName...]',
  `verificationFile` text NULL COMMENT '检定证书(合格)/检定结果通知书(不合格)',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '强检记录';

-- TODO: 以下表待添加 ///////////////////////////////////////////////////////////////////////////////////////////////////
CREATE TABLE `inspection_index` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '项目名称',
  `requestId` varchar(36) NOT NULL COMMENT '项目说明',
  `requestId` varchar(36) NOT NULL COMMENT '是否医用相关',
  `requestId` varchar(36) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '国家计量强检项目';

CREATE TABLE `inspection_regulation` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '强检项目id',
  `requestId` varchar(36) NOT NULL COMMENT '强检项目名称',
  `requestId` varchar(36) NOT NULL unique COMMENT '编号',
  `requestId` varchar(36) NOT NULL COMMENT '类型: 0-JJG 1-JJF',
  `requestId` varchar(36) NOT NULL COMMENT '规程名称(中文)',
  `requestId` varchar(36) NOT NULL COMMENT '规程名称(英文)',
  `requestId` varchar(36) NOT NULL COMMENT '是否被取代',
  `requestId` varchar(36) NOT NULL COMMENT '取代编号',
  `requestId` varchar(36) NOT NULL COMMENT '链接指引',
  `requestId` varchar(36) NOT NULL COMMENT '是否可下载文件',
  `requestId` varchar(36) NOT NULL COMMENT '文档文件',
  `requestId` varchar(36) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '国家计量检定文档';

CREATE TABLE `inspection_regulation_tag` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `requestId` varchar(36) NOT NULL COMMENT '文档编号',
  `requestId` varchar(36) NOT NULL COMMENT '标签编码',
  `requestId` varchar(36) NOT NULL COMMENT '标签说明',
  PRIMARY KEY (`id`)
) COMMENT '国家计量检定文档分类标签';

CREATE TABLE `re_equipment_type_inspection_regulation` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentTypeId` varchar(36) NOT NULL COMMENT '设备类型id',
  `regulationId` varchar(36) NOT NULL COMMENT '检定文档id',
  PRIMARY KEY (`id`)
) COMMENT '设备类型和检定文档的关联';