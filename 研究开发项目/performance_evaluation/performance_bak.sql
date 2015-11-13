﻿-- 建表 外键未添加 auto_increment也未添加
-- 
-- staff表
drop table if exists staff;
create table staff (
	staff_id 		int(11) not null auto_increment,
	staff_name		varchar(20) not null,
	staff_no		int(11) not null,  -- gonghao
	staff_sex		varchar(10),
	staff_password	varchar(20) not null,
	department_id 	int(11) not null,
	post_id			int(11) not null,
	role_id  		int(11) not null,
	class_id		text, 				-- string ;;
	primary key (staff_id)
) default charset=utf8;


drop table if exists staff_class;
create table staff_class(
	class_id char(9) not null,
	class_name varchar(200) not null,
	class_comment varchar(200),
	primary key(class_id)
)default charset=utf8;

-- role表
drop table if exists role;
create table role(
	role_id	int(11) not null auto_increment,
	role_name	varchar(20) not null,
	role_description varchar(200),
	primary key(role_id)
) default charset=utf8;

-- 页面分类表
drop table if exists funcpage_category;
create table funcpage_category(
	funcpage_category_id int(11) not null auto_increment,
	funcpage_category_name varchar(200) not null,
	primary key(funcpage_category_id)
)default charset=utf8;

-- 页面表　通过角色来配置
drop table if exists funcpage;
create table funcpage(
	funcpage_id int(11) not null auto_increment,
	funcpage_name varchar(20) not null,
	funcpage_action text not null,
	funcpage_category_id int(11),
	primary key (funcpage_id)
)default charset=utf8;


-- role funcpage  relationship
drop table if exists role_funcpage_relation;
create table role_funcpage_relation(
	role_funcpage_relation_id int(11) not null auto_increment,
	role_id int(11) not null,
	funcpage_id int(11) not null,
	primary key (role_funcpage_relation_id)
)default charset=utf8;


-- post表
drop table if exists post;
create table post(
	post_id	int(11) not null auto_increment,
	post_name	varchar(20) not null,
	primary key(post_id)
) default charset=utf8;



-- department表
drop table if exists department;
create table department(
	department_id			int(11) not null auto_increment,
	department_name			varchar(20) not null,
	higher_department_id 	int(11),
	nature int(3) not null,
	primary key(department_id)
) default charset=utf8;

-- 条目类别表
drop table if exists score_category;
create table score_category(
	score_category_id int(11) not null auto_increment,
	score_category_name varchar(20),
	primary key(score_category_id)
)default charset=utf8;

-- 部门条目表
drop table if exists department_score_item;
create table department_score_item(
	score_item_id int(11) not null auto_increment,
	score_item_name varchar(200),
	score_description varchar(200),	
	score_value	float,
	score_reference varchar(200),
	score_calculation_method varchar(200),
	department_id int(11),
	score_category_id int(11),
	score_creation_date date,
	score_in_use	int(1),	
	primary key(score_item_id)
)default charset=utf8;

-- 部门条目对照表
drop table if exists department_score_relation;
create table department_score_relation(
	department_score_relation_id int(11) not null auto_increment,
	score_item_id int(11),	
	department_id int(11),
	primary key(department_score_relation_id)
)default charset=utf8;
-- test datas

-- 部门打分记录表
drop table if exists department_score_record;
create table department_score_record(
	department_score_record_id int(11) not null auto_increment,
	department_from_id int(11),
	department_to_id int(11),
	score_item_id int(11),
	score float,
	score_comment text,
	score_date date,
	which_year int(11),
	which_month int(11),
	modify_times int(11),
	primary key(department_score_record_id)
)default charset=utf8;

-- 部门得分表
drop table if exists department_score;
create table department_score(
	department_total_score_id int(11) not null auto_increment,
	department_id int(11),
	total_score float,
	score_date date,
	which_year int(11),
	which_month int(11),
	annotation text,
	primary key(department_total_score_id)
)default charset=utf8;

