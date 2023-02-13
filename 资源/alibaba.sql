# database

create database alibaba character set utf8mb4;
use alibaba;

# table

create table if not exists alibaba.user
(
    id          int(11) auto_increment comment '主键',
    username    varchar(128) not null default '' comment '账号',
    password    varchar(128) not null default '' comment '密码',
    phone       char(11)     not null default '' comment '电话',
    create_time datetime     not null default now() comment '首次创建时间',
    last_modify datetime     not null default now() comment '最后修改时间',
    primary key (id)
)
    comment '用户表';
create table if not exists alibaba.product
(
    id            int(11) auto_increment comment '主键',
    product_name  varchar(128)   not null default '' comment '商品名',
    product_price decimal(10, 2) not null default 0.0 comment '商品价格',
    product_stock int(11)        not null default 0 comment '商品库存',
    create_time   datetime       not null default now() comment '首次创建时间',
    last_modify   datetime       not null default now() comment '最后修改时间',
    primary key (id)
)
    comment '商品表';
create table if not exists alibaba.order
(
    id           int(11) auto_increment comment '主键',
    username     varchar(128) not null default '' comment '下单用户名',
    product_id   int(11)      not null comment '商品表外键',
    product_name varchar(128) not null default '' comment '商品名（冗余）',
    number       int(11)      not null default 1 comment '购买数量',
    create_time  datetime     not null default now() comment '首次创建时间',
    last_modify  datetime     not null default now() comment '最后修改时间',
    primary key (id),
    foreign key (product_id) references product (id)
)
    comment '订单表';
create table if not exists alibaba.member
(
    member_id   int(11) auto_increment comment '会员表主键',
    username    varchar(128) not null default '' comment '登录账号',
    password    varchar(128) not null default '' comment '登录密码',
    nick_name   varchar(128) not null default '' comment '会员昵称',
    real_name   varchar(128) not null default '' comment '真实姓名',
    create_time datetime     not null default now() comment '首次创建时间',
    last_modify datetime     not null default now() comment '最后修改时间',
    primary key (member_id)
)
    comment '会员表';
create table if not exists alibaba.role
(
    role_id     int(11) auto_increment comment '角色表主键',
    role_title  varchar(128) not null default '' comment '角色名称',
    role_info   varchar(512) not null default '' comment '角色描述',
    create_time datetime     not null default now() comment '首次创建时间',
    last_modify datetime     not null default now() comment '最后修改时间',
    primary key (role_id)
)
    comment '角色表';
create table if not exists alibaba.permission
(
    permission_id    int(11) auto_increment comment '权限表主键',
    permission_title varchar(128) not null default '' comment '权限名称',
    permission_info  varchar(512) not null default '' comment '权限描述',
    create_time      datetime     not null default now() comment '首次创建时间',
    last_modify      datetime     not null default now() comment '最后修改时间',
    primary key (permission_id)
)
    comment '权限表';
create table if not exists alibaba.member_role
(
    member_role_id int(11) auto_increment comment '会员-角色中间表主键',
    member_id      int(11) comment '会员表主键',
    role_id        int(11) comment '角色表主键',
    create_time    datetime not null default now() comment '首次创建时间',
    last_modify    datetime not null default now() comment '最后修改时间',
    primary key (member_role_id)
)
    comment '会员-角色中间表';
create table if not exists alibaba.role_permission
(
    role_permission_id int(11) auto_increment comment '角色-权限中间表主键',
    role_id            int(11) comment '角色表主键',
    permission_id      int(11) comment '权限表主键',
    create_time        datetime not null default now() comment '首次创建时间',
    last_modify        datetime not null default now() comment '最后修改时间',
    primary key (role_permission_id)
)
    comment '会员-角色中间表';

# record

insert into alibaba.user
values (1, 'admin', 'admin', '18888888888', now(), now());
## product
insert into alibaba.product
values (1, '海尔冰箱', 3500.00, 5, now(), now()),
       (2, '小米电视', 4500.00, 6, now(), now()),
       (3, '华为电脑', 5500.00, 7, now(), now());
## order
insert into alibaba.order
values (1, 'admin', 1, '海尔冰箱', 2, now(), now());
## member
insert into alibaba.member
values (1, 'zhaosi', 'zhaosi', '亚洲舞王', '赵国强', now(), now()),
       (2, 'liuneng', 'liuneng', '象牙山小诸葛', '刘能', now(), now()),
       (3, 'guangkun', 'guangkun', '首富', '赵国强', now(), now());
## role
insert into alibaba.role
values (1, 'root', '超级用户', now(), now()),
       (2, 'admin', '管理员', now(), now()),
       (3, 'normal', '普通会员', now(), now());
## permission
insert into alibaba.permission
values (1, 'insert', 'DML添加权限', now(), now()),
       (2, 'select', 'DQL查询权限', now(), now()),
       (3, 'update', 'DML修改权限', now(), now()),
       (4, 'delete', 'DML删除权限', now(), now()),
       (5, 'create', 'DDL创建权限', now(), now()),
       (6, 'drop', 'DDL删除权限', now(), now());
## member_role
insert into alibaba.member_role
values (1, 1, 1, now(), now()),
       (2, 2, 2, now(), now()),
       (3, 3, 3, now(), now());
## role_permission
insert into alibaba.role_permission
values (1, 1, 1, now(), now()),
       (2, 1, 2, now(), now()),
       (3, 1, 3, now(), now()),
       (4, 1, 4, now(), now()),
       (5, 1, 5, now(), now()),
       (6, 1, 6, now(), now()),
       (7, 2, 1, now(), now()),
       (8, 2, 2, now(), now()),
       (9, 2, 3, now(), now()),
       (10, 2, 4, now(), now()),
       (11, 3, 2, now(), now());
commit;

