create table usuario (
        id bigint not null auto_increment,
        apellido varchar(255),
        mail varchar(255),
        nombre varchar(255),
        numero_telefono varchar(255),
        primary key (id)
    ) 