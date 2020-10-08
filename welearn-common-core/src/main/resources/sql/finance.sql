-- 2018/11/08
CREATE TABLE `invoice` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `refType` varchar(64) NULL COMMENT '关联类型-表名',
  `refId` varchar(36) NULL COMMENT '关联类型id',
  `description` varchar(128) NULL COMMENT '关联简述',
  `type` int NOT NULL COMMENT '发票类型:0-增值税普通发票, 1-增值税专用发票, 2-其他发票',
  `code` varchar(16) NOT NULL COMMENT '发票代码',
  `number` varchar(16) NOT NULL COMMENT '发票号码',
  `issuedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开票日期',
  `closedAt` timestamp NULL COMMENT '结算日期',
  `status` int NOT NULL DEFAULT 0 COMMENT '发票状态:0-未结算 1-部分结算 2-已结算',
  `sellerId` varchar(36) NULL COMMENT '销售方公司id',
  `sellerName` varchar(64) NULL COMMENT '销售方公司名称',
  `sellerCode` varchar(64) NULL COMMENT '销售方公司纳税人识别号',
  `sellerAddress` varchar(128) NULL COMMENT '销售方公司地址',
  `sellerPhone` varchar(16) NULL COMMENT '销售方公司电话',
  `sellerBank` varchar(64) NULL COMMENT '销售方公司开户行',
  `sellerAccount` varchar(64) NULL COMMENT '销售方公司账号',
  `buyerId` varchar(36) NULL COMMENT '购买方公司id',
  `buyerName` varchar(64) NULL COMMENT '购买方公司名称',
  `buyerCode` varchar(64) NULL COMMENT '购买方公司纳税人识别号',
  `buyerAddress` varchar(128) NULL COMMENT '购买方公司地址',
  `buyerPhone` varchar(16) NULL COMMENT '购买方公司电话',
  `buyerBank` varchar(64) NULL COMMENT '购买方公司开户行',
  `buyerAccount` varchar(64) NULL COMMENT '购买方公司账号',
  `totalAmount` decimal(14,2) NOT NULL COMMENT '合计金额',
  `totalTax` decimal(14,2) NOT NULL COMMENT '合计税额',
  `balance` decimal(14,2) NOT NULL COMMENT '待结金额',
  `amountLower` decimal(14,2) NOT NULL COMMENT '价税合计小写',
  `amountUpper` varchar(64) NULL COMMENT '价税合计大写',
  `remark` varchar(512) NULL COMMENT '发票备注',
  `uploadType` int NULL COMMENT '上传类型',
  `uploadFile` text NULL COMMENT '上传文件',
  `detailJson` text NOT NULL COMMENT '发票明细',
  PRIMARY KEY (`id`)
) COMMENT '发票';

CREATE TABLE `invoice_payment` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `invoiceId` varchar(36) NOT NULL COMMENT '发票id',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `operatorId` varchar(36) NOT NULL COMMENT '操作人员id',
  `operatorName` varchar(36) NOT NULL COMMENT '操作人员名称',
  `amount` decimal(14,2) NOT NULL COMMENT '结算金额',
  `voucher` text NOT NULL COMMENT '结算凭证',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '发票结算记录';


CREATE TABLE `stock_place` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `type` int NOT NULL COMMENT '类型:0-公司仓库 1-部门仓库',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `departmentId` varchar(36) NULL COMMENT '部门id',
  `buildingId` varchar(36) NOT NULL COMMENT '建筑id',
  `floorId` varchar(36) NOT NULL COMMENT '楼层id',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) COMMENT '库存仓库';

-- 每一个货位 对应一个 标签 可通过扫码方式 减少选择货位的操作
CREATE TABLE `stock_allocation` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `stockPlaceId` varchar(36) NOT NULL COMMENT '库存仓库id',
  `x` int NOT NULL COMMENT '坐标系x',
  `y` int NOT NULL COMMENT '坐标系y',
  `z` int NOT NULL COMMENT '坐标系z',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) COMMENT '库存货位';

-- 类型id 和 类型规格 对应一个唯一的 公司库存类型
CREATE TABLE `stock_type` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `type` int NOT NULL COMMENT '对象类型:0-医疗设备 1-设备备件 2-医用耗材',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `itemTypeId` varchar(36) NOT NULL COMMENT '类型id',
  `itemTypeName` varchar(64) NOT NULL COMMENT '类型名称',
  `specification` varchar(128) NOT NULL COMMENT '类型规格',
  `description` varchar(128) NULL COMMENT '类型描述',
  `unit` varchar(36) NOT NULL COMMENT '基本单位',
  PRIMARY KEY (`id`)
) COMMENT '库存类型';

-- 2018/12/10 库存类型 与 库存 是一对多关系, 但与公司库存是一一对应
CREATE TABLE `stock` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `type` int NOT NULL COMMENT '类型:0-公司仓库 1-部门仓库',
  `parentId` varchar(36) NULL COMMENT '父库存id 部门库存使用',
  `departmentId` varchar(36) NULL COMMENT '部门id 用于标记属于哪个部门, 对于公司仓库为空',
  `stockTypeId` varchar(36) NOT NULL COMMENT '库存类型id',
  `inTransitInCount` int NOT NULL DEFAULT 0 COMMENT '在途入库数量(内含数量 表示将有货物运进来)',
  `inTransitOutCount` int NOT NULL DEFAULT 0 COMMENT '在途出库数量(内含数量 表示将有货物运出去)',
  `sumPurchaseCount` int NOT NULL DEFAULT 0 COMMENT '总购买量(内含数量)',
  `sumPlanConsumeCount` int NOT NULL DEFAULT 0 COMMENT '计划总消耗量(内含数量) 医院库存出库 到 部门库存时登记该值 (部门库存时则与总购买量一致)',
  `sumRealConsumeCount` int NOT NULL DEFAULT 0 COMMENT '实际总消耗量(内含数量) 部门库存出库 时 登记该值',
  `lastConsumeAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次实际消耗时间',
  `safeCount` decimal(5,2) NOT NULL DEFAULT 0  COMMENT '安全库存量(加急订货耗时天数 * 平均日消耗量)',
  `maxCount` decimal(5,2) NOT NULL DEFAULT 0 COMMENT '最高库存量(到达订货存量点时, 订购 (最高库存量 - 安全库存量)/包装内含数量 包装数量 的货物 )',
  `orderCount` decimal(5,2) NOT NULL DEFAULT 0 COMMENT '订货存量点(平均订货耗时天数 * 平均日消耗量 + 安全库存量)',
  `orderAvgCostDay` decimal(5,2) NOT NULL DEFAULT 0 COMMENT '平均订货耗时(天, 计算最近一年的平均值, 每月更新一次)',
  `dayAvgCostCount` decimal(5,2) NOT NULL DEFAULT 0 COMMENT '平均每日消耗(内含数量, 计算最近三月的平均值, 每月更新一次)',
  `holdingCostRatio` decimal(5,2) NOT NULL DEFAULT 0 COMMENT '库存持有成本占比(0-100.00)',
  `executeType` int NOT NULL DEFAULT 1 COMMENT '执行: 0-按计划执行(批量采购) 1-按实际执行(零库存, 但部门出库登记需及时)',
  PRIMARY KEY (`id`)
) COMMENT '库存';

