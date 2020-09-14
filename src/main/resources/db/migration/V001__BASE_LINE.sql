
create table if not exists perfil
(
    id               bigserial    not null
        constraint perfil_pkey
            primary key,
    data_atualizacao timestamp,
    data_criacao     timestamp    not null,
    nome             varchar(255) not null,
    situacao         varchar(255) not null
);



create table if not exists usuario
(
    id                   bigserial    not null
        constraint usuario_pkey
            primary key,
    data_atualizacao     timestamp,
    data_criacao         timestamp    not null,
    cpf                  varchar(255) not null,
    email                varchar(255),
    login                varchar(255) not null,
    nome                 varchar(255) not null,
    permissoes_especiais varchar(255),
    receber_notificacao  varchar(255),
    senha                varchar(255),
    sexo                 varchar(255),
    situacao             varchar(255),
    telefone             varchar(255),
    perfil_id            bigint       not null
        constraint fk_usuario_perfil
            references perfil
);
