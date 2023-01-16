CREATE TABLE contatos (
    id bigserial primary key,
    nome varchar(100),
    sobrenome varchar(255),
    email varchar(150),
    observacao text
)