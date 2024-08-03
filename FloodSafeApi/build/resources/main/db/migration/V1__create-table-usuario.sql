create table usuario (
    id UUID primary key unique not null,
    login varchar(255),
    senha varchar(255)
);