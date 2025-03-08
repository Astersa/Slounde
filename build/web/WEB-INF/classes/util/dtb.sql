use master
go

create database SloundeDB
go

use SloundeDB

create table Subcription (
	SubId int primary key,
	SubType varchar(10)
);

CREATE TABLE Users (
    UserId INT IDENTITY(1,1) PRIMARY KEY,  
    DoB DATE,
    Name NVARCHAR(20),  
    Username NVARCHAR(20),
    Password NVARCHAR(20),  
    SubId INT,
    CONSTRAINT FK_Users_Subcription FOREIGN KEY (SubId) REFERENCES Subcription(SubId) 
);

create table Genre (
	GenreId int primary key,
	[Name] nvarchar(10) 
);

create table Artists (
	ArtistId int primary key,
	[Name] nvarchar(20),
	Followers int
);

create table Songs (
	SongId int primary key,
	[Name] nvarchar(100),
	[Streams] int,
	Likes int,
	SongUrl varchar(max),
	ThumbnailUrl varchar(max),
	ArtistId int foreign key references Artists(ArtistId)
);

create table Albums (
	AlbumId int primary key,
	[Name] nvarchar(100),
	ArtistId int foreign key references Artists(ArtistId)
);

create table SongGenre (
	SongId int foreign key references Songs(SongId),
	GenreId int foreign key references Genre(GenreId),
	primary key (SongId, GenreId)
);

create table UserLikes (
	UserId int foreign key references Users(UserId),
	SongId int foreign key references Songs(SongId),
	primary key (UserId, SongId)
);
