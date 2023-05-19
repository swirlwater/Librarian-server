/*-----------------插入权限------------------*/
insert into permission
values (1, '书籍搜索', 'sys:book:bookSearch', 'bookSearch', 'ios-paper'),
       (2, '借阅记录', 'sys:borrow:borrowSearch', 'borrowSearch', 'md-stats'),
       (3, '添加订单', 'sys:order:orderAdd', 'orderAdd', 'md-cart'),
       (4, '借阅管理', 'sys:borrow:borrowManage', 'borrowManage', 'md-paper'),
       (5, '书籍管理', 'sys:book:bookManage', 'bookManage', 'ios-book'),
       (6, '订单管理', 'sys:order:orderManage', 'orderManage', 'ios-cart'),
       (7, '用户管理', 'sys:user:userManage', 'userManage', 'md-people'),
       (8, '角色管理', 'sys:user:roleManage', 'roleManage', 'ios-build');
