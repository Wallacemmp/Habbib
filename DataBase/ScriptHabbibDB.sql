CREATE DATABASE db_Habbib;

USE db_Habbib;

CREATE TABLE Address(
	Id INT PRIMARY KEY AUTO_INCREMENT,
    ZipCode VARCHAR(9) NOT NULL,
    Address VARCHAR(60) NOT NULL,
    AddressNumber INT NOT NULL,
    Complement VARCHAR(10),
    Neighborhood VARCHAR(60) NOT NULL,
    City VARCHAR(30) NOT NULL,
    UF ENUM('SP','RJ','SC','MG')
);

CREATE TABLE Institution (
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(60) NOT NULL,
    CNPJ VARCHAR(18) NOT NULL,
    Password VARCHAR(20) NOT NULL,
    Type ENUM('Privado','Particular') NOT NULL,
    ContactNumber VARCHAR(15) NOT NULL,
    Id_Address INT,
    FOREIGN KEY(Id_Address) REFERENCES Address(Id)
);

CREATE TABLE Bed(
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Type ENUM('UTI','Semi-intensivo','Baixa complexidade'),
	Status ENUM('Disponivel','Ocupado'),
    Id_Institution INT,
    FOREIGN KEY(Id_Institution) REFERENCES Institution(Id)
);

CREATE TABLE Request(
	Id INT PRIMARY KEY AUTO_INCREMENT,
    Status ENUM('Aprovado','Em análise','Reprovado'),
	Description TEXT NOT NULL,
    Id_Bed INT,
    FOREIGN KEY(Id_Bed) REFERENCES Bed(Id)
);

CREATE TABLE Patient(
	Id INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(60) NOT NULL,
    LastName VARCHAR(60) NOT NULL,
    CPF VARCHAR(19) NOT NULL,
    DOB DATE NOT NULL,
    Gender ENUM('Masculino','Feminino'),
    CID VARCHAR(10),
    Id_Institution INT,
    Id_Request INT,
	FOREIGN KEY(Id_Institution) REFERENCES Institution(Id),
    FOREIGN KEY(Id_Request) REFERENCES Request(Id)
);











