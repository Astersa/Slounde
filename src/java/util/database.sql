use master
go

create database SloundeDB
go

use SloundeDB

create table Subcription (
	SubId int primary key,
	SubType varchar(10)
);

create table Users (
	UserId int primary key,
	DoB date,
	[Name] nvarchar(20),
	Username nvarchar(20) unique,
	[Password] nvarchar(20),
	SubId int foreign key references Subcription(SubId)
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
