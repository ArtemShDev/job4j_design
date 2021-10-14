  create table type(
  	id serial primary key,
     name varchar(255)
  );
 
  create table product(
      id serial primary key,
      name varchar(255),
 	 type_id int references type(id),
 	 expired_date date,
      price float
  );

 insert into type(name) values('сыр'), ('молоко'), ('мучные изделия');
 insert into product(name, type_id, expired_date, price) values('сыр пошехонский', 1, '2021-10-25', 350);
 insert into product(name, type_id, expired_date, price) values('сыр масдам', 1, '2021-10-12', 450);
 insert into product(name, type_id, expired_date, price) values('сыр джугас', 1, '2021-10-30', 550);
 insert into product(name, type_id, expired_date, price) values('молоко Беларусь', 2, '2021-10-30', 80);
 insert into product(name, type_id, expired_date, price) values('молоко РФ', 2, '2021-10-10', 90);
 insert into product(name, type_id, expired_date, price) values('мороженое', 2, '2021-10-30', 50);
 insert into product(name, type_id, expired_date, price) values('сдоба московская', 3, '2021-10-28', 40);
 insert into product(name, type_id, expired_date, price) values('рогалик с изюмом и творогом', 3, '2021-10-29', 40);

select * from product as p join type as t on p.type_id = t.id where t.name like 'сыр';
select * from product as p where p.name like '%мороженое%';
select * from product as p where current_date > p.expired_date;
select * from product as p where p.price = (select max(price) from product);
select t.name as имя_типа, count(p.name) as количество from product as p join type as t on p.type_id = t.id GROUP BY t.name;
select * from product as p join type as t on p.type_id = t.id where t.name like 'сыр' or t.name like 'молоко';
select t.name as имя_типа, count(p.name) as количество from product as p join type as t on p.type_id = t.id GROUP BY t.name having count(p.name) < 10;
select p.name, t.name from product as p join type as t on p.type_id = t.id;