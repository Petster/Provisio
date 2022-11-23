USE provisio;

INSERT INTO locations (id, address, title) values (1, '12 street st, nashua, nh', 'Nashua Hotel');

INSERT INTO locations (id, address, title) values (2, '456 main street, columbus, oh', 'Main Street Hotel');

INSERT INTO locations (id, address, title) values (3, '15 national blvd, lincoln, NE', 'Premier Hotel & Suites');

INSERT INTO rooms (id, title, breakfast, wifi, fitness, store, nosmoke, mobile, room_highlights, image, price, loyalty_points) values (1, 'Single Bed', true, true, true, true, false, true, 'Single Bed', 'singlebed', 299.99, 5);

INSERT INTO rooms (id, title, breakfast, wifi, fitness, store, nosmoke, mobile, room_highlights, image, price, loyalty_points) values (2, 'Suite with Queen Bed', true, true, true, true, true, true, 'Suite with Queen Bed', 'queenbed', 499.99, 10);

INSERT INTO rooms (id, title, breakfast, wifi, fitness, store, nosmoke, mobile, room_highlights, image, price, loyalty_points) values (3, 'Double Bed', true, true, true, true, false, true, 'Double Bed', 'doublebed', 399.99, 15);

INSERT INTO users (id, email, last_name, first_name, phone, join_date, loyalty_points, is_admin, password) values (1, 'jason@test.com', 'Palmeri', 'Jason', '123-456-7890', '2022-11-07', '99', true, 'testPassword');

INSERT INTO users (id, email, last_name, first_name, phone, join_date, loyalty_points, is_admin, password) values (2, 'john@test.com', 'Moore', 'John', '123-456-7890', '2022-12-02', '39', false, 'testPassword');

INSERT INTO users (id, email, last_name, first_name, phone, join_date, loyalty_points, is_admin, password) values (3, 'mishaela@test.com', 'Pedersen', 'Mishaela', '123-456-7890', '2022-11-22', '79', false, 'testPassword');

INSERT INTO news (userID, title, publish_date, description, image) values (1, 'New Hotel', '2022-11-07', 'We have a new hotel coming in december', 'https://via.placeholder.com/150');

INSERT INTO news (userID, title, publish_date, description, image) values (2, 'Test', '2022-12-07', 'We have a new hotel coming in december', 'https://via.placeholder.com/150');

INSERT INTO news (userID, title, publish_date, description, image) values (1, 'Great Value', '2022-05-07', 'Deals on hotel rooms this summer!', 'https://via.placeholder.com/150');

INSERT INTO reservations (userID, room_type, reserve_date, from_date, to_date, price, location, guests) values (1, 1, '2022-11-07', '2022-12-01', '2022-12-08', 2099.93, 1, 2);

INSERT INTO reservations (userID, room_type, reserve_date, from_date, to_date, price, location, guests) values (2, 2, '2022-10-07', '2022-11-01', '2022-11-08', 3499.93, 1, 2);

INSERT INTO reservations (userID, room_type, reserve_date, from_date, to_date, price, location, guests) values (3, 3, '2022-11-07', '2023-01-01', '2023-01-08', 2799.93, 3, 4);

INSERT INTO emails (userID, date_sent, reservation_num, user_email, user_firstname, subject, message) values (1, '2022-11-07', 1, 'jason@test.com', 'Jason', 'Test', 'Test');

INSERT INTO emails (userID, date_sent, reservation_num, user_email, user_firstname, subject, message) values (2, '2022-12-07', 1, 'john@test.com', 'John', 'Test', 'Test');

INSERT INTO emails (userID, date_sent, reservation_num, user_email, user_firstname, subject, message) values (3, '2022-05-07', 1, 'mishaela@test.com', 'Mishaela', 'Test', 'Test');

SELECT * FROM users;
SELECT * FROM reservations;
SELECT * FROM locations;
SELECT * FROM news;
SELECT * FROM rooms;