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
                       PRIMARY KEY (id),
                       UNIQUE (email)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE locations (
                       id bigint NOT NULL AUTO_INCREMENT,
                       address varchar(255),
                       title varchar(255),
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
                       room_highlights varchar(255),
                       image varchar(255),
                       price decimal(10,2),
                       loyalty_points int,
                       PRIMARY KEY (id)
);

DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations (
                              id bigint NOT NULL AUTO_INCREMENT,
                              userID bigint NOT NULL,
                              room_type bigint NOT NULL,
                              location bigint NOT NULL,
                              guests bigint NOT NULL,
                              reserve_date date,
                              from_date date,
                              to_date date,
                              price decimal(10,2),
                              PRIMARY KEY (id),
                              FOREIGN KEY (userID) references users(id),
                              FOREIGN KEY (room_type) references rooms(id),
                              FOREIGN KEY (location) references locations(id)
);

DROP TABLE IF EXISTS news;
CREATE TABLE news (
                      id bigint NOT NULL AUTO_INCREMENT,
                      userID bigint NOT NULL,
                      title varchar(255),
                      publish_date date,
                      description varchar(255),
                      image varchar(255),
                      PRIMARY KEY (id),
                      FOREIGN KEY (userID) references users(id)
);

DROP TABLE IF EXISTS emails;
CREATE TABLE emails (
                        id bigint NOT NULL AUTO_INCREMENT,
                        userID bigint,
                        date_sent date,
                        reservation_num bigint,
                        user_email varchar(255),
                        user_firstname varchar(255),
                        subject varchar(255),
                        message varchar(1000),
                        PRIMARY KEY (id),
                        FOREIGN KEY (userID) references users(id),
                        FOREIGN KEY (reservation_num) references reservations(id)
);