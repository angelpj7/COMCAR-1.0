use comcar;

create Table Usuarios (
	idUsuario int AUTO_INCREMENT,
    nombre VARCHAR(50) not null,
    apellidos VARCHAR(50) not null,
    dni VARCHAR(9) not null,
    telefono int not null,
    direccion VARCHAR(50) not null,
    usuario VARCHAR(32) not null,
    password VARCHAR(32) not null,
    admin boolean default false, 
    email VARCHAR(100),
    PRIMARY KEY (idUsuario)
);
-- Creamos por defecto un administrador en la base de datos --
INSERT into Usuarios (nombre, apellidos, dni, telefono, direccion, usuario, password, admin, email) 
VALUES 
("Administrador", "Administrador", "", 0, "", "admin", "21232f297a57a5a743894a0e4a801fc3", true, "");


create Table Coches (
	idCoche int AUTO_INCREMENT,
    matricula VARCHAR(50) not null,
    marca VARCHAR(50) not null,
    modelo VARCHAR(9) not null,
    anio int not null,
    kilometros int not null,
    combustible VARCHAR(32) not null,
    color VARCHAR(32) not null,
    PRIMARY KEY (idCoche)
);

create Table Anuncios (
	idAnuncio int AUTO_INCREMENT,
    titulo VARCHAR(50) not null,
    descripcion VARCHAR(50) not null,
	fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	precio int not null,
    idUsuario int not null,
    idCoche int not null,
    PRIMARY KEY (idAnuncio),
    foreign key(idUsuario) references Usuarios(idUsuario),
	foreign key(idCoche) references Coches(idCoche)
);