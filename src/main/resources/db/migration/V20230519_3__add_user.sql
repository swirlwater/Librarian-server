/*----------------插入超级管理员---------------*/
insert into user value (1, 'zhangsan', '$2a$10$XXBYSOvaKsJA27.Ac9ITUepKEweLm0CWy.JHw9910QOrWoMeVhuAi',
                        '1978938887@qq.com', 'mark', '15813555711', 'male');
insert into role value (1, 'admin', '超级管理员');
insert into user_role value (1, 1, 1);
insert into role_permission
values (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 1, 4),
       (5, 1, 5),
       (6, 1, 6),
       (7, 1, 7),
       (8, 1, 8);
insert into role value (2, 'user', '用户');
insert into user_role
values (2, 1, 2);
insert into role_permission
values (9, 2, 1),
       (10, 2, 2),
       (11, 2, 3);