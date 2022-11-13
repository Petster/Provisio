

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

import Database.DatabaseManager;

/**
 * Servlet implementation class ResetDatabase
 */
@WebServlet("/ResetDatabase")
public class ResetDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean worked = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			s.addBatch("DROP DATABASE IF EXISTS provisio");
			s.addBatch("CREATE DATABASE provisio");
			s.addBatch("DROP TABLE IF EXISTS provisio.users");
			s.addBatch("DROP TABLE IF EXISTS provisio.rooms");
			s.addBatch("DROP TABLE IF EXISTS provisio.reservations");
			s.addBatch("DROP TABLE IF EXISTS provisio.news");
			s.addBatch("DROP TABLES IF EXISTS provisio.locations");
			s.addBatch("DROP TABLE IF EXISTS provisio.emails");
			s.addBatch(
					"CREATE TABLE provisio.users (" +
					    "id bigint NOT NULL AUTO_INCREMENT," +
					    "email varchar(255)," +
					    "last_name varchar(255)," +
					    "first_name varchar(255)," +
					    "phone varchar(20)," +
					    "join_date date," +
					    "loyalty_points int," +
					    "is_admin boolean," +
					    "password varchar(255) NOT NULL," +
					    "PRIMARY KEY (id)," +
					    "UNIQUE (email)" +
					")"
				);
			s.addBatch(
					"CREATE TABLE provisio.rooms (" +
					    "id bigint NOT NULL AUTO_INCREMENT," +
					    "title varchar(255)," +
					    "breakfast boolean," +
					    "wifi boolean," +
					    "fitness boolean," +
					    "store boolean," +
					    "nosmoke boolean," +
					    "mobile boolean," +
					    "room_highlights varchar(255)," +
					    "image varchar(255)," +
					    "price decimal(10,2)," +
					    "PRIMARY KEY (id)" +
					")"
				);
			s.addBatch(
					"CREATE TABLE provisio.reservations (" +
					    "id bigint NOT NULL AUTO_INCREMENT," +
					    "userID bigint NOT NULL," +
					    "room_type bigint NOT NULL," +
					    "reserve_date date," +
					    "from_date date," +
					    "to_date date," +
					    "price decimal(10,2)," +
					    "PRIMARY KEY (id)," +
					    "FOREIGN KEY (userID) references users(id)," +
					    "FOREIGN KEY (room_type) references rooms(id)" +
					")"
				);
			s.addBatch(
					"CREATE TABLE provisio.news (" +
					    "id bigint NOT NULL AUTO_INCREMENT," +
					    "userID bigint NOT NULL," +
					    "title varchar(255)," +
					    "publish_date date," +
					    "description varchar(255)," +
					    "image varchar(255)," +
					    "PRIMARY KEY (id)," +
					    "FOREIGN KEY (userID) references users(id)" +
					")"
				);
			s.addBatch(
					"CREATE TABLE provisio.locations (" +
					    "id bigint NOT NULL AUTO_INCREMENT," +
					    "address varchar(255)," +
					    "title varchar(255)," +
					    "PRIMARY KEY (id)" +
					")"
				);
			s.addBatch(
					"CREATE TABLE provisio.emails (" +
					    "id bigint NOT NULL AUTO_INCREMENT," +
					    "userID bigint," +
					    "date_sent date," +
					    "reservation_num bigint," +
					    "user_email varchar(255)," +
					    "user_firstname varchar(255)," +
					    "subject varchar(255)," +
					    "message varchar(1000)," +
					    "PRIMARY KEY (id)," +
					    "FOREIGN KEY (userID) references users(id)," +
					    "FOREIGN KEY (reservation_num) references reservations(id)" +
					")"	
				);
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
				String message = "Could not Reset DB";
				response.sendError(500, message);
			}
		}
		
		doGet(request, response);
	}

}
