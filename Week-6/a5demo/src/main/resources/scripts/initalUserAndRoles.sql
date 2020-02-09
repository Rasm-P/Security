
-- INSERT INTO users (user_name, user_pass) VALUES ('admin', 'test')
-- INSERT INTO users (user_name, user_pass) VALUES ('user', 'test')
-- INSERT INTO users (user_name, user_pass) VALUES ('user_admin', 'test')
-- 
-- INSERT INTO roles (role_name) VALUES ('Admin')
-- INSERT INTO roles (role_name) VALUES ('User')
-- 
-- 
-- INSERT INTO user_roles (role_name, user_name) VALUES ('Admin', 'admin')
-- INSERT INTO user_roles (role_name, user_name) VALUES ('User', 'user')
-- INSERT INTO user_roles (role_name, user_name) VALUES ('User', 'user_admin')
-- INSERT INTO user_roles (role_name, user_name) VALUES ('Admin', 'user_admin')

use a5demo;

create table users
(
  user_email varchar(45) primary key,
  user_pass varchar(255) not null,
  users_data varchar(255)
);

create table roles
(
  role_name varchar(15) primary key
);

create table user_roles
(
  user_email varchar(45) not null,
  role_name varchar(15) not null,
  foreign key (user_email) references users (user_email),
  foreign key (role_name) references roles (role_name),
  primary key( user_email, role_name )
);

INSERT INTO users (user_email, user_pass,users_data) VALUES ('a@b.dk', 'test','A''s personal data');
INSERT INTO users (user_email, user_pass,users_data) VALUES ('b@b.dk', 'test23','Every moment is a fresh beginning.');
INSERT INTO users (user_email, user_pass,users_data) VALUES ('c@b.dk', 'test23','Whatever you do, do it well.');
INSERT INTO users (user_email, user_pass,users_data) VALUES ('d@b.dk', 'test23','Yesterday you said tomorrow. Just do it');
INSERT INTO users (user_email, user_pass,users_data) VALUES ('e@b.dk', 'test23','I could agree with you but then we’d both be wrong');
INSERT INTO roles (role_name) VALUES ('user');
INSERT INTO user_roles (user_email, role_name) VALUES ('a@b.dk', 'user');
INSERT INTO user_roles (user_email, role_name) VALUES ('b@b.dk', 'user');
INSERT INTO user_roles (user_email, role_name) VALUES ('c@b.dk', 'user');
INSERT INTO user_roles (user_email, role_name) VALUES ('d@b.dk', 'user');
INSERT INTO user_roles (user_email, role_name) VALUES ('e@b.dk', 'user');


