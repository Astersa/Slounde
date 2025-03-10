use master
go

create database SloundeDB
go

use SloundeDB

create table Subcription (
	Id int IDENTITY(1,1) primary key,
	SubType varchar(10)
);

CREATE TABLE Users (
    Id INT IDENTITY(1,1) PRIMARY KEY,  
    DoB DATE,
    Name NVARCHAR(20),  
    Username NVARCHAR(20),
    Password NVARCHAR(20),  
    SubId INT,
    CONSTRAINT FK_Users_Subcription FOREIGN KEY (SubId) REFERENCES Subcription(Id) 
);

create table Genre (
	Id int IDENTITY(1,1) primary key,
	[Name] nvarchar(10) 
);

create table Artists (
	Id int  IDENTITY(1,1) primary key,
	[Name] nvarchar(20),
	Followers int
);

create table Songs (
	Id int IDENTITY(1,1) primary key,
	[Name] nvarchar(100),
	[Streams] int,
	Likes int,
	SongUrl varchar(max),
	ThumbnailUrl varchar(max),
	ArtistId int foreign key references Artists(Id)
);

create table Albums (
	Id int IDENTITY(1,1) primary key,
	[Name] nvarchar(100),
	ArtistId int foreign key references Artists(Id)
);

create table SongGenre (
    Id int IDENTITY(1,1) primary key,
	SongId int foreign key references Songs(Id),
	GenreId int foreign key references Genre(Id),
	CONSTRAINT UQ_SongGenre UNIQUE (SongId, GenreId)
);

create table UserLikes (
Id int IDENTITY(1,1) primary key,
	UserId int foreign key references Users(Id),
	SongId int foreign key references Songs(Id),
	CONSTRAINT UQ_UserLikes UNIQUE (UserId, SongId)
);
