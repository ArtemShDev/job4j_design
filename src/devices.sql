 create table devices(
     id serial primary key,
     name varchar(255),
     price float
 );

 create table people(
     id serial primary key,
     name varchar(255)
 );

 create table devices_people(
     id serial primary key,
     device_id int references devices(id),
     people_id int references people(id)
 );

 insert into devices(name, price) VALUES('honor10', 15000.50);
 insert into devices(name, price) VALUES('huawey', 25000.50);
 insert into devices(name, price) VALUES('noname', 4000.50);
 insert into devices(name, price) VALUES('iphone10', 40000.00);
 insert into devices(name, price) VALUES('iphone12', 50000.00);
 insert into people(name) VALUES('Maks'), ('Olga'), ('Bob');
 insert into devices_people(device_id, people_id) VALUES(1, 1);
 insert into devices_people(device_id, people_id) VALUES(3, 1);
 insert into devices_people(device_id, people_id) VALUES(2, 2);
 insert into devices_people(device_id, people_id) VALUES(3, 3);
 insert into devices_people(device_id, people_id) VALUES(3, 2);
select avg(d.price) from devices_people as dp join devices as d on dp.device_id = d.id;
select p.name, avg(d.price) from devices_people as dp join people as p on dp.people_id = p.id 
													  join devices as d on dp.device_id = d.id group by p.name;

select p.name, avg(d.price) from devices_people as dp join people as p on dp.people_id = p.id 
													  join devices as d on dp.device_id = d.id group by p.name
													  having avg(d.price) > 5000;