/* --------------------------------------------------------------------------------------- */

/*
=
=	Mostrar as diferenças entre Restrict/No Action, Cascade e Set Null
=
*/

-- ONCASCADE

drop schema if exists senai_codexp_mobile;
create schema senai_codexp_mobile;
use senai_codexp_mobile;

create table generos 
(
	id 		int auto_increment primary key not null
    ,nome 	varchar(255) not null
);

create table filmes 
(
	id 			int not null auto_increment primary key
	,nome		varchar(255) not null
    ,genero 	int
    ,foreign key (genero) 
		references generos(id)
        on delete cascade	
);

-- inserindo primeiro os valores em generos
insert into generos (nome) values ('Ação'), ('Romance'), ('Drama'), ('Desenho');
select * from generos;

-- inserindo os valores dos filmes, uma vez que temos os valores dos generos
insert into filmes (nome, genero) values ('Rambo I', 1), ('Minions', 4), ('Rambo II', 1), ('Como eu era antes de vocÊ', 3), ('Romance', 2);
insert into filmes (nome) values ('Filme sem Gênero');
select * from filmes;

-- o que acontece se deletarmos um filme?
delete from filmes where id = 3;

-- e se deletarmos um genero?
delete from generos where id = 1;

-- RESTRICT/NO ACTION

drop schema if exists senai_codexp_mobile;
create schema senai_codexp_mobile;
use senai_codexp_mobile;

create table generos 
(
	id 		int auto_increment primary key not null
    ,nome 	varchar(255) not null
);

create table filmes 
(
	id 			int not null auto_increment primary key
	,nome		varchar(255) not null
    ,genero 	int
    ,foreign key (genero) 
		references generos(id)
        on delete restrict
);

-- inserindo primeiro os valores em generos
insert into generos (nome) values ('Ação'), ('Romance'), ('Drama'), ('Desenho');
select * from generos;

-- inserindo os valores dos filmes, uma vez que temos os valores dos generos
insert into filmes (nome, genero) values ('Rambo I', 1), ('Minions', 4), ('Rambo II', 1), ('Como eu era antes de vocÊ', 3), ('Romance', 2);
insert into filmes (nome) values ('Filme sem Gênero');
select * from filmes;

-- o que acontece se deletarmos um filme?
delete from filmes where id = 3;

-- e se deletarmos um genero?
delete from generos where id = 1;

-- SET NULL

drop schema if exists senai_codexp_mobile;
create schema senai_codexp_mobile;
use senai_codexp_mobile;

create table generos 
(
	id 		int auto_increment primary key not null
    ,nome 	varchar(255) not null
);

create table filmes 
(
	id 			int not null auto_increment primary key
	,nome		varchar(255) not null
    ,genero 	int
    ,foreign key (genero) 
		references generos(id)
        on delete set null
);

-- inserindo primeiro os valores em generos
insert into generos (nome) values ('Ação'), ('Romance'), ('Drama'), ('Desenho');
select * from generos;

-- inserindo os valores dos filmes, uma vez que temos os valores dos generos
insert into filmes (nome, genero) values ('Rambo I', 1), ('Minions', 4), ('Rambo II', 1), ('Como eu era antes de vocÊ', 3), ('Romance', 2);
insert into filmes (nome) values ('Filme sem Gênero');
select * from filmes;

-- o que acontece se deletarmos um filme?
delete from filmes where id = 3;

-- e se deletarmos um genero?
delete from generos where id = 1;