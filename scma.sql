CREATE TABLE `T_USER` (
`userid` int(12) NOT NULL AUTO_INCREMENT COMMENT '用户id',
`username` varchar(50) NOT NULL COMMENT '用户名',
`password` varchar(200) NOT NULL COMMENT '密码',
`name` varchar(50) NULL COMMENT '姓名',
`sex` varchar(2) NULL COMMENT '性别',
`tel` varchar(20) NULL COMMENT '电话',
`addr` varchar(100) NULL COMMENT '地址',
`image_url` varchar(1000) NULL COMMENT '头像上传url',
`type` int(2) NULL COMMENT '类型：1-普通用户，2-子账号用户',
`build_date` datetime NULL COMMENT '创建时间',
`build_userid` int(12) NULL COMMENT '创建该用户的用户的userid',
`build_partnershipid` int(12) NULL COMMENT '创建该用户的合作企业id',
`modify_date` datetime NULL COMMENT '修改时间',
`modify_userid` int(12) NULL COMMENT '修改者的userid',
`role_id` int(12) NULL COMMENT '角色id',
`status` int(2) NULL COMMENT '用户状态：0-失效，1-有效',
`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',
PRIMARY KEY (`userid`) ,
UNIQUE INDEX `INDEX_USERNAME` (`username`)
);

CREATE TABLE `T_MEMBER` (
`member_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '会员id',
`member_name` varchar(100) NULL COMMENT '会员名称',
`member_type_id` int(12) NULL COMMENT '会员类型serialno',
`corporation` varchar(50) NULL COMMENT '法人',
`reg_address` varchar(100) NULL COMMENT '注册地址',
`steupdate` date NULL COMMENT '创建日期',
`creditno` varchar(30) NULL COMMENT '统一社会信用代码',
`regstock` varchar(50) NULL COMMENT '注册资本',
`contacts` varchar(50) NULL COMMENT '联系人',
`contact_title` varchar(50) NULL COMMENT '联系人职务',
`contact_tel` varchar(30) NULL COMMENT '联系人电话',
`contact_addr` varchar(100) NULL COMMENT '联系人地址',
`province_code` varchar(30) NULL COMMENT '省区域编码',
`city_code` varchar(30) NULL COMMENT '市区域编码',
`introduction` varchar(2000) NULL COMMENT '简介',
`build_date` datetime NULL COMMENT '创建时间',
`build_userid` int(12) NULL COMMENT '创建人userid',
`modify_date` datetime NULL COMMENT '修改时间',
`modify_userid` int(12) NULL COMMENT '修改人userid',
`owner_userid` int(12) NULL COMMENT '所属用户id',
`owner_username` varchar(50) NULL COMMENT '所属用户名称',
`owner_partnership_id` int(12) NULL COMMENT '所属合作企业id',
`owner_partnership_name` varchar(50) NULL COMMENT '所属合作企业名称',
`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',
`status` int(2) NULL COMMENT '状态：0-已释放为公共资源，1-正常，2-已释放到公司会员数据库',
PRIMARY KEY (`member_id`) ,
UNIQUE INDEX `INDEX_MEMBERNAME_UNIQUE` (`member_name`)
);

CREATE TABLE `T_PARTNERSHIP_OLD` (
`partnership_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '合作企业id',
`partnership_name` varchar(50) NULL COMMENT '合作企业名称',
`contacts` varchar(50) NULL COMMENT '联系人',
`contact_tel` varchar(30) NULL COMMENT '联系电话',
`contact_addr` varchar(100) NULL COMMENT '联系地址',
`operation_id` int(12) NULL COMMENT '当前参与的活动id',
`manager_userid` int(12) NULL COMMENT '管理员的userid',
`build_date` datetime NULL COMMENT '创建时间',
`build_userid` int(12) NULL COMMENT '创建用户userid',
`modify_date` datetime NULL COMMENT '修改时间',
`modify_userid` int NULL COMMENT '修改用户userid',
`deleteflag` int(2) NULL COMMENT '删除编辑：0-删除，1-正常',
PRIMARY KEY (`partnership_id`) 
);

