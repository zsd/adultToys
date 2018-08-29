create table plat_dic
(
  id varchar(32) not null comment '主键'
    primary key,
  item varchar(50) not null comment '项目,如性别,年级等',
  name varchar(50) not null comment '字典值,如男,女等',
  order_num int not null comment '排序号',
  description varchar(200) null comment '描述',
  parent_id varchar(200) null comment '父级字典ID'
)
;