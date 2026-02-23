create table consultas(

    id bigint not null auto_increment,
    idMedico bigint not null,
    idPaciente bigint not null,
    data datetime not null,
    motivo varchar(1000) not null,

    primary key(id),
    constraint fk_consultas_medico_id foreign key (idMedico) references medicos(id),
    constraint fk_consultas_paciente_id foreign key (idPaciente) references pacientes(id)

);