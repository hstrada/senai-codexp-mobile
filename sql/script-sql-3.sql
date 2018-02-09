-- Um jogo pode possuir apenas um produtor. Um produtor de jogos pode estar vinculado a mais de um jogo;
create schema jogos;
use jogos;

/*
=
=		Criacao das tabelas
=
*/
create table produtores 
(
	id					int not null auto_increment primary key
    ,nome			varchar(150) not null
);

create table jogos 
(
	id 					int not null auto_increment primary key
    ,nome				varchar(200) not null
    ,id_produtor		int
    ,foreign key (id_produtor)
		references produtores (id) on delete restrict on update cascade
);

/*
=
=		Inserindo os valores das tabelas
=
*/
-- inserindo dois produtores
insert into produtores (nome) values ('Produtor A'), ('Produtor B');
-- inserindo Jogo 1 no Produtor A
insert into jogos (nome, id_produtor) values ('Jogo 1', 1);
-- inserindo Jogo 2 no Produtor A
insert into jogos (nome, id_produtor) values ('Jogo 2', 1);
-- inserindo Jogo 3 no Produtor B
insert into jogos (nome, id_produtor) values ('Jogo 3', 2);

/*
=
=		Selecionando os dados das tabelas
=
*/
select p.*, j.*
	from produtores p
    left join jogos j
    on p.id = j.id_produtor;

/*
=
=		Agrupando a quantidade de jogos que eu tenho por produtores
=
*/
select count(*), j.id_produtor, p.nome 
	from jogos j 
	inner join produtores p 
		on j.id_produtor = p.id 
        group by j.id_produtor;