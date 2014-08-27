create table if not exists students
(
  id int not null primary key,
  name varchar(64) not null,
  surname varchar(64) not null,
  email varchar(64) not null
);

create table if not exists users
(
  id int not null primary key,
  username varchar(64) not null,
  password varchar(64) not null
);

create table if not exists roles
(
  id int not null primary key,
  role_name varchar(64) not null
);

create table if not exists user_roles
(
  user_id int not null,
  role_id int not null,

  foreign key(user_id) references users(id),
  foreign key(role_id) references roles(id)
);

/*
  For some reason data.sql is not loaded by Spring BOOT once you @Autowire UserService into AuthenticationConfiguration.
  This is fairly weird behaviour. Try yourself  :)
 */
runscript from 'classpath:db/data.sql';
