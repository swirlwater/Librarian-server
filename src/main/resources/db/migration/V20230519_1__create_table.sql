/*---------------创建user表--------------*/
create table user
(
    id       int not null auto_increment comment '主键',
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
    id        int not null auto_increment comment '主键',
    book_name varchar(32) not null comment '书名',
    author    varchar(32) not null comment '作者',
    num       int not null default 1 comment '数量',
    press     varchar(32) not null comment '出版社',
    content   varchar(128) comment '描述',
    primary key (id),
    unique (book_name, author)
);
/*---------------创建borrow表---------------*/
create table borrow
(
    id          int not null auto_increment comment '主键',
    username    varchar(32) not null comment '用户名',
    book_name   varchar(32) not null comment '书名',
    author      varchar(32) not null comment '作者',
    num         int not null default 1 comment '数量',
    lend_time   datetime default null comment '借出时间',
    repaid_time datetime default null comment '归还时间',
    station     int not null comment '状态 0申请借阅 1借阅中 2申请归还 3已归还 4撤销',
    primary key (id),
    foreign key (username) references user (username)
);
/*----------------创建order表--------------*/
create table `order`
(
    id          int not null auto_increment comment '主键',
    username    varchar(32) not null comment '用户名',
    book_name   varchar(32) not null comment '书名',
    author      varchar(32) not null comment '作者',
    num         int not null comment '数量',
    press       varchar(32) not null comment '出版社',
    content     varchar(128) comment '描述',
    launch_time datetime comment '发起时间',
    station     int not null comment '订单状态 0未处理 1已处理 2撤销',
    primary key (id),
    foreign key (username) references user (username)
);
/*----------------创建role表-----------------*/
create table role
(
    id      int not null auto_increment comment '主键',
    name    varchar(32) not null comment '角色名',
    content varchar(128) comment '描述',
    primary key (id),
    unique key (name)
);
/*-----------------创建permission表------------*/
create table permission
(
    id        int not null auto_increment comment '主键',
    name      varchar(32) not null comment '权限名',
    url       varchar(64) not null comment '权限路径',
    component varchar(32) not null comment '组件',
    icon      varchar(32) not null comment '图标',
    primary key (id)
);
/*------------------创建user_role表---------------*/
create table user_role
(
    id  int not null auto_increment comment '主键',
    uid int not null comment '用户id',
    rid int not null comment '角色id',
    primary key (id),
    unique key (uid, rid),
    foreign key (uid) references user (id),
    foreign key (rid) references role (id)
);
/*----------------创建role_permission------------*/
create table role_permission
(
    id  int not null auto_increment comment '主键',
    rid int not null comment '角色id',
    pid int not null comment '权限id',
    primary key (id),
    unique key (rid, pid),
    foreign key (rid) references role (id),
    foreign key (pid) references permission (id)
);