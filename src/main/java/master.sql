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

DROP TABLES IF EXISTS locations;
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

INSERT INTO locations (address, title) values ('12 street st, nashua, nh', 'Nashua Hotel');

INSERT INTO locations (address, title) values ('456 main street, columbus, oh', 'Main Street Hotel');

INSERT INTO locations (address, title) values ('15 national blvd, lincoln, NE', 'Premier Hotel & Suites');

INSERT INTO rooms (id, title, breakfast, wifi, fitness, store, nosmoke, mobile, roomHighlights, image, price) values (1, 'Single Bed', true, true, true, true, false, true, 'Single Bed', '/images/rooms/single.jpg', 299.99);

INSERT INTO rooms (id, title, breakfast, wifi, fitness, store, nosmoke, mobile, roomHighlights, image, price) values (2, 'Suite with Queen Bed', true, true, true, true, true, true, 'Suite with Queen Bed', '/images/rooms/suite1.jpg', 499.99);

INSERT INTO rooms (id, title, breakfast, wifi, fitness, store, nosmoke, mobile, roomHighlights, image, price) values (3, 'Double Bed', true, true, true, true, false, true, 'Double Bed', '/images/rooms/double.jpg', 399.99);

INSERT INTO users (id, email, lastName, firstName, phone, joinDate, loyaltyPoints, isAdmin, password) values (1, 'jason@test.com', 'Palmeri', 'Jason', '123-456-7890', '2022-11-07', '99', true, 'testPassword');

INSERT INTO users (id, email, lastName, firstName, phone, joinDate, loyaltyPoints, isAdmin, password) values (2, 'john@test.com', 'Moore', 'John', '123-456-7890', '2022-12-02', '39', false, 'testPassword');

INSERT INTO users (id, email, lastName, firstName, phone, joinDate, loyaltyPoints, isAdmin, password) values (3, 'mishaela@test.com', 'Pedersen', 'Mishaela', '123-456-7890', '2022-11-22', '79', false, 'testPassword');

INSERT INTO news (userID, title, publishDate, description, image) values (1, 'New Hotel', '2022-11-07', 'We have a new hotel coming in december', '/images/newhotel.jpg');

INSERT INTO news (userID, title, publishDate, description, image) values (2, 'Test', '2022-12-07', 'We have a new hotel coming in december', '/images/newhotel.jpg');

INSERT INTO news (userID, title, publishDate, description, image) values (1, 'Great Value', '2022-05-07', 'Deals on hotel rooms this summer!', '/images/deals.jpg');

INSERT INTO reservations (userID, roomType, reserveDate, fromDate, toDate, price) values (1, 1, '2022-11-07', '2022-12-01', '2022-12-08', 2099.93);

INSERT INTO reservations (userID, roomType, reserveDate, fromDate, toDate, price) values (2, 2, '2022-10-07', '2022-11-01', '2022-11-08', 3499.93);

INSERT INTO reservations (userID, roomType, reserveDate, fromDate, toDate, price) values (3, 3, '2022-11-07', '2023-01-01', '2023-01-08', 2799.93);

INSERT INTO emails (userID, dateSent, reservationNum, userEmail, userFirstName, subject, message) values (1, '2022-11-07', 1, 'jason@test.com', 'Jason', 'Test', 'Test');

INSERT INTO emails (userID, dateSent, reservationNum, userEmail, userFirstName, subject, message) values (2, '2022-12-07', 1, 'john@test.com', 'John', 'Test', 'Test');

INSERT INTO emails (userID, dateSent, reservationNum, userEmail, userFirstName, subject, message) values (3, '2022-05-07', 1, 'mishaela@test.com', 'Mishaela', 'Test', 'Test');

SELECT * FROM users;
SELECT * FROM reservations;
SELECT * FROM locations;
SELECT * FROM news;
SELECT * FROM rooms;