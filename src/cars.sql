  create table transmissions(
     id serial primary key,
     name varchar(255)
  );
 
  create table engines(
     id serial primary key,
     name varchar(255)
  );
 
   create table bodys(
     id serial primary key,
     name varchar(255)
  );

  create table cars(
    id serial primary key,
    name varchar(255),
  	trans_id int references transmissions(id),
 	eng_id int references engines(id),
 	body_id int references bodys(id)
  );

  insert into transmissions(name) values ('Auto'), ('Manual'), ('Variator');
  insert into engines(name) values ('2.4'), ('2.0'), ('1.8'), ('2.0 D'), ('5.0'), ('3.2');
  insert into bodys(name) values ('Sedan'), ('Universal'), ('hatchback'), ('Cabriolet'); 
  insert into cars(name, trans_id, eng_id, body_id) VALUES ('Sedan 2.0 A', 1, 2, 1);
  insert into cars(name, trans_id, eng_id, body_id) VALUES ('Sedan 2.4 M', 2, 1, 1);
  insert into cars(name, trans_id, eng_id, body_id) VALUES ('Universal 2.0 Diesel M', 2, 4, 2);
  insert into cars(name, trans_id, eng_id, body_id) VALUES ('hatchback 1.8 A', 1, 3, 3);

select cars.name as car, bodys.name as body, engines.name as eng, t.name as trans from cars 
				   join bodys on cars.body_id = bodys.id
				   join engines on cars.eng_id = engines.id
				   join transmissions as t on cars.trans_id = t.id; 
select * from transmissions as t left join cars on t.id = cars.trans_id where cars.trans_id is null;
select * from engines as e left join cars on e.id = cars.eng_id where cars.eng_id is null;
select * from bodys as b left join cars on b.id = cars.body_id where cars.body_id is null;

