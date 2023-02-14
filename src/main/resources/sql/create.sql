/*------------创建数据库--------------*/
create database librarian;
use librarian;
/*---------------创建user表--------------*/
create table user
(
    id       int(11)     not null auto_increment comment '主键',
    username varchar(32) not null comment '用户名',
    password varchar(64) not null comment '密码',
    email    varchar(64) not null comment '邮箱',
    nickname varchar(32) comment '昵称',
    phone    varchar(32) not null comment '电话',
    gender   varchar(32) comment '性别',
    primary key (id),
    unique (username)
);
/*----------------创建book表----------------*/
create table book
(
    id        int(11)     not null auto_increment comment '主键',
    book_name varchar(32) not null comment '书名',
    author    varchar(32) not null comment '作者',
    num       varchar(32) not null default 1 comment '数量',
    press     varchar(32) not null comment '出版社',
    content   varchar(128) comment '描述',
    primary key (id),
    unique (book_name, author)
);
/*---------------创建borrow表---------------*/
create table borrow
(
    id          int(11)     not null auto_increment comment '主键',
    username    varchar(32) not null comment '用户名',
    book_name   varchar(32) not null comment '书名',
    author      varchar(32) not null comment '作者',
    num         varchar(32) not null default 1 comment '数量',
    lend_time   datetime comment '借出时间',
    repaid_time datetime comment '归还时间',
    station     varchar(5)  not null comment '状态 0外借中 1已归还',
    primary key (id),
    foreign key (username) references user (username)
);
/*----------------创建order表--------------*/
create table `order`
(
    id          int(11)     not null auto_increment comment '主键',
    username    varchar(32) not null comment '用户名',
    book_name   varchar(32) not null comment '书名',
    author      varchar(32) not null comment '作者',
    num         varchar(32) not null comment '数量',
    press       varchar(32) not null comment '出版社',
    content     varchar(128) comment '描述',
    launch_time datetime comment '发起时间',
    station     varchar(5)  not null comment '订单状态 0未处理 1已处理',
    primary key (id),
    foreign key (username) references user (username)
);
/*----------------创建role表-----------------*/
create table role
(
    id      int(11)     not null auto_increment comment '主键',
    name    varchar(32) not null comment '角色名',
    content varchar(128) comment '描述',
    primary key (id),
    unique key (name)
);
/*-----------------创建permission表------------*/
create table permission
(
    id      int(11)      not null auto_increment comment '主键',
    url     varchar(64)  not null comment 'url',
    name    varchar(32)  not null comment '权限名',
    content varchar(128) not null comment '描述',
    primary key (id)
);
/*------------------创建user_role表---------------*/
create table user_role
(
    id  int(11) not null auto_increment comment '主键',
    uid int(11) not null comment '用户id',
    rid int(11) not null comment '角色id',
    primary key (id),
    unique key (uid, rid),
    foreign key (uid) references user (id),
    foreign key (rid) references role (id)
);
/*----------------创建role_permission------------*/
create table role_permission
(
    id  int(11) not null auto_increment comment '主键',
    rid int(11) not null comment '角色id',
    pid int(11) not null comment '权限id',
    primary key (id),
    unique key (rid, pid),
    foreign key (rid) references role (id),
    foreign key (pid) references permission (id)
);
/*----------------通过角色id查询拥有的权限名----------------*/
select name
from permission
where id in (select id from role_permission where rid = 1);