CREATE TABLE `user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `type` int NOT NULL DEFAULT 0 COMMENT '账户类型: 0-人 1-设备',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `email` varchar(255) NULL COMMENT '邮箱',
  `password` varchar(255) NULL COMMENT '密码',
  `telephone` varchar(11) NULL COMMENT '手机',
  `sex` int NULL DEFAULT 0 COMMENT '性别：0-未知，1-男，2-女',
  `birthday` timestamp NULL COMMENT '生日',
  `state` int NOT NULL DEFAULT 1 COMMENT '状态：0-离职 1-正常，2-请假，3-外出...',
  `headImageUrl` text NULL COMMENT '头像图片URL',
  `companyId` varchar(36) NULL COMMENT '公司id',
  `departmentId` varchar(36) NULL COMMENT '默认部门id',
  `lockStatus` int NOT NULL DEFAULT 0 COMMENT '锁定状态：0-未锁定，1-已锁定',
  `lockCount` int NOT NULL DEFAULT 0 COMMENT '锁定计数',
  `lockedAt` timestamp NULL COMMENT '锁定计时',
  `authCode` varchar(16) NULL COMMENT '验证码',
  `authCodeLastSendAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '验证码最近一次发送时间',
  `wechatStatus` int NOT NULL DEFAULT 0  COMMENT '微信状态 0-未关注 1-已关注 2-已绑定',
  `configJson` text NULL COMMENT '参数配置项 key-value 键值对',
  PRIMARY KEY (`id`)
) COMMENT '用户';


-- TODO:后期考虑删除 addressId 字段, 放入区域中
CREATE TABLE `company` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `parentId` varchar(36) NULL COMMENT '父节点主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  `addressId` varchar(36) NULL COMMENT '地址id',
  `phone` varchar(16) NULL COMMENT '电话',
  `logo` text NULL COMMENT '商标LOGO图片的URL',
  `creatorId` varchar(36) NOT NULL COMMENT '创建者id',
  `adminId` varchar(36) NULL COMMENT '管理员id',
  `type` int NOT NULL COMMENT '类型: 0-医院 1-供应 2-系统',
  `tags` varchar(128) NULL COMMENT '标签: 可有多个标签,根据标签来确认功能',
  `state` int NOT NULL DEFAULT 0 COMMENT '状态: 0-正常',
  `businessLicenseId` varchar(36) NULL COMMENT '营业执照id',
  `configJson` text NULL COMMENT '参数配置项 key-value 键值对',
  PRIMARY KEY (`id`)
) COMMENT '公司';

-- TODO: 暂时不进行处理
CREATE TABLE `area` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `addressId` varchar(36) NULL COMMENT '地址id',
  `photos` text NULL COMMENT '区域照片',
  PRIMARY KEY (`id`)
) COMMENT '区域';

CREATE TABLE `department` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `parentId` varchar(36) NULL COMMENT '父节点主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  `creatorId` varchar(36) NOT NULL COMMENT '创建者id',
  `adminId` varchar(36) NULL COMMENT '管理员id',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `areaId` varchar(36) NULL COMMENT '所在区域id',
  `buildingId` varchar(36) NULL COMMENT '所在建筑id',
  `floorId` varchar(36) NULL COMMENT '所在楼层id',
  `tags` varchar(128) NULL COMMENT '标签: 可有多个标签 JSON存储: []',
  `state` int NOT NULL DEFAULT 0 COMMENT '状态: 0-正常',
  PRIMARY KEY (`id`)
) COMMENT '部门';

CREATE TABLE `role` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  `creatorId` varchar(36) NOT NULL COMMENT '创建者id',
  `type` int NOT NULL COMMENT '类型',
  `code` varchar(64) NOT NULL COMMENT '编码',
  PRIMARY KEY (`id`)
) COMMENT '角色';

CREATE TABLE `permission` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  `type` int NOT NULL COMMENT '类型',
  `code` varchar(128) NOT NULL COMMENT '编码',
  `service` varchar(36) NOT NULL COMMENT '所属服务',
  PRIMARY KEY (`id`)
) COMMENT '权限';

CREATE TABLE `link_code` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `number` BIGINT NOT NULL unique COMMENT '编号: 获取当前数据库数据数量, 设置此值',
  `type` int NOT NULL COMMENT '类型: 0-关联ID 1-跳转链接',
  `servicePrefix` varchar(2) NOT NULL default 'FF' COMMENT '服务类型前缀:1,2 位',
  `persistantPrefix` varchar(2) NOT NULL default 'FF' COMMENT '实体类型前缀:3,4 位',
  `serialNumber` varchar(16) NOT NULL unique COMMENT '序号:[servicePrefix:2][persistantPrefix:2][number:12(十六进制)]',
  `isBinding` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否绑定：0-未绑定 1-已绑定',
  `bindingAt` timestamp NULL COMMENT '绑定时间',
  `bindingId` varchar(36) NULL COMMENT '绑定id(类型为ID时可用)',
  `bindingPayload` text NULL COMMENT '绑定载荷(链接/绑定id时附带参数)',
  `companyId` varchar(36) NULL COMMENT '条码所属公司Id',
  PRIMARY KEY (`id`)
) COMMENT '关联编码(可用于短链/一维码/二位码)';

