insert into d_status (id, name)
    select t.id,t.nam from (
select 1 id, 'Новая' nam
union all
select 2 id, 'В работе' nam
union all
select 3 id, 'Требуются уточнения' nam
union all
select 4 id, 'Попридержать' nam
union all
select 5 id, 'Исправлена' nam
union all
select 6 id, 'Закрыта' nam
union all
select 7 id, 'Отменена' nam) t
on conflict (id) do update set name=excluded.name;

insert into d_priority(id,name,ident)
    select t.id,t.nam,t.ident from
        (select 1 id, 'Блокирующая' nam, 'blocker' ident
         union all
         select 2 id, 'Критичная' nam, 'critical' ident
         union all
         select 3 id, 'Значительная' nam, 'major' ident
         union all
         select 4 id, 'Незначительная' nam, 'minor' ident
         union all
         select 5 id, 'Тривиальная' nam, 'trivial' ident) t
on conflict (id) do update set
    name = excluded.name,
    ident = excluded.ident;

delete from status_roles;

insert into status_roles(status_id,role)
    select t.status_id, t.rol from
        (/*select 1 status_id, 'USER' rol
         union all*/
         select 4 status_id, 'USER' rol
         union all
         select 6 status_id, 'USER' rol
         union all
         select 7 status_id, 'USER' rol
         union all
         select 2 status_id, 'ENGINEER' rol
         union all
         select 3 status_id, 'ENGINEER' rol
         union all
         select 5 status_id, 'ENGINEER' rol) t;
