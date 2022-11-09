package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import Database.DatabaseManager;

public class DatabaseManager {

	private final String dbURL = "jdbc:mysql://localhost:3306";
	private final String dbName = "root";
	private final String dbPass = "root";
	private final String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		loadDriver(dbDriver);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbURL, dbName, dbPass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/* needs to be worked on */
	public String createUser(User user) {
		loadDriver(dbDriver);
		Connection conn = getConnection();
		
		String sql = "INSERT INTO `provisio`.`users` (email, lastName, firstName, phone, joinDate, password, loyaltyPoints, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String message = "";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  user.getEmail());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getFirstname());
			ps.setString(4,  user.getPhone());
			ps.setString(5, user.getJoindate());
			ps.setString(6,  user.getPassword());
			ps.setInt(7, user.getLoyaltyPoints());
			ps.setBoolean(8, user.getAdmin());
			int rowsAffected = ps.executeUpdate();
			
		} catch(SQLIntegrityConstraintViolationException e) {
			System.out.println(e);
			message = "Failed to create user: Email already has an account!";
			return message;
		} catch (SQLException e) {
			System.out.println("N" + e);
		}
		message = "User creation successful";
		return message;
	}
	
	public User loginUser(String username, String password) {
		loadDriver(dbDriver);
		Connection conn = getConnection();
		String sql = "SELECT * FROM `provisio`.`users` WHERE email='"+username+"' AND password='"+password+"'";
		User loggedIn = new User();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				String testPassword = rs.getString(9).trim();
				if(testPassword.equals(password.trim())) {
					loggedIn.setId(rs.getInt(1));
					loggedIn.setEmail(rs.getString(2));
					loggedIn.setLastname(rs.getString(3));
					loggedIn.setFirstname(rs.getString(4));
					loggedIn.setPhone(rs.getString(5));
					loggedIn.setJoindate(rs.getString(6));
					loggedIn.setLoyaltyPoints(rs.getInt(7));
					loggedIn.setAdmin(rs.getBoolean(8));
					loggedIn.setPassword(rs.getString(9));
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loggedIn;		
	}
}
