-- use NAME OF YOUR DB;

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

INSERT INTO users (user_email, user_pass,users_data) VALUES ('kurt_admin@somewhere.dk', 'test','Kurt_admins personal data');
INSERT INTO users (user_email, user_pass,users_data) VALUES ('peter_user@somewhere.dk', 'test','Peter_users personal data');
INSERT INTO roles (role_name) VALUES ('user');
INSERT INTO roles (role_name) VALUES ('admin');
INSERT INTO user_roles (user_email, role_name) VALUES ('kurt_admin@somewhere.dk', 'admin');
INSERT INTO user_roles (user_email, role_name) VALUES ('peter_user@somewhere.dk', 'user');