-- 每月开始会记录下库存的相关参数
CREATE TABLE `stock_log` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `stockId` varchar(36) NOT NULL COMMENT '库存id',
  `stockTypeId` varchar(36) NOT NULL COMMENT '库存类型id',
  `inTransitInCount` int NOT NULL DEFAULT 0 COMMENT '在途入库数量(内含数量 表示将有货物运进来)',
  `inTransitOutCount` int NOT NULL DEFAULT 0 COMMENT '在途出库数量(内含数量 表示将有货物运出去)',
  `sumPurchaseCount` int NOT NULL COMMENT '总购买量',
  `sumPlanConsumeCount` int NOT NULL COMMENT '计划总消耗量',
  `sumRealConsumeCount` int NOT NULL COMMENT '实际总消耗量',
  `orderAvgCostDay` decimal(5,2) NOT NULL COMMENT '平均订货耗时',
  `dayAvgCountCost` decimal(5,2) NOT NULL COMMENT '平均每日消耗',
  `holdingCostRatio` decimal(5,2) NOT NULL DEFAULT 0 COMMENT '库存持有成本占比(0-100.00)',
  PRIMARY KEY (`id`)
) COMMENT '库存日志';

-- 允许一个货位上出现多个批次货位, 当批次余量为 0 时须将该关联禁用掉
CREATE TABLE `re_batch_allocation` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `placeId` varchar(36) NOT NULL COMMENT '仓库id',
  `allocationId` varchar(36) NOT NULL COMMENT '货位id',
  `batchId` varchar(36) NOT NULL COMMENT '批次id',
  PRIMARY KEY (`id`)
) COMMENT '库存类型';

