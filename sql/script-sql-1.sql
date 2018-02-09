-- Um estudante pode participar de muitos cursos, bem como um curso pode ter um ou mais estudantes;

/*
=
=		Criacao do banco de dados
=
*/
drop schema escola;
create schema escola;
use escola;

/*
=
=		Criacao da estrutura das tabelas
=
*/
create table estudantes (
    estudante_id int primary key auto_increment not null
    ,estudante_nome varchar(100) not null
);

create table cursos (
    curso_id int primary key auto_increment not null
    ,curso_nome varchar(300) not null
);

create table associacao (
    estudante_id int not null,
    curso_id int not null,
    foreign key (estudante_id) references estudantes (estudante_id) on delete restrict on update cascade,
    foreign key (curso_id) references cursos (curso_id) on delete restrict on update cascade,
    primary key (estudante_id, curso_id)
);

/*
=
=		Insercao dos valores relacionados
=
*/
-- inserindo 3 estudantes
insert into estudantes (estudante_nome) values ('Estudante A'), ('Estudante B'), ('Estudante C');

-- inserindo 2 cursos
insert into cursos (curso_nome) values ('Curso 1'), ('Curso 2');

-- inserindo o Estudante A no Curso 1
insert into associacao (estudante_id, curso_id) values (1,1);

-- inserindo o Estudante B no Curso 2
insert into associacao (estudante_id, curso_id) values (2,2);

-- inserindo o Estudante C no Curso 1 e 2
insert into associacao (estudante_id, curso_id) values (3,1),(3,2);

/*
=
=		Selecionando os dados das tabelas
=
*/

select e.*, c.* from estudantes e
	join associacao a
    on e.estudante_id = a.estudante_id
		join cursos c
        on a.curso_id = c.curso_id;