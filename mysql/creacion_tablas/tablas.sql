CREATE TABLE receta (
	id int(4) auto_increment,
	Nombre varchar(40) not null,
	Descripcion text,
	Plato varchar(10) not null,
	numeroPersonas int(2) not null,
	Primary Key (id)
);

CREATE TABLE ingrediente (
	Nombre varchar(20),
	TipoIngrediente varchar(15) not null,
	Primary Key (nombre)
);

CREATE TABLE comentarios (
	idReceta int(4),
	idComentario int(4) auto_increment,
	comentario varchar(140) not null,
	Foreign Key(idReceta) REFERENCES id (receta) ON DELETE CASCADE,
	Primary Key (idReceta, idComentario)
);

CREATE TABLE puntuacion (
	idReceta int(4),
	ip varchar(30) not null,
	puntos int(2),
	Foreign Key(idReceta) REFERENCES id (receta) ON DELETE CASCADE,
	Primary Key(ip, idReceta)
);

CREATE TABLE componente (
	idReceta int(4),
	ingrediente varchar(20),
	esPrincipal int(1),
	peso int(4) not null,
	Foreign Key(idReceta) REFERENCES id (receta) ON DELETE CASCADE,
	Foreign Key(ingrediente) REFERENCES Nombre (ingrediente) ON DELETE CASCADE,
	Primary Key (idReceta, ingrediente)
);


