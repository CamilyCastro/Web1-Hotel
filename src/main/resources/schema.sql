DROP TABLE IF EXISTS aluguel;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS quartos;

create table quartos (
        id int not null auto_increment,
        descricao varchar(100) not null,
        nota int,
        capacidade int not null,
        valor decimal(8, 2),
        primary key (id)
);

create table cliente (
	id int not null auto_increment,
	nome varchar(40) not null,
	email varchar(40) not null,
	password varchar(40) not null,
	primary key (id)
);

create table aluguel (
	id int not null auto_increment,
	entrada varchar(15) not null,
	saida varchar(15) not null,
	id_quarto int not null references quartos(id),
	primary key (id)
);