-- 个人评分条目表
drop table if exists staff_score_item;
create table staff_score_item(
	score_item_id int(11) not null auto_increment,
	score_item_name varchar(20),
	score_weight float,
	score_description varchar(200),
	score_standard1 varchar(200),
	score_standard2 varchar(200),
	score_standard3 varchar(200),
	score_standard4 varchar(200),
	score_standard5 varchar(200),
	score_category_id int(11) not null,
	score_creation_date date,
	score_in_use int(1),
	primary key(score_item_id)
)default charset=utf8;

-- 个人条目对照表
drop table if exists staff_score_relation;
create table staff_score_relation(
	staff_score_relation_id int(11) not null auto_increment,
	score_item_id int(11) not null ,
	department_id int(11) not null ,
	post_id int(11) not null ,
	primary key(staff_score_relation_id)
)default charset=utf8;

-- 员工打分记录表
drop table if exists staff_score_record;
create table staff_score_record(
	staff_score_record_id int(11) not null auto_increment,
	scored_from_staff_id int(11),
	scored_to_staff_id int(11),
	score_item_id int(11),
	score float,
	score_date date,
	which_year int(11),
	which_month int(11),
	modify_times int(11),
	primary key(staff_score_record_id)
)default charset=utf8;

-- 个人得分表
drop table if exists staff_score;
create table staff_score(
	staff_score_id int(11) not null auto_increment,
	staff_id int(11),
	staff_score float,
	score_date date,
	which_year int(11),
	which_month int(11),
	annotation text,
	primary key(staff_score_id)
)default charset=utf8;


/*
-- 部门得分撤销次数表
drop table if exists department_score_Revocation;
create table department_score_Revocation(
	id int(11) not null auto_increment,
	department_from_id int(11) not null,
	department_to_id int(11) not null,
	score_category varchar(20) not null ,
	remaining_times int(11) not null,
	primary key(id)
)default charset=utf8;

-- 员工得分撤销次数表
drop table if exists staff_score_Revocation;
create table staff_score_Revocation(
	id int(11) not null auto_increment,
	staff_from_id int(11) not null,
	staff_to_id int(11) not null,
	remaining_times int(11) not null,
	primary key(id)
)default charset=utf8;

*/

-- 参数表
drop table if exists configuration_data;
create table configuration_data(
	configuration_data_id int not null auto_increment,
	configuration_data_key varchar(200),
	configuration_data_value varchar(200),
	primary key(configuration_data_id)
	)default charset=utf8;

-- 任务进度表
drop table if exists task_progress;
create table task_progress(
	task_progress_id int not null auto_increment,
	task_progress float,
	creation_date date,
	which_year int(11),
	which_month int(11),
	primary key(task_progress_id)
)default charset=utf8;

-- 部门阶段重点工作表
drop table if exists department_key_work;
create table department_key_work(
	key_work_id int not null auto_increment,
	department_id int not null,
	key_work_content text not null,
	start_year int not null,
	start_month int not null,
	end_year int not null,
	end_month int not null,
	primary key(key_work_id)
)default charset=utf8;

-- 工资表
drop table if exists salary;
create table salary(
	salary_id int not null auto_increment,
	staff_id int not null,
	salary float not null,
	primary key(salary_id)
)default charset=utf8;


-- 消息表
drop table if exists message;
create table message(
	message_id int not null auto_increment,
	title varchar(100) not null,
	content longtext not null,
	simple_content text,
	message_type int,
	sender int,
	receiver text,
	received text,
	attachment text,
	message_date DATETIME,
	primary key(message_id)
)default charset=utf8;

-- 文件关联
drop table if exists upload_file;
create table upload_file(
	file_id varchar(50),
	file_name varchar(100) not null,
	file_type varchar(100) not null,
	file_size int not null,
	creator_id int(11) not null,
	upload_time DATETIME,
	primary key(file_id)
)default charset=utf8;

