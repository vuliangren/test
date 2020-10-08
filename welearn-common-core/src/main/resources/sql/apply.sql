CREATE TABLE `approval_process` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `creatorCompanyId` varchar(36) NOT NULL COMMENT '创建者公司id(一般为平台公司id)',
  `visitorCompanyType` int NOT NULL default 0 COMMENT '访问者公司类型(编码和此类型映射到唯一的一个流程上): 0-医院 1-供应商 2-平台',
  `isUseDefaultProcessPoint` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否可用(用于一些情况下系统控制的审批流程, 不允许用户添加)：0-不用，1-使用',
  PRIMARY KEY (`id`)
) COMMENT '审批流程';

CREATE TABLE `approval_process_point` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `processId`  varchar(36) NOT NULL COMMENT '审批流程id',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `companyId` varchar(36) NULL COMMENT '公司id',
  `departmentId` varchar(36) NOT NULL COMMENT '部门id',
  `departmentName` varchar(36) NULL COMMENT '部门名称',
  `positionId` varchar(36) NULL COMMENT '职位id',
  `positionName` varchar(36) NULL COMMENT '职位名称',
  `approverId` varchar(36) NULL COMMENT '审批人id',
  `approverName` varchar(36) NULL COMMENT '审批人姓名',
  `type` int NOT NULL COMMENT '类型: 0-审批人 1-职位任意人 2-职位所有人',
  `sort` int NOT NULL COMMENT '排序',
  `approvalType` int NOT NULL COMMENT '审批类型 0-审批 1-提意 2-抄送',
  PRIMARY KEY (`id`)
) COMMENT '审批节点';

CREATE TABLE `approval_application` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `linkId`  varchar(36) NULL COMMENT '分步申请中的上一步申请id',
  `type`  int NOT NULL DEFAULT 0 COMMENT '申请类型: 0-一般申请,1-分步申请',
  `applicantId` varchar(36) NOT NULL COMMENT '申请人id',
  `applicantName` varchar(36) NULL COMMENT '申请人姓名',
  `companyId` varchar(36) NULL COMMENT '公司id',
  `departmentId` varchar(36) NULL COMMENT '申请人所在部门id',
  `departmentName` varchar(36) NULL COMMENT '申请人所在部门名称',
  `applyAt` timestamp NULL COMMENT '申请时间',
  `status` int NOT NULL DEFAULT 0 COMMENT '申请状态 0-待提交 1-待审批 2-待修改 3-通过 4-驳回 5-被取消',
  `contentId` varchar(36) NOT NULL COMMENT '申请内容id',
  `outlook` varchar(1024) NULL COMMENT '内容简述',
  `processId` varchar(36) NOT NULL COMMENT '审批流程id',
  `processJson` text NULL COMMENT '审批流程缓存,用户可对审批流程进行一定的自定义,此处保存自定义后流程的JSON序列化数据',
  PRIMARY KEY (`id`)
) COMMENT '申请信息';

CREATE TABLE `approval_result` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `applicationId` varchar(36) NOT NULL COMMENT '申请id',
  `sort` int NOT NULL COMMENT '排序,对应processJson的某个节点',
  `opinion` varchar(2048) NULL COMMENT '审批意见',
  `result` int NOT NULL COMMENT '审批结果',
  `departmentId` varchar(36) NULL COMMENT '部门id',
  `departmentName` varchar(36) NULL COMMENT '部门名称',
  `approverId` varchar(36) NOT NULL COMMENT '审批人id',
  `approverName` varchar(36) NULL COMMENT '审批人姓名',
  `signatureId` varchar(36) NULL COMMENT '审批人签名',
  `approvalType` int NOT NULL COMMENT '审批类型 0-审批 1-提意 2-抄送',
  `approvalAt` timestamp NULL COMMENT '审批时间',
  PRIMARY KEY (`id`)
) COMMENT '审批结果';

CREATE TABLE `equipment_year_plan` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `year` int NOT NULL COMMENT '年度',
  `companyId` varchar(36) NULL COMMENT '公司id',
  `departmentApplyStart` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '科室申请开始时间',
  `departmentApplyEnd` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '科室申请结束时间',
  `status` int NOT NULL DEFAULT 0 COMMENT '计划状态 0-科室申请阶段 1-委员会评审阶段 2-院长审批阶段 3-采购执行阶段',
  PRIMARY KEY (`id`)
) COMMENT '年度设备计划';

