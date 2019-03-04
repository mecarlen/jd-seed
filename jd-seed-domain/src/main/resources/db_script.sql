---------------------- 创建测试库demo ----------------------------------
CREATE DATABASE demo DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
create user demo identified by 'demo';
grant select,insert,update,delete on demo.* to 'demo'@'%';
flush PRIVILEGES;
------字典-城市表------
CREATE TABLE dict_city(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	zh_name VARCHAR(30) NOT NULL COMMENT '中文名称',
	en_name VARCHAR(50) COMMENT '英文名称',
	unity_code VARCHAR(30) NOT NULL COMMENT '统一编码',
	zh_full_pin VARCHAR(50) COMMENT '中文名全拼',
	zh_short_pin VARCHAR(10) COMMENT '中文名简拼',
	priority TINYINT(2) UNSIGNED NOT NULL DEFAULT 99 COMMENT '排序号',
	state TINYINT(2) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,2->停用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_dict_city PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='城市表';
-------权限-菜单------------
CREATE TABLE auth_menu(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	name VARCHAR(30) NOT NULL COMMENT '名称',
	unity_code VARCHAR(30) NOT NULL COMMENT '统一编码',
	parent_id BIGINT(10) UNSIGNED COMMENT '父菜单ID',
	sn VARCHAR(55) NOT NULL COMMENT 'SN',
	url VARCHAR(100) COMMENT 'URL',
	type TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '类型,0-根节点,1-叶子节点,2-按钮节点',
	sequence TINYINT(3) UNSIGNED NOT NULL DEFAULT 9 COMMENT '次序',
	descr VARCHAR(300) NOT NULL DEFAULT 'description' COMMENT '备注 ',
	state TINYINT(2) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,2->停用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_auth_menu PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='菜单表';
alter table auth_menu add constraint uk_auth_menu_uc unique(unity_code);
-------权限-角色------------
CREATE TABLE auth_role(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	name VARCHAR(30) NOT NULL COMMENT '名称',
	unity_code VARCHAR(30) NOT NULL COMMENT '统一编码',
	type TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '类型,0-系统,1-流程,2-业务',
	descr VARCHAR(300) NOT NULL DEFAULT 'description' COMMENT '备注 ',
	state TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,-1->弃用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_auth_role PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='角色表';
alter table auth_role add constraint uk_auth_role_uc unique(unity_code);
-------权限-授权------------
CREATE TABLE auth_permission(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	role_id BIGINT(10) UNSIGNED NOT NULL COMMENT '角色id',
	resource_id BIGINT(10) UNSIGNED NOT NULL COMMENT '资源id,资源:菜单/数据',
	res_type TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '资源类型,0-菜单,1-数据',
	operation TINYINT(2) UNSIGNED NOT NULL COMMENT '操作码或运算值',
	with_opt TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '权限是否可下放,默认-0-否',
	state TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,-1->弃用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_auth_permission PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='授权';
alter table auth_permission add constraint uk_auth_permission_rr unique(resource_id,role_id);
-------权限-用户组------------
CREATE TABLE auth_group(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	name VARCHAR(30) NOT NULL COMMENT '名称',
	unity_code VARCHAR(30) NOT NULL COMMENT '统一编码',
	descr VARCHAR(300) NOT NULL DEFAULT 'description' COMMENT '备注 ',
	state TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,-1->弃用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_auth_group PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='用户组表';
alter table auth_group add constraint uk_auth_group_uc unique(unity_code);
-------权限-用户组/角色------------
CREATE TABLE auth_group_role(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	group_id BIGINT(10) UNSIGNED NOT NULL COMMENT '用户组id',
	role_id BIGINT(10) UNSIGNED NOT NULL COMMENT '角色id',
	state TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,-1->弃用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_auth_gr PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='用户组-角色关系表';
alter table auth_group_role add constraint uk_auth_group_role_gr unique(role_id,group_id);
-------权限-用户/角色------------
CREATE TABLE auth_user_role(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	user_id BIGINT(10) UNSIGNED NOT NULL COMMENT '用户id',
	user_type TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户类型,0-员工,1-访客',
	role_id BIGINT(10) UNSIGNED NOT NULL COMMENT '角色id',
	state TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,-1->弃用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_auth_ur PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT '用户-角色关系表';
alter table auth_user_role add constraint uk_auth_user_role_ur unique(user_id,role_id);
-------权限-用户/用户组------------
CREATE TABLE auth_user_group(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	user_id BIGINT(10) UNSIGNED NOT NULL COMMENT '用户id',
	user_type TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户类型,0-员工,1-访客',
	group_id BIGINT(10) UNSIGNED NOT NULL COMMENT '用户组id',
	state TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,-1->弃用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_auth_ur PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT '用户-用户组关系表';
alter table auth_user_group add constraint uk_auth_user_group_ug unique(user_id,group_id);
-------组织结构-部门----------
CREATE TABLE org_department(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	name VARCHAR(30) NOT NULL COMMENT '名称',
	unity_code VARCHAR(30) NOT NULL COMMENT '统一编码',
	parent_id BIGINT(10) UNSIGNED COMMENT '父部门ID',
	sn VARCHAR(55) NOT NULL COMMENT 'SN',
	sequence TINYINT(3) UNSIGNED NOT NULL DEFAULT 9 COMMENT '次序',
	descr VARCHAR(300) NOT NULL DEFAULT 'description' COMMENT '备注 ',
	state TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,-1->弃用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_org_department PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='部门表';
-------组织结构-岗位----------
CREATE TABLE org_post(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	name VARCHAR(30) NOT NULL COMMENT '名称',
	unity_code VARCHAR(30) NOT NULL COMMENT '统一编码',
	
	descr VARCHAR(300) NOT NULL DEFAULT 'description' COMMENT '备注 ',
	state TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,-1->弃用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_org_department PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='岗位表';
-------组织结构-员工----------
CREATE TABLE org_employee(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	unity_code VARCHAR(30) NOT NULL COMMENT '工号',
	sn VARCHAR(255) NOT NULL COMMENT 'SN',
	full_name VARCHAR(50) NOT NULL COMMENT '姓名',
	erp_account VARCHAR(30) NOT NULL COMMENT 'ERP账号',
	password VARCHAR(128) NOT NULL COMMENT '密码',
	email VARCHAR(50) NOT NULL COMMENT '邮箱',
	mobile VARCHAR(11) NOT NULL COMMENT '手机号',
	phone VARCHAR(11) COMMENT '座机',
	department_id BIGINT(10) UNSIGNED NOT NULL COMMENT '职能部门ID',
	post_id BIGINT(10) UNSIGNED NOT NULL COMMENT '主岗位ID',
	descr VARCHAR(300) NOT NULL DEFAULT 'description' COMMENT '备注 ',
	state TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,-1->弃用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT pk_org_department PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='员工表';

