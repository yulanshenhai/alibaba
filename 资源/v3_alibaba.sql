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

# record

insert into alibaba.user
values (2, 'zhaosi', 'zhaosi', '18888888889', now(), now());
# values (1, 'admin', 'admin', '18888888888', now(), now());
insert into alibaba.product
values (1, '海尔冰箱', 3500.00, 5, now(), now()),
       (2, '小米电视', 4500.00, 6, now(), now()),
       (3, '华为电脑', 5500.00, 7, now(), now());
insert into alibaba.order
values (1, 'admin', 1, '海尔冰箱', 2, now(), now());
commit;