CREATE TABLE `equipment_year_plan_application` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentYearPlanId` varchar(36) NOT NULL COMMENT '年度设备采购计划id',
  `equipmentTypeId` varchar(36) NOT NULL COMMENT '设备类型id',
  `equipmentTypeName` varchar(64) NOT NULL COMMENT '设备类型名称',
  `isLargeEquipment` int NOT NULL DEFAULT 0 COMMENT '大型医疗设备：0-非大型医疗设备，1-甲类大型医疗设备 2-乙类大型医疗设备',
  `specification` varchar(64) NOT NULL COMMENT '设备规格',
  `unitPrice` decimal(14,2) NOT null COMMENT '预估单价',
  `count` int NOT NULL COMMENT '申购数量',
  `sumPrice` decimal(14,2) NOT null COMMENT '预估总价',
  `capitalSource` int NOT NULL COMMENT '资金来源',
  `specialFundsType` int NULL COMMENT '专项资金类型',
  `reasonType` int NOT NULL COMMENT '申请理由类型',
  `oldEquipmentId` varchar(36) NULL COMMENT '原设备信息',
  `reasonDescriptionId` varchar(36) NULL COMMENT '申请理由内容id',
  `recommendsJson` text NOT NULL COMMENT '设备选型',
  `auxiliariesJson` text NOT NULL COMMENT '相关辅助配套设备',
  `serviceYear` int NULL COMMENT '正常服务年限',
  `monthlyUsage` int NULL COMMENT '每月使用人次',
  `fee` decimal(14,2) NULL COMMENT '收费标准/人次',
  `yearlyIncome` decimal(14,2) NULL COMMENT '每年收入',
  `yearlyMaintenanceCost` decimal(14,2) NULL COMMENT '每年维护费用',
  `yearlyConsumablesCost` decimal(14,2) NULL COMMENT '每年耗材费用',
  `yearlyBenefit` decimal(14,2) NULL COMMENT '预计一年经济效益',
  `recoveryCostYears` int NULL COMMENT '预计回收成本年限',
  `socialBenefitAnalysisId` varchar(36) NULL COMMENT '社会效益分析id',
  `technicalFeasibilityAnalysisId` varchar(36) NULL COMMENT '技术可行性分析id',
  `installationConditionsAnalysisId` varchar(36) NULL COMMENT '安装条件可行性分析id',
  `fieldInvestigationAnalysisId` varchar(36) NULL COMMENT '实地考察论证分析id',
  `recommendsTechnicalAnalysisId` varchar(36) NULL COMMENT '选型设备技术评价id',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态: 0-审批中 1-设备科审批失败 2-设备科审批通过 3-委员会审批失败 4-委员会审批通过 5-院长审批失败 6-院长审批通过 7-采购已执行',
  `committeeApprovalResultJson` text NULL COMMENT '委员会评审结果',
  `directorApprovalResultJson` text NULL COMMENT '院长评审结果',
  PRIMARY KEY (`id`)
) COMMENT '年度设备计划采购申请';

-- 2018/10/13 添加
CREATE TABLE `certificate` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `isReplaced` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是被替换的旧证书：0-否，1-是',
  `currentId` varchar(36) NULL COMMENT '当前证书id 证书失效后补交重审时使用',
  `no` varchar(36) NOT NULL COMMENT '编号',
  `type` varchar(128) NOT NULL COMMENT '类型',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `content` text NOT NULL COMMENT '内容 Json 存储',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态: 0-审批中 1-审批通过 2-审批失败 3-证书过期',
  `licenceDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '颁发时间',
  `expirationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `nature` int NOT NULL DEFAULT 0 COMMENT '证书性质: 0-企业证书 1-部门证书 2-个人证书',
  `companyId` varchar(36) NOT NULL COMMENT '所属公司id',
  `personId` varchar(36) NULL COMMENT '所属个人id',
  `personName` varchar(36) NULL COMMENT '所属个人名称',
  `departmentId` varchar(36) NULL COMMENT '所属部门id',
  `departmentName` varchar(36) NULL COMMENT '所属部门名称',
  `photocopy` varchar(2048) NOT NULL COMMENT '复印件图片 Json存储多个图片链接',
  `applicationId` varchar(36) NULL COMMENT '证书添加申请id',
  PRIMARY KEY (`id`)
) COMMENT '证书';

-- 2018/10/29 添加
CREATE TABLE `large_equipment_application` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `equipmentYearPlanApplicationId` varchar(36) NULL COMMENT '年度设备计划采购申请内容id',
  `applicationFile` text NULL COMMENT '大型医疗设备装备申请文件',
  `replyFile` text NULL COMMENT '相关部门批复文件',
  PRIMARY KEY (`id`)
) COMMENT '大型医疗设备装备申请(年度设备计划采购申请 如为大型医疗设备 审批后 创建该申请)';

-- 2019/01/25 添加
CREATE TABLE `contract` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `companyName` varchar(64) NOT NULL COMMENT '公司名称',
  `departmentId` varchar(36) NOT NULL COMMENT '部门id',
  `departmentName` varchar(64) NOT NULL COMMENT '部门部门',
  `creatorId` varchar(36) NOT NULL COMMENT '创建人id',
  `creatorName` varchar(36) NOT NULL COMMENT '创建人名称',
  `targetId` varchar(36) NULL COMMENT '合同目标id',
  `type` int NOT NULL COMMENT '合同类型',
  `outlook` varchar(36) NOT NULL COMMENT '合同简述',
  `no` varchar(36) NOT NULL COMMENT '合同编号',
  `firstPart` varchar(128) NOT NULL COMMENT '合同甲方',
  `secondPart` varchar(128) NOT NULL COMMENT '合同乙方',
  `effectiveAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效日期',
  `expiredAt` timestamp NULL COMMENT '过期日期',
  `photocopy` text NOT NULL COMMENT '合同复印件',
  `contentJson` text NULL COMMENT '合同详细内容JSON存储',
  `remark` varchar(512) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) COMMENT '合同';