CREATE TABLE `T_SUPPLIER` (`supplier_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '供应商id',`supplier_name` varchar(50) NULL COMMENT '供应商名称',`contacts` varchar(50) NULL COMMENT '联系人',`contact_tel` varchar(30) NULL COMMENT '联系电话',`contact_addr` varchar(100) NULL COMMENT '联系地址',`introduction` varchar(2000) NULL COMMENT '简介',`member_type_id` int(2) NULL COMMENT '企业类型，字典同会员类型',`partnership_type` int(2) NULL COMMENT '企业性质，字典同合作企业企业性质',`wechat_account` varchar(100) NULL COMMENT '企业微信号',`video_account` varchar(100) NULL COMMENT '企业视频号',`email` varchar(100) NULL COMMENT '企业邮箱',`director_name` varchar(50) NULL COMMENT '负责人姓名',`director_position` varchar(50) NULL COMMENT '负责人职务',`logo_url` varchar(1000) NULL COMMENT '企业logo url',`contacts_position` varchar(50) NULL COMMENT '联系人职务',`join_logistics_fair` int(2) NULL COMMENT '是否参加过物博会，0-否，1-是',`service_provider` varchar(50) NULL COMMENT '服务对接人',`director_tel` varchar(50) NULL COMMENT '负责人手机号',`cooperation_cases` varchar(2000) NULL COMMENT '合作案例',`build_userid` int(12) NULL COMMENT '创建用户userid',`build_date` datetime NULL COMMENT '创建时间',`modify_userid` int(12) NULL COMMENT '修改用户userid',`modify_date` datetime NULL COMMENT '修改时间',`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',PRIMARY KEY (`supplier_id`) );

CREATE TABLE `T_OPERATION` (
`operation_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '活动id',
`operation_name` varchar(50) NULL COMMENT '活动名称',
`director` varchar(50) NULL COMMENT '活动负责人',
`contact_tel` varchar(30) NULL COMMENT '联系电话',
`address` varchar(100) NULL COMMENT '活动地址',
`start_date` datetime NULL COMMENT '活动开始时间',
`end_date` datetime NULL COMMENT '活动结束时间',
`introduction` text NULL COMMENT '简介',
`status` int(2) NULL COMMENT '活动状态：0-未开始，1-正常，2-已结束',
`build_date` datetime NULL COMMENT '创建时间',
`build_userid` int(12) NULL COMMENT '创建者userid',
`modify_date` datetime NULL COMMENT '修改时间',
`modify_userid` int(12) NULL COMMENT '修改者userid',
`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',
PRIMARY KEY (`operation_id`) 
);

CREATE TABLE `T_ROLE` (
`role_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '角色id',
`role_name` varchar(50) NULL COMMENT '角色名称',
`introduction` varchar(2000) NULL COMMENT '角色介绍',
`build_date` datetime NULL COMMENT '创建时间',
`build_userid` int(12) NULL COMMENT '创建者userid',
`modify_date` datetime NULL COMMENT '修改时间',
`modify_userid` int(12) NULL COMMENT '修改者userid',
`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',
PRIMARY KEY (`role_id`) 
);

CREATE TABLE `T_PERMISSION` (
`permission_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '权限id',
`menu_name` varchar(50) NULL COMMENT '菜单名称',
`menu_sort` int(2) NULL COMMENT '菜单排序，统一个menu_name请使用同一个sort，若有不同的sort，会随机取一个',
`permission_name` varchar(30) NULL COMMENT '权限名称',
`permission_sort` int(2) NULL COMMENT '权限在菜单内部的排序排序',
`introduction` varchar(2000) NULL COMMENT '权限描述',
`build_date` datetime NULL COMMENT '创建时间',
`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',
PRIMARY KEY (`permission_id`) 
);

CREATE TABLE `T_ROLE_MTM_PERMISSION` (
`serialno` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
`role_id` int(12) NULL COMMENT '角色id',
`permission_id` int(12) NULL COMMENT '权限id',
`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',
PRIMARY KEY (`serialno`) 
);

CREATE TABLE `T_SYSCONFIG` (
`serialno` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
`cust_code` varchar(50) NULL COMMENT '设置代码',
`cust_value` text NULL COMMENT '设置值',
`build_date` datetime NULL COMMENT '创建时间',
`modify_date` datetime NULL COMMENT '修改时间',
`modify_userid` int(12) NULL COMMENT '修改者userid',
`introduction` varchar(2000) NULL COMMENT '简介',
`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',
PRIMARY KEY (`serialno`) 
);

CREATE TABLE `T_ITEM` (
`item_code` varchar(30) NULL COMMENT '主体编码',
`item_name` varchar(30) NULL COMMENT '主体名称',
`parent_code` varchar(30) NULL COMMENT '父级编码',
`type` int(2) NULL COMMENT '主体类型：1-省，2-市'
);

CREATE TABLE `T_OPERATION_OTM_PARTNERSHIP` (
`serialno` int(12) NOT NULL AUTO_INCREMENT COMMENT '序号',
`operation_id` int(12) NULL COMMENT '活动id',
`partnership_id` int(12) NULL COMMENT '合作企业id',
`build_date` datetime NULL COMMENT '合作企业参加活动时间',
`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',
PRIMARY KEY (`serialno`) 
);

CREATE TABLE `T_MEMBER_TYPE` (
`member_type_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '会员类型id',
`member_type_name` varchar(50) NULL COMMENT '会员类型名称',
`introduction` varchar(2000) NULL COMMENT '简介',
`build_date` datetime NULL COMMENT '创建时间',
`build_userid` int(12) NULL COMMENT '创建者userid',
`deleteflag` int(2) NULL COMMENT '删除标记：0-删除，1-正常',
PRIMARY KEY (`member_type_id`) 
);

CREATE TABLE `T_PARTNERSHIP` (
`partnership_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '合作企业id',
`partnership_name` varchar(100) NULL COMMENT '单位名称',
`partnership_type` int(2) NULL COMMENT '企业性质，1-国企，2-民企，3-外企',
`logo_url` varchar(1000) NULL COMMENT '企业logo url',
`addr` varchar(100) NULL COMMENT '地址',
`email` varchar(100) NULL COMMENT '企业邮箱',
`web_addr` varchar(100) NULL COMMENT '官方网址',
`wechat_account` varchar(100) NULL COMMENT '微信公众号',
`video_account` varchar(100) NULL COMMENT '视频号',
`director_name` varchar(100) NULL COMMENT '负责人姓名',
`director_position` varchar(100) NULL COMMENT '负责人职务',
`director_tel` varchar(100) NULL COMMENT '负责人手机号',
`director_wechat` varchar(100) NULL COMMENT '负责人微信号',
`contact_name` varchar(100) NULL COMMENT '联系人姓名',
`contact_position` varchar(100) NULL COMMENT '联系人职务',
`contact_tel` varchar(100) NULL COMMENT '联系人手机',
`contact_wechat` varchar(100) NULL COMMENT '联系人微信号',
`amount` varchar(20) NULL COMMENT '协办金额',
`payment_time` date NULL COMMENT '进账时间',
`contract_number` varchar(50) NULL COMMENT '合同编号',
`contract_term` varchar(50) NULL COMMENT '合同期限',
`project_type` varchar(20) NULL COMMENT '合作项目，1-协办，2-广告，3-活动，多个同时存在时使用逗号隔开',
`first_join_time` datetime NULL COMMENT '首次加入协办单位时间',
`service_provider` varchar(50) NULL COMMENT '对接服务人员',
`operation_id` int(12) NULL COMMENT '当前活动id',
`operation_status` int(2) NULL COMMENT '当前绑定活动状态，0-未开始，1-正常，2-已结束',
`manager_userid` int(12) NULL COMMENT '管理员userid',
`build_date` datetime NULL COMMENT '创建时间',
`build_userid` int(12) NULL COMMENT '创建者userid',
`modify_date` datetime NULL COMMENT '修改时间',
`modify_userid` int(12) NULL COMMENT '修改者userid',
`deleteflag` int(2) NULL COMMENT '删除标值，0-删除，1-正常',
PRIMARY KEY (`partnership_id`) 
);

