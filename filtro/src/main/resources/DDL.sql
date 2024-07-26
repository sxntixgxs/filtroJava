DROP SCHEMA IF EXISTS sgpzf;
CREATE SCHEMA sgpzf;
USE sgpzf;

CREATE TABLE stack(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE skill(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE stack_skill(
    idskill INT NOT NULL,
    idstack INT NOT NULL,
    idstatus INT NOT NULL,
    PRIMARY KEY (idskill,idstack),
    FOREIGN KEY (idskill) REFERENCES skill(id) ON DELETE CASCADE,
    FOREIGN KEY (idstack) REFERENCES stack(id) ON DELETE CASCADE
);

CREATE TABLE country(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);
CREATE TABLE region(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    idcountry INT NOT NULL,
    FOREIGN KEY (idcountry) REFERENCES country(id) ON DELETE CASCADE
);

CREATE TABLE city(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    idregion INT NOT NULL,
    FOREIGN KEY (idregion) REFERENCES region(id) ON DELETE CASCADE
);
CREATE TABLE gender(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE persons(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    idcity INT NOT NULL,
    address VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    idgender INT NOT NULL,
    FOREIGN KEY (idcity) REFERENCES city(id) ON DELETE CASCADE,
    FOREIGN KEY (idgender) REFERENCES gender(id) ON DELETE CASCADE
);
CREATE TABLE persons_skill(
    id INT PRIMARY KEY AUTO_INCREMENT,
    registration_date DATE,
    iperson INT NOT NULL, 
    idskill INT NOT NULL,
    FOREIGN KEY (iperson) REFERENCES persons(id) ON DELETE CASCADE,
    FOREIGN KEY (idskill) REFERENCES skill(id) ON DELETE CASCADE
);
-- es idperson pero en el diagrama está así escrito
