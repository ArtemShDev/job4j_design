create table departments(
    id serial primary key,
    name varchar(255)
);

create table accounts(
    id serial primary key,
    user_name varchar(255),
	pass varchar(12)
);

create table tasks(
    id serial primary key,
    name varchar(255),
	score int
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id),
	account_id int references accounts(id) unique
);

create table employees_tasks(
    id serial primary key,    
    employe_id int references employees(id),
	task_id int references tasks(id)
);

insert into departments(name) values ('Managers');
insert into departments(name) values ('Developers');
insert into accounts(user_name, pass) values ('Ivan', 'password');
insert into accounts(user_name, pass) values ('Bob', 'passw0rd');
insert into accounts(user_name, pass) values ('Alex', 'password123');
insert into tasks(name, score) values ('Increase profits', 1000);
insert into tasks(name, score) values ('Refactoring code', 800);
insert into departments(name) values ('Developers');
insert into employees(name, department_id, account_id) VALUES ('Ivan', 2, 1);
insert into employees(name, department_id, account_id) VALUES ('Bob', 2, 2);
insert into employees(name, department_id, account_id) VALUES ('Alex', 1, 3);
insert into employees_tasks(employe_id, task_id) VALUES (1, 1);
insert into employees_tasks(employe_id, task_id) VALUES (3, 1);
insert into employees_tasks(employe_id, task_id) VALUES (1, 2);
insert into employees_tasks(employe_id, task_id) VALUES (2, 2);

select * from employees;
select * from departments;
select * from tasks;
select * from accounts;
select * from employees_tasks;
