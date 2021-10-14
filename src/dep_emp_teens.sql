 create table departments(
     id serial primary key,
     name varchar(255)
 );

 create table emploers(
     id serial primary key,
     name varchar(255),
 	dep_id int references departments(id)
 );

 insert into departments(name) values ('Managers'), ('Developers'), ('Analyst');
 insert into emploers(name, dep_id) VALUES ('Ivan', 2);
 insert into emploers(name, dep_id) VALUES ('Bob', 2);
 insert into emploers(name, dep_id) VALUES ('Alex', 1);
 insert into emploers(name) VALUES ('Mike');

  create table teens(
      id serial primary key,
      name varchar(255),
  	 gender char
  );
 
  insert into teens(name, gender) VALUES ('Ivan', 'm');
  insert into teens(name, gender) VALUES ('Maks', 'm');
  insert into teens(name, gender) VALUES ('Alex', 'm');
  insert into teens(name, gender) VALUES ('Niki', 'f');
  insert into teens(name, gender) VALUES ('Rose', 'f');
  insert into teens(name, gender) VALUES ('Mary', 'f');

select * from departments as d left join emploers as e on d.id = e.dep_id; 
select * from departments as d right join emploers as e on d.id = e.dep_id;
select * from departments as d full join emploers as e on d.id = e.dep_id;
select * from departments as d cross join emploers as e;
select * from departments as d left join emploers as e on d.id = e.dep_id where e.dep_id is null;
select * from departments as d right join emploers as e on d.id = e.dep_id;
select * from emploers as e left join departments as d on d.id = e.dep_id;

select * from teens as t1 cross join teens as t2 where t1.gender != t2.gender;