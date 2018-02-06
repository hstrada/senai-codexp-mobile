/*
=
=	Referências: https://dev.mysql.com/doc/refman/5.7/en/create-table-foreign-keys.html
=
*/


/* --------------------------------------------------------------------------------------- */

/*
=
=	Mostrar os problemas para trabalhar apenas com uma tabela
=
*/

DROP SCHEMA IF EXISTS senai_codexp_mobile;
CREATE SCHEMA senai_codexp_mobile;
USE senai_codexp_mobile;

create table filmes
(
	id 			int primary key auto_increment not null
    ,nome 		varchar(255) not null
    ,genero		varchar(255)
);

insert into filmes (nome, genero) values ('Rambo - First Blood', 'Ação');
insert into filmes (nome, genero) values ('Rambo - First Blood Part II', 'Ação');
insert into filmes (nome, genero) values ('Rambo III', 'Ação');
insert into filmes (nome, genero) values ('Rambo', 'Ação');
insert into filmes (nome, genero) values ('Como eu era antes de você', 'Romance');

select * from filmes;

select nome, genero from filmes;

select * from filmes where genero = 'Romance';
select * from filmes where id > 2;

-- deletando um filme
delete from filmes where id = 2;

select * from filmes;

-- atualizando um filme
update filmes set nome = 'Como eu sou depois de você' where id = 5;

select * from filmes;

insert into filmes (nome, genero) values ('Rambo - O retorno', 'Aço');

select * from filmes where genero = 'Ação';

-- E se criássemos uma tabela para guardar as informações que se repetem em nossa tabela?

create table generos 
(
	id 		int auto_increment primary key not null
    ,nome 	varchar(255) not null
);

/* --------------------------------------------------------------------------------------- */

/*
=
=	Como criar uma tabela com chave estrangeira
=
*/

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
);

-- inserindo primeiro os valores em generos
insert into generos (nome) values ('Ação'), ('Romance'), ('Drama'), ('Desenho');
select * from generos;

-- inserindo os valores dos filmes, uma vez que temos os valores dos generos
insert into filmes (nome, genero) values ('Rambo I', 1), ('Minions', 4), ('Rambo II', 1);
insert into filmes (nome) values ('Filme sem Gênero');
select * from filmes;

-- Realizar a consulta trazendo os dados das duas tabelas
select f.*, g.* from filmes f inner join generos g on f.genero = g.id;

/* --------------------------------------------------------------------------------------- */

/*
=
=	Mostrar como adicionar uma chave estrangeira depois que a tabela foi criada
=
*/

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
);

/* ALTER TABLE nome-da-tabela ADD CONSTRAINT nome-da-constraint 
FOREIGN KEY(nome-da-coluna-local) REFERENCES nome-da-tabela-da-fk(coluna-fk); */

ALTER TABLE filmes ADD CONSTRAINT fk_filmes_generos_id
FOREIGN KEY (genero) REFERENCES generos(id);