-- 批次可拆分出 多个子批次, 每个库存任务则对应一个批次, 所有的入库出库操作均是对批次的操作
CREATE TABLE `stock_batch` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `stockId` varchar(36) NOT NULL COMMENT '库存id',
  `stockTypeId` varchar(36) NOT NULL COMMENT '库存类型id',
  `parentId` varchar(36) NULL COMMENT '父批次id (批次追踪)',
  `packageUnit` varchar(36) NOT NULL COMMENT '包装单位',
  `sumCount` int NOT NULL DEFAULT 0 COMMENT '总量(内含数量)',
  `realRemainCount` int NOT NULL DEFAULT 0 COMMENT '余量(内含数量)',
  `planRemainCount` int NOT NULL DEFAULT 0 COMMENT '计划余量(内含数量, 默认与余量等值, 如有相关库存任务在处理时 被取出数量 则会从计划余量中扣除)',
  `price` decimal(14,2) NOT NULL DEFAULT 0 COMMENT '单价(内含数量)',
  `orderCostDay` int NOT NULL DEFAULT 0 COMMENT '订货耗时(天)',
  `producedAt` timestamp NULL COMMENT '生产日期',
  `expiredAt` timestamp NULL COMMENT '保质期限(截止时间)',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态: 0-正常 1-封存中 2-待退换 3-待报废',
  `remark` varchar(128) NULL COMMENT '备注',
  `description` varchar(256) NOT NULL COMMENT '描述(必填)',
  `model` varchar(128) NOT NULL COMMENT '物品型号',
  `manufacturerName` varchar(128) NOT NULL COMMENT '生产厂商名称',
  `procurementId` varchar(36) NULL COMMENT '采购项目id',
  `supplierId` varchar(36) NULL COMMENT '供应商id',
  PRIMARY KEY (`id`)
) COMMENT '库存批次';

-- 同一时间 每个货物 只会有一个 可用 关联
CREATE TABLE `re_batch_package` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `batchId` varchar(36) NOT NULL COMMENT '批次id',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态:0-在库 1-离库 2-出库 3-消耗',
  `creatorId` varchar(36) NOT NULL COMMENT '创建人id',
  `updateUserId` varchar(36) NULL COMMENT '更新人id',
  `sscc` varchar(128) NOT NULL COMMENT '货物包装SSCC',
  PRIMARY KEY (`id`)
) COMMENT '库存批次与货物包装关联';

-- 包装上不应该携带数量 数量有系统管理 否则需要频繁的更换标签
CREATE TABLE `stock_package` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `remark` varchar(128) NULL COMMENT '备注',
  `rePackageId` varchar(36) NULL COMMENT '关联包装id, 在包装拆分或合并时会使用',
  `amount` int NOT NULL DEFAULT 0 COMMENT '包装创建时内含数量',
  `count` int NOT NULL DEFAULT 0 COMMENT '包装当前的内含数量',
  `sscc` varchar(36) NOT NULL COMMENT '货物包装SSCC(与 id 一一对应)',
  `creatorId` varchar(36) NOT NULL COMMENT '创建人id',
  `stockTypeId` varchar(36) NOT NULL COMMENT '库存类型id',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态: 0-虚拟态(系统创建了该SSCC条码) 1-现实态(库存人员将该SSCC条码打印并贴在箱子上)',
  PRIMARY KEY (`id`)
) COMMENT '库存货物包装';

-- 如为 耗材 或 备件 则可不关联, 系统不对其做进一步的单件做细致管理
CREATE TABLE `re_package_item` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `packageId` varchar(36) NOT NULL COMMENT '货物包装id',
  `itemId` varchar(36) NOT NULL COMMENT '物品id',
  PRIMARY KEY (`id`)
) COMMENT '库存货物包装与内含物品关联';

CREATE TABLE `stock_task` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否可用：0-不可用，1-可用',
  -- 任务主要信息
  `stockId` varchar(36) NOT NULL COMMENT '库存id',
  `type` int NOT NULL COMMENT '任务类型: 0-入库 1-出库 2-消耗',
  `taskCount` int NOT NULL COMMENT '任务数量(内含数量)',
  `finishCount` int NOT NULL COMMENT '完成数量(内含数量)',
  `typeDescription` varchar(128) NOT NULL COMMENT '任务类型说明',
  `status` int NOT NULL DEFAULT 0 COMMENT '任务状态: 0-待处理 1-处理中 2-已完成 3-已取消',
  -- 对于 内部的 两个库存间 货物的转移, 则对两个库存 分别创建任务, 一个入 一个出 通过该字段关联两任务
  `reTaskId` varchar(36) NULL COMMENT '关联任务id',
  -- 任务人员信息
  `commanderId` varchar(36) NULL COMMENT '任务派发人id',
  `commanderName` varchar(64) NULL COMMENT '任务派发人名称',
  `commanderDepartmentId` varchar(36) NOT NULL COMMENT '任务派发人部门id',
  `commanderDepartmentName` varchar(128) NOT NULL COMMENT '任务派发人部门名称',
  `commanderRemark` varchar(256) NULL COMMENT '任务派发人备注',
  `operatorId` varchar(36) NULL COMMENT '任务执行人id',
  `operatorName` varchar(64) NULL COMMENT '任务执行人名称',
  `operatorDepartmentId` varchar(36) NULL COMMENT '任务执行人部门id',
  `operatorDepartmentName` varchar(128) NULL COMMENT '任务执行人部门名称',
  `operatorRemark` varchar(256) NULL COMMENT '任务执行人备注',
  `cancelReason` varchar(256) NULL COMMENT '任务取消原因',
  PRIMARY KEY (`id`)
) COMMENT '库存任务';

