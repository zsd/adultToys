create table plat_user
(
  id varchar(32) not null comment '主键'
    primary key,
  login_name varchar(255) null comment '用户名',
  password varchar(255) null comment '密码',
  name varchar(255) null comment '姓名',
  phone varchar(20) null comment '手机号码',
  email varchar(255) null comment '邮箱',
  photo varchar(1024) null comment '头像图片地址',
  birth_day date null,
  reward_money int null comment '奖励金额',
  reward_point int null comment '奖励积分',
  wechat_id varchar(255) null comment '微信ID',
  wechat_name varchar(255) null comment '微信用户名',
  weibo_id varchar(255) null comment '微博ID',
  weibo_name varchar(255) null comment '微博用户名',
  is_freeze int null,
  freeze_time datetime null,
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间'
)
  comment '用户表'
;
