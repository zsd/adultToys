create table plat_attach_relation
(
  id varchar(32) not null comment '主键ID',
  attach_id varchar(32) not null comment '附件ID',
  object_type int null comment '业务对象类型',
  object_id varchar(32) not null comment '业务对象ID',
  primary key (id, attach_id, object_id)
)
;

create table plat_attachment
(
  id varchar(32) not null comment '主键'
    primary key,
  old_name varchar(200) null comment '原附件名',
  new_name varchar(200) null comment '新附件名',
  suffix varchar(20) null comment '附件后缀, doc|docx|xsl|xslx 等',
  type varchar(20) null comment '附件类型, word|excel|pdf 等',
  path varchar(400) not null comment '附件存储路径',
  file_size int null comment '附件大小, 单位KB',
  user_id varchar(32) null comment '创建者用户ID',
  user_name varchar(100) null comment '创建者用户名称',
  create_time datetime null comment '创建时间',
  update_time datetime null comment '更新时间'
)
;