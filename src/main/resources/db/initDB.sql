drop table IF EXISTS d_roles;
drop table if exists d_users;
drop table if exists ref_user_roles;

drop sequence if exists global_seq;

create sequence global_seq start 10000;

create table d_roles(
  id integer primary key default nextval('global_seq'),
  name varchar not NULL
);

create table d_users(
  id integer primary key default nextval('global_seq'),
  login varchar not null unique,
  password varchar
);

create table ref_user_roles(
  id_role integer,
  id_user integer
);

alter table ref_user_roles add primary key (id_role, id_user);




