
insert into quartos (descricao, nota, capacidade, valor) values ('Suite simples', null, 1, 100.00);
insert into quartos (descricao, nota, capacidade, valor) values ('Suite com televisao, banheira e ar condicionado', null, 1, 200.00);
insert into quartos (descricao, nota, capacidade, valor) values ('Suite simples', null, 2, 150.00);
insert into quartos (descricao, nota, capacidade, valor) values ('Suite com televisao, banheira e ar condicionado', null, 2, 300.00);
insert into quartos (descricao, nota, capacidade, valor) values ('Suite simples', null, 5, 200.00);
insert into quartos (descricao, nota, capacidade, valor) values ('Suite com televisao, banheira e ar condicionado', null, 5, 400.00);
insert into quartos (descricao, nota, capacidade, valor) values ('Suite com televisao, banheira, ar condicionado e frigobar', null, 1, 250.00);
insert into quartos (descricao, nota, capacidade, valor) values ('Suite com televisao, banheira, ar condicionado e frigobar', null, 2, 350.00);
insert into quartos (descricao, nota, capacidade, valor) values ('Suite com televisao, banheira, ar condicionado e frigobar', null, 5, 450.00);
insert into quartos (descricao, nota, capacidade, valor) values ('Suite com televisao, banheira, ar condicionado,frigobar e café da manhã incluso', null, 5, 500.00);

insert into cliente (nome, email, password) values ('Ana A.', 'ana@a.a', 'ana123');
insert into cliente (nome, email, password) values ('Bob B.', 'bob@b.b', 'bob123');

insert into aluguel (entrada, saida, id_quarto, id_cliente) values ('01/08/2024', '02/08/2024', 1, 1);
insert into aluguel (entrada, saida, id_quarto, id_cliente) values ('11/08/2024', '12/08/2024', 1, 1);