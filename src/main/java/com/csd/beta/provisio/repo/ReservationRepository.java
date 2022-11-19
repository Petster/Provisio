package com.csd.beta.provisio.repo;


import com.csd.beta.provisio.entity.Reservation;
import com.csd.beta.provisio.exception.ProvisioException;
import com.csd.beta.provisio.util.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements Repository<Reservation> {
	// field constants
	private final String COLUMN_RESERVATION_ID = "id";
	private final String COLUMN_RESERVATION_USER_FK = "userID";
	private final String COLUMN_RESERVATION_ROOM_FK = "room_type";
	private final String COLUMN_RESERVATION_RESERVE_DATE = "reserve_date";
	private final String COLUMN_RESERVATION_FROM_DATE = "from_date";
	private final String COLUMN_RESERVATION_TO_DATE = "to_date";
	private final String COLUMN_RESERVATION_PRICE = "price";

	private final Logger logger;


	public ReservationRepository() {
		this.logger = new Logger(ReservationRepository.class.getSimpleName());
	}

	@Override
	public Reservation insertOne(Reservation reservation) {
		try (Connection c = establishConnection()) {
			//@formatter:off
			String insert = "INSERT INTO reservations (userID, room_type, reserve_date, from_date, to_date, price)" +
					                " VALUES (?,?,?,?,?,?)";
			//@formatter:on
			PreparedStatement statement = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, reservation.getUserId());
			statement.setLong(2, reservation.getRoomType());
			statement.setString(3, reservation.getReserveDate());
			statement.setString(4, reservation.getFromDate());
			statement.setString(5, reservation.getToDate());
			statement.setInt(6, reservation.getPrice());
			statement.executeUpdate();

			return getReservationWithGeneratedId(c, statement, reservation);
		} catch (Exception e) {
			logger.e("insertOne", e);
		}

		return null; // noop
	}

	@Override
	public List<Reservation> insertMany(List<Reservation> reservations) {
		return reservations.stream().map(this::insertOne).toList();
	}

	@Override
	public Reservation getById(long id) {
		Reservation result = null;
		try (Connection c = establishConnection()) {
			String q = "SELECT * FROM reservations WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(q);
			statement.setLong(1, id);

			ResultSet rs = statement.executeQuery();
			result = buildReservation(rs);
		} catch (Exception e) {
			logger.e("getById", e);
		}

		return result;
	}

	@Override
	public List<Reservation> getAll() {
		List<Reservation> result = new ArrayList<>(0);
		try (Connection c = establishConnection()) {
			String q = "SELECT * FROM reservations";
			PreparedStatement statement = c.prepareStatement(q);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				result.add(buildReservation(rs));
			}
		} catch (Exception e) {
			logger.e("getAll", e);
		}

		return result;
	}

	public List<Reservation> getAllById(long id) {
		List<Reservation> result = new ArrayList<>(0);
		try (Connection c = establishConnection()) {
			String q = "SELECT * FROM reservations WHERE userID = ?";
			PreparedStatement statement = c.prepareStatement(q);

			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				result.add(buildReservation(rs));
			}
		} catch (Exception e) {
			logger.e("getAllById", e);
		}

		return result;
	}

	@Override
	public void updateById(Reservation reservation, long id) {
		try (Connection c = establishConnection()) {
			//@formatter:off
			String q = "UPDATE reservations SET room_type = ?, reserve_date = ?, from_date = ?, to_date = ?, " +
					           "price = ? WHERE id = ?";
			//@formatter:on
			PreparedStatement statement = c.prepareStatement(q);
			statement.setLong(1, reservation.getRoomType());
			statement.setString(2, reservation.getReserveDate());
			statement.setString(3, reservation.getFromDate());
			statement.setString(4, reservation.getToDate());
			statement.setInt(5, reservation.getPrice());
			statement.setLong(6, reservation.getId());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == -1) {
				throw new ProvisioException.ReservationRepositoryException("Unable to updated reservation");
			} else if (rowsAffected > 1) { // then batch operation and it is a problem
				throw new ProvisioException.ReservationRepositoryException("More than 1 row as affected: " + rowsAffected);
			}
		} catch (Exception e) {
			logger.e("updateById", e);
		}
	}

	@Override
	public void deleteById(long id) {
		try (Connection c = establishConnection()) {
			String q = "DELETE FROM reservations WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(q);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			logger.e("deleteById", e);
		}
	}

	private Reservation getReservationWithGeneratedId(Connection c, Statement statement,
	                                                  Reservation reservation) throws SQLException, ProvisioException.ReservationRepositoryException {
		ResultSet rs = statement.getGeneratedKeys();

		if (rs == null || !rs.next()) {
			throw new ProvisioException.ReservationRepositoryException("could not insert Reservation");
		}

		if(rs.next()) {
			long reservationId = rs.getLong(COLUMN_RESERVATION_ID);
			reservation.setId(reservationId);
		}

		c.close();
		return reservation;
	}

	private Reservation buildReservation(ResultSet rs) throws SQLException {
		Reservation result = new Reservation();
		result.setId(rs.getLong(COLUMN_RESERVATION_ID));
		result.setUserId(rs.getLong(COLUMN_RESERVATION_USER_FK));
		result.setRoomType(rs.getLong(COLUMN_RESERVATION_ROOM_FK));
		result.setReserveDate(rs.getString(COLUMN_RESERVATION_RESERVE_DATE));
		result.setFromDate(rs.getString(COLUMN_RESERVATION_FROM_DATE));
		result.setToDate(rs.getString(COLUMN_RESERVATION_TO_DATE));
		result.setPrice(rs.getInt(COLUMN_RESERVATION_PRICE));

		return result;
	}
}
