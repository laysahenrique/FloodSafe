create table usuario (
    id UUID primary key unique not null,
    username varchar(255),
    senha varchar(255)
);