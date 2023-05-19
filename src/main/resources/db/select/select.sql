/*----------------通过角色id查询拥有的权限名----------------*/
select name
from permission
where id in (select id from role_permission where rid = 1);
/*-------------------通过用户id查询相应权限----------------------*/
select distinct *
from permission
where id in (select pid from role_permission where rid in (select rid from user_role where uid = -666873854));