CREATE TABLE `re_role_permission` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `roleId` varchar(36) NOT NULL COMMENT '角色id',
  `permissionId` varchar(36) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) COMMENT '角色权限关联';

CREATE TABLE `re_user_role` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `userId` varchar(36) NOT NULL COMMENT '用户id',
  `roleId` varchar(36) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) COMMENT '用户角色关联';

CREATE TABLE `tag` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `companyId` varchar(36) NOT NULL COMMENT '所属公司id',
  `itemType` varchar(36) NOT NULL COMMENT '数据类型 详情见 PersistantConst',
  `name` varchar(36) NOT NULL COMMENT '标签名称',
  `description` varchar(256) NOT NULL COMMENT '标签说明',
  PRIMARY KEY (`id`)
) COMMENT '标签';

CREATE TABLE `re_tag_item` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `tagId` varchar(36) NOT NULL COMMENT '标签id',
  `itemId` varchar(36) NOT NULL COMMENT '数据项id',
  PRIMARY KEY (`id`)
) COMMENT '标签与数据关联';


CREATE TABLE `address` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `country` varchar(4) NOT NULL DEFAULT 'CN' COMMENT '国家编号',
  `cityIndex1` varchar(32) NULL COMMENT '地址索引一级(省)',
  `cityIndex2` varchar(32) NULL COMMENT '地址索引二级(市)',
  `cityIndex3` varchar(32) NULL COMMENT '地址索引三级(区)',
  `cityIndex4` varchar(32) NULL COMMENT '地址索引四级(街)',
  `cityIndex5` varchar(32) NULL COMMENT '地址索引五级(备)',
  `addressPrefix` varchar(128) NULL COMMENT '地址前缀',
  `addressDetail` varchar(128) NULL COMMENT '详细地址',
  `postalCode` varchar(16) NULL COMMENT '邮政编码',
  `longitude` decimal(12,8) NULL COMMENT '经度',
  `latitude` decimal(12,8) NULL COMMENT '纬度',
  `remark` varchar(128) NULL COMMENT '备注内容',
  PRIMARY KEY (`id`)
) COMMENT '地址信息';

CREATE TABLE `re_user_department` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `creatorId` varchar(36) NOT NULL COMMENT '创建者id',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `departmentId` varchar(36) NOT NULL COMMENT '部门id',
  `userId` varchar(36) NOT NULL COMMENT '用户id',
  `entryRemark` varchar(256) NULL COMMENT '入职备注',
  `leaveRemark` varchar(256) NULL COMMENT '离职备注',
  PRIMARY KEY (`id`)
) COMMENT '用户部门入离职记录';

CREATE TABLE `position` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `type` int NOT NULL COMMENT '类型: 0-系统定义的公司职位 1-系统定义的部门职位 2-用户定义的公司职位 3-用户定义的部门职位',
  `visitorCompanyType` int NOT NULL default 0 COMMENT '访问者公司类型(主要对系统定义的特殊职位进行区分): 0-医院 1-供应商 2-平台',
  `companyId` varchar(36) NULL COMMENT '公司id',
  `departmentId` varchar(36) NULL COMMENT '部门id',
  `name` varchar(36) NOT NULL COMMENT '职位名称',
  `code` varchar(36) NULL COMMENT '职位编码, 仅系统创建的职位带此信息',
  `description` text NULL COMMENT '职位描述',
  `color` varchar(16) NOT NULL DEFAULT '#40a9ff' COMMENT '颜色',
  `minCount` int NOT NULL DEFAULT 0 COMMENT '公司该职位人员数量最小限制: 0-不限制',
  `maxCount` int NOT NULL DEFAULT 0 COMMENT '公司该职位人员数量最大限制: 0-不限制',
  PRIMARY KEY (`id`)
) COMMENT '部门职务';

CREATE TABLE `re_user_position` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `creatorId` varchar(36) NOT NULL COMMENT '创建者id',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `departmentId` varchar(36) NULL COMMENT '部门id',
  `positionId` varchar(36) NOT NULL COMMENT '职务id',
  `userId` varchar(36) NOT NULL COMMENT '用户id',
  `allotAt` timestamp NULL COMMENT '分配时间',
  `takeBackAt` timestamp NULL COMMENT '撤回时间',
  PRIMARY KEY (`id`)
) COMMENT '部门职务分配记录';

CREATE TABLE `building` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `creatorId` varchar(36) NOT NULL COMMENT '创建者id',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) COMMENT '建筑';

CREATE TABLE `floor` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `creatorId` varchar(36) NOT NULL COMMENT '创建者id',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `buildingId` varchar(36) NOT NULL COMMENT '建筑id',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `description` varchar(128) NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) COMMENT '楼层';

