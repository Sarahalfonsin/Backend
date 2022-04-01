
create table IF NOT EXISTS domicilios(id BIGINT auto_increment primary key,calle varchar(255),numero int,localidad varchar (255),provincia varchar (255));
create table IF NOT EXISTS pacientes(id BIGINT auto_increment primary key,apellido varchar(255),nombre varchar (255),email varchar(255),dni int,fechaIngreso VARCHAR(255),domicilio_id BIGINT,odontologo_id BIGINT);
create table IF NOT EXISTS odontologos(id BIGINT auto_increment primary key,numeroMatricula int,nombre varchar(255),apellido varchar (255));
