package com.csd.beta.provisio.repo;

import com.csd.beta.provisio.entity.User;
import com.csd.beta.provisio.exception.ProvisioException;
import com.csd.beta.provisio.util.Encryption;
import com.csd.beta.provisio.util.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {
	public final String COLUMN_USER_ID = "id";
	public final String COLUMN_USER_EMAIL = "email";
	public final String COLUMN_USER_LASTNAME = "last_name";
	public final String COLUMN_USER_FIRSTNAME = "first_name";
	public final String COLUMN_USER_PHONE = "phone";
	public final String COLUMN_USER_JOIN_DATE = "join_date";
	public final String COLUMN_USER_LOYALTY_POINTS = "loyalty_points";
	public final String COLUMN_USER_IS_ADMIN = "is_admin";
	public final String COLUMN_USER_PASSWORD = "password";

	private final Logger logger;

	public UserRepository() {
		logger = new Logger(UserRepository.class.getSimpleName());

	}

	@Override
	public User insertOne(User user) {
		try (Connection c = establishConnection()) {
			//@formatter:off
			Encryption auth = new Encryption();
			String salt = auth.generateSalt();
			String encryptedPassword = auth.generateEncryptedPassword(user.getPassword(), salt);
			String insert = "INSERT INTO users (" +
					"email, last_name, first_name, phone, join_date, loyalty_points, is_admin, " +
					"password) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
			String formattedPassword = salt + "." + encryptedPassword;
			//@formatter:on
			PreparedStatement statement = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getFirstname());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getJoinDate());
			statement.setInt(6, user.getLoyaltyPoints());
			statement.setBoolean(7, user.isAdmin());
			statement.setString(8, formattedPassword);
			statement.executeUpdate();

			return getUserWithGeneratedId(c, statement, user);
		} catch (Exception e) {
			logger.e("insertOne", e);
		}

		return null;
	}

	@Override
	public List<User> insertMany(List<User> users) {
		return users.stream().map(this::insertOne).toList();
	}

	@Override
	public User getById(long id) {
		User result = null;
		try (Connection c = establishConnection()) {
			String q = "SELECT * FROM users WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(q);
			statement.setLong(1, id);

			ResultSet rs = statement.executeQuery();
			result = buildUser(rs);
		} catch (Exception e) {
			logger.e("getById", e);
		}
		return result;
	}

	@Override
	public List<User> getAll() {
		List<User> result = new ArrayList<>(0);
		try (Connection c = establishConnection()) {
			String q = "SELECT * FROM users";
			PreparedStatement statement = c.prepareStatement(q);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				result.add(buildUser(rs));
			}
		} catch (Exception e) {
			logger.e("getAll", e);
		}
		return result;
	}

	@Override
	public void updateById(User user, long id) {
		try (Connection c = establishConnection()) {
			//@formatter:off
			String q = "UPDATE users SET email = ?, last_name = ?, first_name = ?, phone = ?, join_date = " +
					"?, loyalty_points = ?, is_admin = ?, password = ? WHERE id = ?";
			//@formatter:on
			PreparedStatement statement = c.prepareStatement(q);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getFirstname());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getJoinDate());
			statement.setInt(6, user.getLoyaltyPoints());
			statement.setBoolean(7, user.isAdmin());
			statement.setString(8, user.getPassword());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == -1) {
				throw new ProvisioException.UserRepositoryException("Unable to update user");
			} else if (rowsAffected > 1) {
				throw new ProvisioException.UserRepositoryException("More than 1 row was affected: " + rowsAffected);
			}
		} catch (Exception e) {
			logger.e("updateById", e);
		}
	}

	@Override
	public void deleteById(long id) {
		try (Connection c = establishConnection()) {
			String q = "DELETE FROM users WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(q);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			logger.e("deleteById", e);
		}
	}

	public void changeUserPassword(User user, String newPassword) {
		try (Connection c = establishConnection()) {
			//@formatter:off
			String q = "UPDATE users SET password = ? WHERE id = ?";
			//@formatter:on
			PreparedStatement statement = c.prepareStatement(q);
			Encryption auth = new Encryption();
			String salt = auth.generateSalt();
			String encryptedPassword = auth.generateEncryptedPassword(newPassword, salt);
			String formattedPassword = salt + "." + encryptedPassword;

			statement.setString(1, formattedPassword);
			statement.setLong(2, user.getId());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == -1) {
				throw new ProvisioException.UserRepositoryException("Unable to update user");
			} else if (rowsAffected > 1) {
				throw new ProvisioException.UserRepositoryException("More than 1 row was affected: " + rowsAffected);
			}
		} catch (Exception e) {
			logger.e("changeUserPassword", e);
		}
	}

	public User getUserByUserName(String username) {
		User result = null;
		try (Connection c = establishConnection()) {
			String q = "SELECT * FROM users WHERE email = ?";
			PreparedStatement statement = c.prepareStatement(q);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				result = buildUser(rs);
			}
		} catch (Exception e) {
			logger.e("getUserByUserName", e);
		}
		return result;
	}

	public User getUserByUserNameAndPassword(String username, String password) {
		User result = null;
		try (Connection c = establishConnection()) {
			Encryption auth = new Encryption();

			String q = "SELECT * FROM users WHERE email = ?";
			PreparedStatement statement = c.prepareStatement(q);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			rs.next();
			String rsPassword = rs.getString("password");
			if(rsPassword.contains(".")) {
				String passwordArr[] = rsPassword.split("\\.");
				Boolean passwordMatch = auth.verifyPassword(passwordArr[1], passwordArr[0], password);
				if(passwordMatch == true) {
					result = buildUser(rs);
				}
			} else {
				if(rsPassword.equals(password)) {
					result = buildUser(rs);
				}
			}
		} catch (Exception e) {
			logger.e("getUserByUserNameAndPassword", e);
		}
		return result;
	}

	private User getUserWithGeneratedId(Connection c, Statement statement, User user) throws SQLException,
			ProvisioException.UserRepositoryException {
		ResultSet rs = statement.getGeneratedKeys();
		long userId;

		if (rs == null || !rs.next()) {
			throw new ProvisioException.UserRepositoryException("could not insert User");
		}

		if (rs.next()) {
			userId = rs.getLong(1);
			user.setId(userId);
		}

		c.close();
		return user;
	}

	private User buildUser(ResultSet rs) throws SQLException {
		User result = new User();
		result.setId(rs.getLong(COLUMN_USER_ID));
		result.setPhone(rs.getString(COLUMN_USER_PHONE));
		result.setEmail(rs.getString(COLUMN_USER_EMAIL));
		result.setLastname(rs.getString(COLUMN_USER_LASTNAME));
		result.setFirstname(rs.getString(COLUMN_USER_FIRSTNAME));
		result.setJoinDate(rs.getString(COLUMN_USER_JOIN_DATE));
		result.setLoyaltyPoints(rs.getInt(COLUMN_USER_LOYALTY_POINTS));
		result.setAdmin(rs.getBoolean(COLUMN_USER_IS_ADMIN));
		result.setPassword(rs.getString(COLUMN_USER_PASSWORD));
		return result;
	}
}