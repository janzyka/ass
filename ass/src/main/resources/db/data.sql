insert into students (id, name, surname, email) values (1, 'Jan', 'Zyka', 'zyka.jan@gmail.com');
insert into students (id, name, surname, email) values (2, 'Martin', 'Novak', 'martin.novak@gmail.com');
insert into students (id, name, surname, email) values (3, 'Matej', 'Hron', 'matej.hron@gmail.com');
insert into students (id, name, surname, email) values (4, 'Jirka', 'Hokes', 'jirka.hokes@gmail.com');

insert into roles (id, role_name) values (1, 'ADMIN');
insert into users (id, username, password) values (1, 'zyka.jan@gmail.com', 'heslo');

insert into user_roles (user_id, role_id) values (1,1);