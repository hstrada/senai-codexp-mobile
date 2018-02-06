/*
=
=	Referências: https://dev.mysql.com/doc/refman/5.7/en/create-table-foreign-keys.html
=
*/


/* --------------------------------------------------------------------------------------- */

/*
=
=	Mostrar os diferentes tipos de "join"
=
*/

DROP SCHEMA IF EXISTS senai_codexp_mobile;
CREATE SCHEMA senai_codexp_mobile;
USE senai_codexp_mobile;

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
insert into generos (nome) values ('Sem Valor');
select * from generos;

-- inserindo os valores dos filmes, uma vez que temos os valores dos generos
insert into filmes (nome, genero) values ('Rambo I', 1), ('Minions', 4), ('Rambo II', 1);
insert into filmes (nome) values ('Filme sem Gênero');
insert into filmes (nome, genero) values ('Romance', 2), ('Drama', 3), ('Romance A', 2);
select * from filmes;

/*
=
=	(INNER) JOIN
=
*/

select f.*, g.* from filmes f inner join generos g on f.genero = g.id;

/*
=
=	LEFT JOIN
=
*/

select f.*, g.* from filmes f left join generos g on f.genero = g.id;

/*
=
=	RIGHT JOIN
=
*/

select f.*, g.* from filmes f right join generos g on f.genero = g.id;

/*
=
=	FULL OUTER JOIN
=
*/

select f.*, g.* from filmes f left join generos g on f.genero = g.id
union all
select f.*, g.* from filmes f right join generos g on f.genero = g.id where f.id is null;