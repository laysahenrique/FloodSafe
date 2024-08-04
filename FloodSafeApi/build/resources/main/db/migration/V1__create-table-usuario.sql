create table usuario (
    id UUID primary key unique not null,
    email varchar(255),
    senha varchar(255)
);