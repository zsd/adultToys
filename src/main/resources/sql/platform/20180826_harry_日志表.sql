create table plat_log
(
  id varchar(32) not null comment '日志ID'
    primary key,
  type varchar(50) not null comment '类型,业务日志类型 1：添加 2：删除 3：修改 4：查询 5：查看，6:详细信息（可以自定义更多操作类型，通用操作依次添加，与业务模块相关的添加业务模块序号前缀）',
  description varchar(200) null comment '日志描述',
  ip varchar(200) null comment '访问IP',
  client_type varchar(200) null comment '客户端类型',
  client_version varchar(200) null comment '客户端版本',
  module_id varchar(32) null comment '模块ID',
  module_name varchar(200) null comment '模块名称',
  obj_id varchar(32) null comment '对象ID',
  obj_name varchar(200) null comment '对象名称',
  obj_type_id varchar(32) null comment '对象类型ID',
  obj_type_name varchar(200) null comment '对象类型名称',
  latitude varchar(200) null comment '维度',
  longitude varchar(200) null comment '经度',
  user_id varchar(32) null comment '用户ID',
  user_name varchar(200) null comment '用户姓名',
  create_time datetime null comment '创建时间'
)
;