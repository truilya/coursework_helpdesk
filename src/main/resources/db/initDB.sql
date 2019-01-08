drop table IF EXISTS d_roles;
drop table if exists d_users;
drop table if exists user_roles;
drop table if exists d_status;
drop table if exists status_roles;
drop table if exists d_priority;
drop table if exists issue;
drop table if exists issue_history;

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

create table d_status
(
  id integer primary key,
  name varchar
);

create table status_roles
(
  status_id integer not null,
  role varchar,
  constraint status_roles_idx unique (status_id,role),
  foreign key (status_id) references d_status (id) on delete cascade
);

create table d_priority
(
  id integer primary key,
  name varchar,
  ident varchar not null,
  constraint d_priority_idx1 unique (ident)
);

create table issue
(
  id integer primary key default nextval('global_seq'),
  date_created date not null,
  creator_id integer not null,
  engineer_id integer,
  name varchar not null,
  description varchar,
  priority_id integer not null,
  status_id integer not null,
  foreign key (priority_id) references d_priority (id) on delete cascade,
  foreign key (status_id) references d_status (id) on delete cascade,
  foreign key (creator_id) references d_users (id)
);

create table issue_history
(
  id integer primary key default nextval('global_seq'),
  issue_id integer not null,
  date_change date not null,
  creator_id integer,
  engineer_id integer,
  name varchar,
  description varchar,
  priority_id integer,
  status_id integer,
  changer_id integer,
  comment_txt varchar,
  foreign key (priority_id) references d_priority (id) on delete cascade,
  foreign key (status_id) references d_status (id) on delete cascade,
  foreign key (creator_id) references d_users (id),
  foreign key (engineer_id) references d_users (id),
  foreign key (changer_id) references d_users (id),
  foreign key (issue_id) references issue (id) on delete cascade
);

create table status_ref (
  id integer,
  ref_id integer
);






