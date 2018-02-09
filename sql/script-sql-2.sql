-- Uma pessoa está vinculada a 1 ou mais carros. Um carro só está vinculado a uma pessoa;
create schema estacionamento;
use estacionamento;

/*
=
=		Criacao das tabelas
=
*/
create table pessoas
(
	id 			int not null auto_increment primary key
    ,nome		varchar(150) not null 
);

create table carros
(
	id					int not null auto_increment primary key
    ,nome			varchar(40) not null
    ,id_pessoa	int
    ,foreign key (id_pessoa) references pessoas (id) on delete set null on update cascade
);

/*
=
=		Insercao dos valores das tabelas
=
*/
-- inserindo 2 pessoas
insert into pessoas (nome) values ('Pessoa A'), ('Pessoa B');
-- inserindo o Carro 1 que eh da Pessoa A
insert into carros (nome, id_pessoa) values ('Carro 1', 1);
-- inserindo o Carro 2 que eh da Pessoa A
insert into carros (nome, id_pessoa) values ('Carro 2', 1);
-- inserindo o Carro 3 que eh da Pessoa B
insert into carros (nome, id_pessoa) values ('Carro 3',2);

/*
=
=		Selecionando os dados das tabelas relacionados
=
*/
select p.*, c.*
	from pessoas p
    left join carros c
    on p.id = c.id_pessoa;
    
    
    
    
    
    