-- 外部入库时会根据采购项目 创建一个批次 保存在此, 内部入库时每扫一次条码会入一次库, 第一次扫码会创建一个批次, 保存在此处
-- 对于公司库存 出库时相关的批次 也会保存在此, 所有批次 可通过该表查询到相关出入库记录
CREATE TABLE `re_task_batch` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `taskId` varchar(36) NOT NULL COMMENT '任务id',
  `batchId` varchar(36) NOT NULL COMMENT '批量id',
  PRIMARY KEY (`id`)
) COMMENT '库存任务与批量关联';

-- 对于入库的任务 会创建该关联
CREATE TABLE `re_task_package` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `taskId` varchar(36) NOT NULL COMMENT '任务id',
  `packageId` varchar(36) NOT NULL COMMENT '包装id',
  `packageCount` int NOT NULL COMMENT '包装内含数量',
  PRIMARY KEY (`id`)
) COMMENT '库存任务与货物包装关联';

-- 2018/11/8
CREATE TABLE `fixed_assets` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  -- 物品信息
  `itemType` int NOT NULL COMMENT '物品类型: 0-医疗设备',
  `itemId` varchar(36) NULL COMMENT '物品id',
  `itemName` varchar(128) NOT NULL COMMENT '物品名称',
  `itemSpecification` varchar(256) NULL COMMENT '物品规格',
  `itemModel` varchar(128) NOT NULL COMMENT '物品型号',
  `itemManufacturer` varchar(128) NOT NULL COMMENT '物品生产厂商名称',
  -- 资产信息
  `no` varchar(64) NULL COMMENT '固定资产编号',
  `type` int NOT NULL DEFAULT 0 COMMENT '固定资产类型: 0-生产用固定资产 1-非生产用固定资产 2-租出固定资产 3-未使用固定资产 4-不需用固定资产 5-融资租赁固定资产 6-接受捐赠固定资产',
  `origin` int NOT NULL DEFAULT 0 COMMENT '资产来源: 0-数据导入 1-采购添加 ',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态: 0-使用中的固定资产 1-未使用的固定资产 2-不需用的固定资产',
  `accountingAt` timestamp NULL COMMENT '入账时间',
  `originalValue` decimal(14,2) NOT NULL COMMENT '原值',
  `residualValue` decimal(14,2) NOT NULL COMMENT '残值',
  `estimatedNetSalvageRate` decimal(5,2) NOT NULL DEFAULT 5 COMMENT '预计净残值率(0-100.00)',
  `expectedUsefulLife` int NULL COMMENT '预计使用年限',
  `sumWorkloadExpectancy` int NULL COMMENT '预计总工作量',
  -- 折旧信息
  `isDepreciation` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否折旧：0-不折旧，1-折旧',
  `depreciationMethod` int NULL default 0 COMMENT '折旧方式: 0-平均年限折旧法Ⅰ 1-平均年限折旧法Ⅱ 2-年数总和法 3-双倍余额递减法 4-工作量法',
  `monthsOfDepreciation` int NOT NULL DEFAULT 0 COMMENT '已提月份',
  `accumulatedDepreciation` decimal(14,2) NOT NULL DEFAULT 0  COMMENT '累计折旧',
  -- 资产位置
  `departmentId` varchar(36) NOT NULL COMMENT '部门id',
  `departmentName` varchar(36) NOT NULL COMMENT '部门名称',
  `address` varchar(256) NOT NULL COMMENT '资产位置',
  -- 公司信息
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `companyName` varchar(36) NOT NULL COMMENT '公司名称',
  -- 入账信息
  `creatorId` varchar(36) NULL COMMENT '创建人id',
  `creatorName` varchar(64) NULL COMMENT '创建人名称',
  `isCheck` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否入账确认：0-未确认 1-已确认',
  `applyId` varchar(36) NULL COMMENT '入账申请id',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '固定资产';

-- 2018/11/8 TODO:待确定字段
CREATE TABLE `depreciation_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) COMMENT '折旧记录';