package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Database.DatabaseManager;

public class DatabaseManager {

	private String dbURL = "jdbc:mysql://localhost:3306";
	private String dbName = "root";
	private String dbPass = "root";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	
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
	
}
