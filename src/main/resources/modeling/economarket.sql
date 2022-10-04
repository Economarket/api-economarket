create or replace procedure economarket()
language 'plpgsql'
as $$
begin
	
RAISE NOTICE 'Total de tabelas criadas 0/12';
drop table if exists public.brand cascade;

create table public.brand(
	id int primary key generated always as identity,
	searchName varchar(128) unique not null,
	brandName varchar(128) unique not null
);

insert into public.brand(searchName, brandName) values ('cocacola', 'Coca-Cola');
insert into public.brand(searchName, brandName) values ('nestle', 'Nestlé');
insert into public.brand(searchName, brandName) values ('pepsico', 'Pepsico');
insert into public.brand(searchName, brandName) values ('jaboti', 'Jaboti');
insert into public.brand(searchName, brandName) values ('unilever', 'Unilever');
insert into public.brand(searchName, brandName) values ('generalmills', 'General Mills');
insert into public.brand(searchName, brandName) values ('danone', 'Danone');
insert into public.brand(searchName, brandName) values ('mondelez', 'Mondeléz');
insert into public.brand(searchName, brandName) values ('mars', 'Mars');
insert into public.brand(searchName, brandName) values ('kraftheinz', 'KraftHeinz');
insert into public.brand(searchName, brandName) values ('jbs', 'JBS');
insert into public.brand(searchName, brandName) values ('itambe', 'Itambé');

RAISE NOTICE 'Total de tabelas criadas 1/12';
drop table if exists public.category cascade;

create table public.category(
	id int primary key generated always as identity,
	uuid varchar(128) unique null,
	name varchar(128) unique not null
);

insert into public.category(uuid, name) values (null, 'Carnes');
insert into public.category(uuid, name) values (null, 'Higiene');
insert into public.category(uuid, name) values (null, 'Limpeza');
insert into public.category(uuid, name) values (null, 'Laticínios/Frios');
insert into public.category(uuid, name) values (null, 'Enlatados');
insert into public.category(uuid, name) values (null, 'Cereais');
insert into public.category(uuid, name) values (null, 'Utilidades');
insert into public.category(uuid, name) values (null, 'Condimentos');
insert into public.category(uuid, name) values (null, 'Frutas e Vegetais');
insert into public.category(uuid, name) values (null, 'Congelados');
insert into public.category(uuid, name) values (null, 'Bebidas');
insert into public.category(uuid, name) values (null, 'Mercearia');

RAISE NOTICE 'Total de tabelas criadas 2/12';
drop table if exists public.permission cascade;

create table public.permission(
	id int primary key generated always as identity,
	name varchar(128) unique not null
);

RAISE NOTICE 'Total de tabelas criadas 3/12';
drop table if exists public.user cascade;

create table public.user(
	id int primary key generated always as identity,
    uuid varchar(128) unique null,
    name varchar(128) not null,
    password varchar(256) not null,
    email varchar(128) not null,
    experience numeric(14,2) null
);
RAISE NOTICE 'Total de tabelas criadas 4/12';

drop table if exists public.user_permission ;

create table public.user_permission(
	id int primary key generated always as identity,
	userId int not null,
	permissionId int not null,
	foreign key (userId) references public.user (id),
	foreign key (permissionId) references public.permission (id)
);
RAISE NOTICE 'Total de tabelas criadas 5/12';
drop table if exists public.product cascade;

create table public.product(
	id int primary key generated always as identity,
	uuid varchar(128) unique null,
	name varchar(128) unique not null,
	price numeric(14,4) null,
	brandId int not null,
	categoryId int not null,
	unity varchar(8) not null,
	foreign key (brandId) references public.brand (id),
	foreign key (categoryId) references public.category (id)
);

insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Fanta', 8.30, 1, 11, 'UN');
insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Lays''s', 9.10, 3, 12, 'PC');
insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Hellmann''s', 7.00, 5, 8, 'UN');
insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Carne Moída', 17.00, 11, 1, 'KG');
insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Bisquick', 12.00, 6, 6, 'PC');
insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Twix', 4.00, 9, 12, 'UN');
insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Nescafé', 12.00, 2, 12, 'UN');
insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Quaker', 8.00, 3, 6, 'UN');
insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Chicken Tonight', 12.99, 5, 10, 'G');
insert into public.product(uuid, name, price, brandId, categoryId, unity) values (null, 'Tomato Ketchup', 7.80, 10, 8, 'UN');
RAISE NOTICE 'Total de tabelas criadas 6/12';

drop table if exists public.shopping_list cascade;
create table public.shopping_list(
	id int primary key generated always as identity,
	uuid varchar(128) unique null,
	userId int not null,
	foreign key (userId) references public.user (id)
);

RAISE NOTICE 'Total de tabelas criadas 7/12';

drop table if exists public.product_list;
create table public.product_list(
	id int primary key generated always as identity,
	uuid varchar(128) unique null,
	shoppingListId int not null,
	productId int not null,
	quantity numeric(14,2) not null,
	foreign key (productId) references public.product (id),
	foreign key (shoppingListId) references public.shopping_list (id)
);

RAISE NOTICE 'Total de tabelas criadas 8/12';

drop table if exists public.market cascade;
create table public.market(
	id int primary key generated always as identity,
	uuid varchar(128) unique null,
	name varchar(256) not null,
	description varchar(256) not null,
	logo bytea null,
	locateX int not null,
	locateY int not null
);

RAISE NOTICE 'Total de tabelas criadas 9/12';

drop table if exists public.address cascade;
create table public.address(
	id int primary key generated always as identity,
	marketId int not null,
	userId int not null,
	cep varchar(25) not null,
	street varchar(128) not null,
	number int not null,
	complement varchar(128) null,
	district varchar(128) not null,
	city  varchar(256) not null,
	state varchar(128) not null,
	foreign key (userId) references public.user (id),
	foreign key (marketId) references public.market (id)
);

RAISE NOTICE 'Total de tabelas criadas 10/12';

drop table if exists public.market_with_product;
create table public.market_with_product(
	id int primary key generated always as identity,
	marketId int not null,
	productId int not null,
	foreign key (marketId) references public.market (id),
	foreign key (productId) references public.product (id)
);

RAISE NOTICE 'Total de tabelas criadas 11/12';

drop table if exists public.schedules cascade;
create table public.schedules(
	id int primary key generated always as identity,
	marketId int not null,
	oppeningHour time not null,
	closingHour time not null,
	dayOfWeek int not null,
	foreign key (marketId) references public.market (id)
);

RAISE NOTICE 'Total de tabelas criadas 12/12';

end;
$$;
--select * from product 
--select * from brand;
--select * from category;
--
--call economarket();
