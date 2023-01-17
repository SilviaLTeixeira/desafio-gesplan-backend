CREATE TABLE telefones (
    id bigserial primary key,
    id_contato bigint REFERENCES contatos(id),
    telefone char(11)
)