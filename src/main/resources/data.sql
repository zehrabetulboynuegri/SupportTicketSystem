create table if not exists persistent_logins ( 
  username varchar(100) not null, 
  series varchar(64) primary key, 
  token varchar(64) not null, 
  last_used timestamp not null
);

delete from  users_roles;
delete from  role;
delete from  user;


INSERT INTO role (id, name) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_ACTUATOR'),
(3, 'ROLE_USER')
;

INSERT INTO user (id, email, password, name) VALUES 
(1, 'admin@gmail.com', '{bcrypt}$2a$10$.xuUgiYgl.mqqJ3hMOYUteFiwyDJc59qUoMN93.FM6qKZOL4rvEJG', 'Admin'),
(2, 'deneme@gmail.com', '{bcrypt}$2a$10$i9nUOsXSC7uIuvohkjycTOstD1/TMwLSDIl9a5.vGqypdXtRyL7Gy', 'deneme'),
(3, 'betul@gmail.com', '{bcrypt}$2a$10$DA/Hz9AU.xI8M7mFPYGzGO7KCawdXSkYYl2nVIUUXACSy7//mnOL6', 'betul');


insert into users_roles(user_id, role_id) values
(1,1),
(1,2),
(1,3),
(3,2)
;