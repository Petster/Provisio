package com.csd.beta.provisio;

import com.csd.beta.provisio.Database.DatabaseManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class DummyData
 */
@WebServlet("/DummyData")
public class DummyData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean worked = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DummyData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DatabaseManager dbm = new DatabaseManager();
		Connection conn = dbm.getConnection();
		Statement s = null;
		
		try {
			s = conn.createStatement();
			s.addBatch("INSERT INTO provisio.locations (id, address, title) values (1, '12 street st, nashua, nh', 'Nashua Hotel')");
			s.addBatch("INSERT INTO provisio.locations (id, address, title) values (2, '456 main street, columbus, oh', 'Main Street Hotel')");
			s.addBatch("INSERT INTO provisio.locations (id, address, title) values (3, '15 national blvd, lincoln, NE', 'Premier Hotel & Suites')");
			s.addBatch("INSERT INTO provisio.rooms (id, title, breakfast, wifi, fitness, store, nosmoke, mobile, room_highlights, image, price, loyalty_points) values (1, 'Single Bed', true, true, true, true, false, true, 'Single Bed', 'singlebed', 299.99, 5)");
			s.addBatch("INSERT INTO provisio.rooms (id, title, breakfast, wifi, fitness, store, nosmoke, mobile, room_highlights, image, price, loyalty_points) values (2, 'Suite with Queen Bed', true, true, true, true, true, true, 'Suite with Queen Bed', 'queenbed', 499.99, 10)");
			s.addBatch("INSERT INTO provisio.rooms (id, title, breakfast, wifi, fitness, store, nosmoke, mobile, room_highlights, image, price, loyalty_points) values (3, 'Double Bed', true, true, true, true, false, true, 'Double Bed', 'doublebed', 399.99, 15)");
			s.addBatch("INSERT INTO provisio.users (id, email, last_name, first_name, phone, join_date, loyalty_points, is_admin, password) values (1, 'jason@test.com', 'Palmeri', 'Jason', '123-456-7890', '2022-11-07', '99', true, 'testPassword')");
			s.addBatch("INSERT INTO provisio.users (id, email, last_name, first_name, phone, join_date, loyalty_points, is_admin, password) values (2, 'john@test.com', 'Moore', 'John', '123-456-7890', '2022-12-02', '39', false, 'testPassword')");
			s.addBatch("INSERT INTO provisio.users (id, email, last_name, first_name, phone, join_date, loyalty_points, is_admin, password) values (3, 'mishaela@test.com', 'Pedersen', 'Mishaela', '123-456-7890', '2022-11-22', '79', false, 'testPassword')");
			s.addBatch("INSERT INTO provisio.news (userID, title, publish_date, description, image) values (1, 'New Hotel', '2022-11-07', 'We have a new hotel coming in december', 'https://via.placeholder.com/150')");
			s.addBatch("INSERT INTO provisio.news (userID, title, publish_date, description, image) values (2, 'Test', '2022-12-07', 'We have a new hotel coming in december', 'https://via.placeholder.com/150')");
			s.addBatch("INSERT INTO provisio.news (userID, title, publish_date, description, image) values (1, 'Great Value', '2022-05-07', 'Deals on hotel rooms this summer!', 'https://via.placeholder.com/150')");
			s.addBatch("INSERT INTO provisio.reservations (id, userID, room_type, reserve_date, from_date, to_date, price, location, guests) values (1, 1, 1, '2022-11-07', '2022-12-01', '2022-12-08', 2099.93, 1, 2)");
			s.addBatch("INSERT INTO provisio.reservations (id, userID, room_type, reserve_date, from_date, to_date, price, location, guests) values (2, 2, 2, '2022-10-07', '2022-11-01', '2022-11-08', 3499., 1, 2)");
			s.addBatch("INSERT INTO provisio.reservations (id, userID, room_type, reserve_date, from_date, to_date, price, location, guests) values (3, 3, 3, '2022-11-07', '2023-01-01', '2023-01-08', 2799.93, 3, 4)");
			s.addBatch("INSERT INTO provisio.emails (userID, date_sent, reservation_num, user_email, user_firstname, subject, message) values (1, '2022-11-07', 1, 'jason@test.com', 'Jason', 'Test', 'Test')");
			s.addBatch("INSERT INTO provisio.emails (userID, date_sent, reservation_num, user_email, user_firstname, subject, message) values (2, '2022-12-07', 1, 'john@test.com', 'John', 'Test', 'Test')");
			s.addBatch("INSERT INTO provisio.emails (userID, date_sent, reservation_num, user_email, user_firstname, subject, message) values (3, '2022-05-07', 1, 'mishaela@test.com', 'Mishaela', 'Test', 'Test')");
			s.executeBatch();
			worked = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			HttpSession session = request.getSession();
			response.setContentType("text/plain");
			if(worked) {
				response.setStatus(200);
				session.invalidate();
			} else {
				String message = "Could not Create Data";
				response.sendError(500, message);
			}
		}
		
		doGet(request, response);
	}

}
