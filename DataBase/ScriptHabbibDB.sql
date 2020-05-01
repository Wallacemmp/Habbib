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
    UF ENUM('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO')
);

CREATE TABLE Patient(
    Id INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(60) NOT NULL,
    LastName VARCHAR(60) NOT NULL,
    CPF VARCHAR(19) NOT NULL,
    DOB DATE NOT NULL,
    Gender ENUM('Masculino','Feminino'),
    CID VARCHAR(10)
);

CREATE TABLE Bed(
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Type ENUM('UTI','Semi-intensivo','Baixa complexidade'),
    Status ENUM('Disponivel','Ocupado') DEFAULT 'Disponivel',
    Id_Institution INT
);

CREATE TABLE Requisition(
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Status ENUM('Aprovado','Em análise','Reprovado') DEFAULT 'Em análise',
    Description TEXT NOT NULL,
    Id_Bed INT,
    Id_Patient INT,
    FOREIGN KEY(Id_Bed) REFERENCES Bed(Id),
    FOREIGN KEY(Id_Patient) REFERENCES Patient(Id)
);

CREATE TABLE Institution (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(60) NOT NULL,
    CNPJ VARCHAR(18) NOT NULL,
    Password VARCHAR(20) NOT NULL,
    Type ENUM('Privado','Particular') NOT NULL,
    ContactNumber VARCHAR(15) NOT NULL,
    Id_Address INT,
    Id_Requisition INT,
    FOREIGN KEY(Id_Address) REFERENCES Address(Id),
    FOREIGN KEY(Id_Requisition) REFERENCES Requisition(Id)
);

ALTER TABLE Bed ADD FOREIGN KEY(Id_Institution) REFERENCES Institution(Id);






















