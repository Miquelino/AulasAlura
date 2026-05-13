create table clientes(

    id bigint not null auto_increment,
    numero int not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(6) not null unique,
    id_tipo_conta int not null unique,
    primary key(id))

);

create table tipoConta(

    id bigint not null auto_increment,
    String varchar(100) not null,
    primary key(id),
    FOREIGN KEY (id) REFERENCES clientes(id_tipo_conta))

);