drop table IF EXISTS d_roles;
drop table if exists d_users;
drop table if exists user_roles;

drop sequence if exists global_seq;

create sequence global_seq start 10000;

create table d_roles(
  id integer primary key default nextval('global_seq'),
  name varchar not NULL,
  ident varchar not null
);

create table d_users(
  id integer primary key default nextval('global_seq'),
  login varchar not null unique,
  password varchar
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES d_users (id) ON DELETE CASCADE
);






