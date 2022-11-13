package repo;

import entity.Reservation;
import exception.ProvisioException;
import util.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ReservationRepository implements Repository<Reservation> {
	// field constants
	private final String COLUMN_RESERVATION_ID = "id";
	private final String COLUMN_RESERVATION_USER_FK = "userID";
	private final String COLUMN_RESERVATION_ROOM_FK = "roomType";
	private final String COLUMN_RESERVATION_RESERVE_DATE = "reserveDate";
	private final String COLUMN_RESERVATION_FROM_DATE = "fromDate";
	private final String COLUMN_RESERVATION_TO_DATE = "toDate";
	private final String COLUMN_RESERVATION_PRICE = "price";

	private final Logger logger;


	public ReservationRepository() {
		this.logger = new Logger(ReservationRepository.class.getSimpleName());
	}

	@Override
	public Reservation insertOne(Reservation reservation) {
		return null;
	}

	@Override
	public List<Reservation> insertMany(List<Reservation> reservations) {
		return null;
	}

	@Override
	public Reservation getById(long id) {
		return null;
	}

	@Override
	public List<Reservation> getAll() {
		return null;
	}

	@Override
	public void updateById(Reservation reservation, long id) {

	}

	@Override
	public void deleteById(long id) {

	}

	private Reservation getReservationWithGeneratedId(Connection c, Statement statement,
	                                                  Reservation reservation) throws SQLException, ProvisioException.ReservationRepositoryException {
		ResultSet rs = statement.getGeneratedKeys();

		if (rs == null || !rs.next()) {
			throw new ProvisioException.ReservationRepositoryException("could not insert Reservation");
		}

		long reservationId = rs.getLong(COLUMN_RESERVATION_ID);
		reservation.setId(reservationId);

		c.close();
		return reservation;
	}

	private Reservation buildReservation(ResultSet rs) throws SQLException {
		Reservation result = new Reservation();
		result.setId(rs.getLong(COLUMN_RESERVATION_ID));
		result.setUserId(rs.getLong(COLUMN_RESERVATION_USER_FK));
		result.setRoomType(rs.getLong(COLUMN_RESERVATION_ROOM_FK));
		result.setReserveDate(rs.getDate(COLUMN_RESERVATION_RESERVE_DATE));
		result.setFromDate(rs.getDate(COLUMN_RESERVATION_FROM_DATE));
		result.setToDate(rs.getDate(COLUMN_RESERVATION_TO_DATE));
		result.setPrice(rs.getInt(COLUMN_RESERVATION_PRICE));

		return result;
	}
}
