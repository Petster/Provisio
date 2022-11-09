DROP DATABASE IF EXISTS provisio;
CREATE DATABASE provisio;
USE provisio;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id int NOT NULL AUTO_INCREMENT,
    email varchar(255),
    lastName varchar(255),
    firstName varchar(255),
    phone varchar(20),
    joinDate date,
    loyaltyPoints int,
    isAdmin boolean,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS rooms;
CREATE TABLE rooms (
    id int NOT NULL AUTO_INCREMENT,
    title varchar(255),
    breakfast boolean,
    wifi boolean,
    fitness boolean,
    store boolean,
    nosmoke boolean,
    mobile boolean,
    roomHighlights varchar(255),
    image varchar(255),
    price int,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations (
    id int NOT NULL AUTO_INCREMENT,
    userID int NOT NULL,
    roomType int NOT NULL,
    reserveDate date,
    fromDate date,
    toDate date,
    price int,
    PRIMARY KEY (id),
    FOREIGN KEY (userID) references users(id),
    FOREIGN KEY (roomType) references rooms(id)
);

DROP TABLE IF EXISTS news;
CREATE TABLE news (
    id int NOT NULL AUTO_INCREMENT,
    userID int NOT NULL,
    title varchar(255),
    publishDate date,
    description varchar(255),
    image varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (userID) references users(id)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE locations (
    id int NOT NULL AUTO_INCREMENT,
    address varchar(255),
    title varchar(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS emails;
CREATE TABLE emails (
    id int NOT NULL AUTO_INCREMENT,
    userID int,
    dateSent date,
    reservationNum int,
    userEmail varchar(255),
    userFirstName varchar(255),
    subject varchar(255),
    message varchar(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (userID) references users(id),
    FOREIGN KEY (reservationNum) references reservations(id)
);
