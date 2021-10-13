create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into fauna(name, avg_age, discovery_date) VALUES('fish', 15000, '1920-01-01');
insert into fauna(name, avg_age, discovery_date) VALUES('bird', 25000, '1953-01-01');
insert into fauna(name, avg_age, discovery_date) VALUES('dino', 150000, '1890-01-01');
insert into fauna(name, avg_age) VALUES('dinoZ', 190000);
select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 AND avg_age < 26000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';