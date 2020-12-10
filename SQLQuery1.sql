create table Casa(
	codigo int primary key 
	, descripcion varchar(100)
	, direccion varchar(100)
)

go

create table Residente(
	cedula varchar(16) primary key 
	, nombres varchar(30) not null
	, apellidos varchar(30) not null
	, dependeientes int
	, casa int foreign key references Casa(codigo)
)
go

create table Usuario(
	cedula varchar(16) primary key 
	, nombres varchar(30) not null
	, apellidos varchar(30) not null
	, carnet varchar(30) not null unique
	, pw varchar(100) not null
)
go

create table Visitante(
	cedula varchar(16) primary key 
	, nombres varchar(30) not null
	, apellidos varchar(30) not null
	, betado bit default 0
)

go

create table Visita (
	id int primary key identity(1, 1)
	, usuario varchar(16) foreign key references Usuario(cedula)
	, casa int foreign key references  Casa(Codigo)
	, Visitante varchar(16) foreign key references Visitante(Cedula)
	, fechaHora datetime default getdate()
	, razonVisita varchar(100) 
	, transporte varchar(100)
	, matricula varchar(30)
)

