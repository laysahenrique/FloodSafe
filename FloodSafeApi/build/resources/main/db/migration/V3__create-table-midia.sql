create table if not exists midia (
    id UUID primary key unique not null,
    usuario UUID not null,
    latitude numeric(18,18) not null,
    longitude numeric(18,18) not null,
    arquivo bytea not null,
    descricao varchar(255) not null,
    data_inclusao timestamptz not null
);