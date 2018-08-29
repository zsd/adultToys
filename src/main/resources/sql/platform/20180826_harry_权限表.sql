create table plat_function
(
  id varchar(32) not null comment '主键'
    primary key,
  name varchar(100) not null comment '名称',
  code varchar(100) not null comment '编号',
  src varchar(100) not null comment '路径',
  type varchar(2) not null comment '类型：1：菜单，2：按钮',
  description varchar(200) null comment '描述',
  is_show varchar(2) not null comment '是否显示,1：显示，0不显示',
  order_num int not null comment '排序号',
  parent_id varchar(32) not null comment '父节点',
  icon varchar(100) not null comment '图片url',
  create_time datetime null comment '创建时间',
  update_time datetime null comment '更新时间'
)
;

create table plat_role
(
  id varchar(32) not null comment '角色ID'
    primary key,
  name varchar(50) not null comment '角色名称',
  description varchar(32) null comment '描述',
  create_time datetime null comment '创建时间',
  update_time datetime null comment '更新时间'
)
;

create table plat_role_function
(
  role_id varchar(32) not null comment '角色ID',
  function_id varchar(32) default '' not null comment '权限ID',
  primary key (role_id, function_id)
)
;

create table plat_user_role
(
  user_id varchar(32) not null comment '机构、岗位ID',
  role_id varchar(32) default '' not null comment '角色ID',
  create_time datetime null comment '创建时间',
  update_time datetime null comment '更新时间',
  primary key (user_id, role_id)
)
;