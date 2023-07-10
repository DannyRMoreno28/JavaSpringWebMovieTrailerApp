USE [master]
GO
CREATE DATABASE [organizador_peliculas]
    CONTAINMENT = NONE
    ON  PRIMARY 
( NAME = N'organizador_peliculas', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\organizador_peliculas.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
    LOG ON 
( NAME = N'organizador_peliculas_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\organizador_peliculas_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
    WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO

USE [organizador_peliculas]
GO

CREATE TABLE [dbo].[genero](
	[id_genero] [int] NOT NULL,
	[titulo] [varchar](255) NULL,
	PRIMARY KEY CLUSTERED 
	(
		[id_genero] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[genero_pelicula](
	[id_pelicula] [int] NOT NULL,
	[id_genero] [int] NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[pelicula](
	[id_pelicula] [int] IDENTITY(1,1) NOT NULL,
	[fecha_estreno] [date] NULL,
	[ruta_portada] [varchar](255) NULL,
	[sinopsis] [varchar](500) NULL,
	[titulo] [varchar](255) NULL,
	[youtube_trailer_id] [varchar](255) NULL,
	PRIMARY KEY CLUSTERED 
	(
		[id_pelicula] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[genero_pelicula]  WITH CHECK ADD  CONSTRAINT [FK7rxwafj1id7f40i0mq446ivw6] FOREIGN KEY([id_pelicula])
REFERENCES [dbo].[pelicula] ([id_pelicula])
GO

ALTER TABLE [dbo].[genero_pelicula] CHECK CONSTRAINT [FK7rxwafj1id7f40i0mq446ivw6]
GO

ALTER TABLE [dbo].[genero_pelicula]  WITH CHECK ADD  CONSTRAINT [FKnpb8vdeybu4p41dibnbawra2q] FOREIGN KEY([id_genero])
REFERENCES [dbo].[genero] ([id_genero])
GO

ALTER TABLE [dbo].[genero_pelicula] CHECK CONSTRAINT [FKnpb8vdeybu4p41dibnbawra2q]
GO

ALTER DATABASE [organizador_peliculas] SET  READ_WRITE 
GO