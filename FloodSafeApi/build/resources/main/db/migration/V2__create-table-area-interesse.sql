create table if not exists area_interesse (
    id UUID primary key unique not null,
    usuario UUID not null,
    latitude numeric(18,18) not null,
    longitude numeric(18,18) not null,
    emite_alerta_email boolean not null default false
);

ALTER TABLE public.usuario ADD if not exists nome varchar(255) NOT NULL;
ALTER TABLE public.usuario ALTER COLUMN email SET NOT NULL;
ALTER TABLE public.usuario ALTER COLUMN senha SET NOT NULL;
