/*------------创建数据库--------------*/
create
    database librarian;
use
    librarian;
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
    num       int(5)      not null default 1 comment '数量',
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
    num         int(5)      not null default 1 comment '数量',
    lend_time   datetime default null comment '借出时间',
    repaid_time datetime default null comment '归还时间',
    station     int(1) not null comment '状态 0申请借阅 1借阅中 2申请归还 3已归还 4撤销',
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
    num         int(5)      not null comment '数量',
    press       varchar(32) not null comment '出版社',
    content     varchar(128) comment '描述',
    launch_time datetime comment '发起时间',
    station     int(1)      not null comment '订单状态 0未处理 1已处理 2撤销',
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
    id        int(11)     not null auto_increment comment '主键',
    name      varchar(32) not null comment '权限名',
    url       varchar(64) not null comment '权限路径',
    component varchar(32) not null comment '组件',
    icon      varchar(32) not null comment '图标',
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
/*-----------------插入权限------------------*/
insert into permission
values (1, '书籍搜索', 'sys:book:bookSearch', 'bookSearch', 'ios-paper'),
       (2, '借阅记录', 'sys:borrow:borrowSearch', 'borrowSearch', 'md-stats'),
       (3, '添加订单', 'sys:borrow:orderAdd', 'orderAdd', 'md-cart'),
       (4, '借阅管理', 'sys:borrow:borrowManage', 'borrowManage', 'md-paper'),
       (5, '书籍管理', 'sys:book:bookManage', 'bookManage', 'ios-book'),
       (6, '订单管理', 'sys:book:orderManage', 'orderManage', 'ios-cart'),
       (7, '用户管理', 'sys:user:userManage', 'userManage', 'md-people'),
       (8, '角色管理', 'sys:user:roleManage', 'roleManage', 'ios-build');
/*----------------插入超级管理员---------------*/
insert into user value (1, 'zhangsan', '$2a$10$qdNVeQ5hIVS45t8foOSIp.7pyP/8Lu9x1uY4uGHEzvPz63IIjOMme',
                        '1978938887@qq.com', 'mark', '15813555711', 'male');
insert into role value (1, 'admin', '超级管理员');
insert into user_role value (1, 1, 1);
insert into role_permission
values (1, 1, 1),
       (2,1,2),
       (3,1,3),
       (4,1,4),
       (5,1,5),
       (6,1,6),
       (7,1,7),
       (8,1,8);
/*----------------通过角色id查询拥有的权限名----------------*/
select name
from permission
where id in (select id from role_permission where rid = 1);
/*-------------------通过用户id查询相应权限----------------------*/
select distinct *
from permission
where id in (select pid from role_permission where rid in (select rid from user_role where uid = -666873854));