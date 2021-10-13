create table apartment (
	id serial primary key,
 	number int,
 	tel varchar(20),
 	price float8,
     description text
 );

  create table residents (
      id serial primary key,	
  	name varchar(255),
  	age int,
  	apartment_id int references apartment(id)
  );

 insert into apartment (number, tel, price, description) values (25, '8-495-777-77-25', 29000.00, 'Best apartment for 1 family');
  insert into apartment (number, tel, price, description) values (42, '8-495-777-77-42', 20000.00, 'Best apartment for single');
  insert into residents(name, age, apartment_id) VALUES('Egor', 7, 7);
  insert into residents(name, age, apartment_id) VALUES('Alex', 39, 7);
  insert into residents(name, age, apartment_id) VALUES('Anna', 36, 7); 
  insert into residents(name, age, apartment_id) VALUES('Maks', 25, 8);
  insert into residents(name, age) VALUES('Irina', 24);
 select p.name as Имя, p.age as Возраст, ap.number as НомерКвартиры from residents as p join apartment as ap on p.apartment_id = ap.id;
 select * from residents as p join apartment as ap on p.apartment_id = ap.id where ap.description like '%single%';
 select p.name from residents as p join apartment as ap on p.apartment_id = ap.id where ap.description like '%family%' order by p.age;
 