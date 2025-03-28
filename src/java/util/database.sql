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
	AvatarUrl NVARCHAR(max),
	Mail NVARCHAR(max),
	Role INT,
    SubId INT,
    CONSTRAINT FK_Users_Subcription FOREIGN KEY (SubId) REFERENCES Subcription(Id) 
);

create table Genre (
	Id int IDENTITY(1,1) primary key,
	[Name] nvarchar(10) 
);

CREATE TABLE Artists (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    [Name] NVARCHAR(50), 
    Followers INT DEFAULT 0,
    ArtistUrl NVARCHAR(max) 
);

create table Albums (
	Id int IDENTITY(1,1) primary key,
	[Name] nvarchar(100),
                  AlbumUrl NVARCHAR(max),
                  Likes int,
	ArtistId int foreign key references Artists(Id)
);

create table Songs (
	Id int IDENTITY(1,1) primary key,
	[Name] nvarchar(100),
	[Streams] int,
	Likes int,
	SongUrl varchar(max),
	ThumbnailUrl varchar(max),
	ArtistId int foreign key references Artists(Id),
                  AlbumId int foreign key references Albums(Id)
);

create table SongGenre (
    Id int IDENTITY(1,1) primary key,
	SongId int foreign key references Songs(Id),
	GenreId int foreign key references Genre(Id),
	CONSTRAINT UQ_SongGenre UNIQUE (SongId, GenreId)
);

CREATE TABLE UserLikesSongs (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    SongId INT FOREIGN KEY REFERENCES Songs(Id),
    created_at DATETIME DEFAULT GETDATE(),
    CONSTRAINT UQ_UserLikes UNIQUE (UserId, SongId)
);

CREATE TABLE UserFollowsArtists (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    ArtistId INT FOREIGN KEY REFERENCES Artists(Id),
    created_at DATETIME DEFAULT GETDATE(),
    CONSTRAINT UQ_UserFollowsArtist UNIQUE (UserId, ArtistId)
);

CREATE TABLE UserLikesAlbum (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT,
    AlbumId INT,
    LikeDate DATETIME DEFAULT GETDATE(),
    created_at DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_UserLikesAlbum_User FOREIGN KEY (UserId) REFERENCES Users(Id),
    CONSTRAINT FK_UserLikesAlbum_Album FOREIGN KEY (AlbumId) REFERENCES Albums(Id),
    CONSTRAINT UQ_UserLikesAlbum UNIQUE (UserId, AlbumId)
);
