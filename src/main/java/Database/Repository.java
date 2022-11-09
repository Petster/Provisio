package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * @param <T> The entity that it controls the resource
 */
public interface Repository<T> {
	default Connection establishConnection() {
		final String url = "jdbc:mysql://localhost:3306/provisio?";
		final String credentials = "user=clerk&password=password";
		Connection c = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url + credentials);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
		return c;
	}

	T insertOne(T t);

	List<T> insertMany(List<T> tList);

	T getById(long id);

	List<T> getAll();

	void updateById(T t, long id);

	void deleteById(long id);
}
