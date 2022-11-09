DROP DATABASE IF EXISTS provisio;
CREATE DATABASE provisio;
USE provisio;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id bigint NOT NULL AUTO_INCREMENT,
    email varchar(255),
    last_name varchar(255),
    first_name varchar(255),
    phone varchar(20),
    join_date date,
    loyalty_points int,
    is_admin boolean,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS rooms;
CREATE TABLE rooms (
    id bigint NOT NULL AUTO_INCREMENT,
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
    id bigint NOT NULL AUTO_INCREMENT,
    userID bigint NOT NULL,
    roomType bigint NOT NULL,
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
    id bigint NOT NULL AUTO_INCREMENT,
    userID bigint NOT NULL,
    title varchar(255),
    publishDate date,
    description varchar(255),
    image varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (userID) references users(id)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE locations (
    id bigint NOT NULL AUTO_INCREMENT,
    address varchar(255),
    title varchar(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS emails;
CREATE TABLE emails (
    id bigint NOT NULL AUTO_INCREMENT,
    userID bigint,
    dateSent date,
    reservationNum bigint,
    userEmail varchar(255),
    userFirstName varchar(255),
    subject varchar(255),
    message varchar(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (userID) references users(id),
    FOREIGN KEY (reservationNum) references reservations(id)
);
