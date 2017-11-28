---------------------- 创建测试库demo ----------------------------------
CREATE DATABASE demo DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
------城市表------
CREATE TABLE dict_city(
	id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	zh_name VARCHAR(30) NOT NULL COMMENT '中文名称',
	en_name VARCHAR(50) COMMENT '英文名称',
	unity_code VARCHAR(30) NOT NULL COMMENT '统一编码',
	zh_full_pin VARCHAR(50) COMMENT '中文名全拼',
	zh_short_pin VARCHAR(10) COMMENT '中文名简拼',
	priority TINYINT(2) NOT NULL DEFAULT 99 COMMENT '排序号',
	state TINYINT(2) NOT NULL DEFAULT 1 COMMENT '状态,0->停用,1->在用,2->停用',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	CONSTRAINT dict_city_pk PRIMARY KEY(id)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='城市表';
