create table apartments (
    id serial primary key,
	number int,
	tel varchar(20),
	price float8,
    description text
);
insert into apartments (number, tel, price, description) values (25, '8-495-777-77-25', 25000.00, 'Best apartment');
select * from apartments;
update apartments set price = 26000.00;
select * from apartments;
delete from apartments;
select * from apartments;