CREATE TABLE `room` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `creatorId` varchar(36) NOT NULL COMMENT '创建者id',
  `companyId` varchar(36) NOT NULL COMMENT '公司id',
  `buildingId` varchar(36) NOT NULL COMMENT '建筑id',
  `floorId` varchar(36) NOT NULL COMMENT '楼层id',
  `name` varchar(36) NOT NULL COMMENT '名称(门牌号)',
  `description` varchar(128) NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) COMMENT '房间';

-- 2018/11/23 添加
CREATE TABLE `supplier_register` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `companyName` varchar(36) NOT NULL COMMENT '公司名称',
  `unifiedSocialCreditCode` varchar(36) NOT NULL COMMENT '统一社会信用代码',
  `supplierType` varchar(128) NOT NULL COMMENT '供应商类型',
  `supplierLevel` int NOT NULL COMMENT '供应商级别',
  `headOfSalesName` varchar(36) NOT NULL COMMENT '销售负责人姓名',
  `headOfSalesIdCard` varchar(36) NOT NULL COMMENT '负责人身份证号',
  `headOfSalesPhoneNumber` varchar(11) NOT NULL COMMENT '销售负责人手机',
  `headOfSalesEmail` varchar(256) NOT NULL COMMENT '销售负责人邮件',
  `headOfSalesPassword` varchar(256) NOT NULL COMMENT '销售负责人密码',
  `idCardPhotocopy` text NOT NULL COMMENT '销售负责人的身份证加盖企业公章复印件',
  `activeCode` varchar(128) NULL COMMENT '账户邮件激活码',
  `remark` varchar(512) NULL COMMENT '备注',
  `approverId` varchar(36) NULL COMMENT '审批人id',
  `result` varchar(512) NULL COMMENT '结果',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态: 0-待审批 1-审批通过 2-审批失败',
  PRIMARY KEY (`id`)
) COMMENT '供应商注册';

CREATE TABLE `wechat_app_user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `userId` varchar(36) NULL COMMENT '用户id',
  `openId` varchar(128) NOT NULL COMMENT 'openId',
  `sessionKey` varchar(128) NOT NULL COMMENT 'sessionKey',
  `sessionKeyCreatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'sessionKey创建时间',
  `nickName` varchar(50) NULL COMMENT '昵称',
  `gender` int(11) NULL COMMENT '性别',
  `language` varchar(10) NULL COMMENT '语言',
  `city` varchar(20) NULL COMMENT '城市',
  `province` varchar(20) NULL COMMENT '省份',
  `country` varchar(20) NULL COMMENT '国家',
  `avatarUrl` varchar(255) NULL COMMENT '头像',
  `unionId` varchar(128) NULL DEFAULT NULL COMMENT 'UNION-ID',
  `remark` varchar(40) NULL DEFAULT NULL COMMENT '备注',
  `groupId` int(11) NULL DEFAULT NULL COMMENT '组',
  PRIMARY KEY (`id`)
) COMMENT '微信小程序用户';

CREATE TABLE `re_route_role` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `clientType` int NOT NULL default 1 COMMENT '客户端类型: 1-web',
  `path` varchar(256) NOT NULL COMMENT '前端路由地址',
  `role` varchar(64) NOT NULL COMMENT '角色code',
  PRIMARY KEY (`id`)
) COMMENT '前端路由与角色编码的关联';

CREATE TABLE `re_route_permission` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `clientType` int NOT NULL default 1 COMMENT '客户端类型: 1-web',
  `path` varchar(256) NOT NULL COMMENT '前端路由地址',
  `permission` varchar(128) NOT NULL COMMENT '权限code',
  PRIMARY KEY (`id`)
) COMMENT '前端路由与权限编码的关联';

CREATE TABLE `re_route_help` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isEnable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用：0-不可用，1-可用',
  `clientType` int NOT NULL default 1 COMMENT '客户端类型: 1-web',
  `path` varchar(256) NOT NULL COMMENT '前端路由地址',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `description` varchar(512) NOT NULL COMMENT '描述',
  `helpInfoId` varchar(36) NOT NULL COMMENT '帮助信息富文本id',
  PRIMARY KEY (`id`)
) COMMENT '前端路由的帮助信息';


ALTER TABLE `re_user_role` ADD CONSTRAINT `fk_re_user_role_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`);
ALTER TABLE `re_user_role` ADD CONSTRAINT `fk_re_user_role_role` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`);
ALTER TABLE `re_role_permission` ADD CONSTRAINT `fk_re_role_permission_role` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`);
ALTER TABLE `re_role_permission` ADD CONSTRAINT `fk_re_role_permission_permission` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`);
ALTER TABLE `role` ADD CONSTRAINT `fk_role_user` FOREIGN KEY (`creatorId`) REFERENCES `user` (`id`);
ALTER TABLE `user` ADD CONSTRAINT `fk_user_company` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`);
ALTER TABLE `user` ADD CONSTRAINT `fk_user_department` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`);
ALTER TABLE `department` ADD CONSTRAINT `fk_department_company` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`);
ALTER TABLE `re_user_department` ADD CONSTRAINT `fk_re_user_department_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`);
ALTER TABLE `re_user_department` ADD CONSTRAINT `fk_re_user_department_department` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`);




