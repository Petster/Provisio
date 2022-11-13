package entity;

import java.sql.Date;
import java.util.Objects;

public class Reservation {
	private long _id;
	private long _userId;
	private long _roomType;
	private Date _reserveDate;
	private Date _fromDate;
	private Date _toDate;
	private int _price;

	// no arg
	public Reservation() {}

	// all arg


	public Reservation(long id, long userId, long roomType, Date reserveDate, Date fromDate,
	                   Date toDate, int price) {
		_id = id;
		_userId = userId;
		_roomType = roomType;
		_reserveDate = reserveDate;
		_fromDate = fromDate;
		_toDate = toDate;
		_price = price;
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getRoomType() {
		return _roomType;
	}

	public void setRoomType(long roomType) {
		_roomType = roomType;
	}

	public Date getReserveDate() {
		return _reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		_reserveDate = reserveDate;
	}

	public Date getFromDate() {
		return _fromDate;
	}

	public void setFromDate(Date fromDate) {
		_fromDate = fromDate;
	}

	public Date getToDate() {
		return _toDate;
	}

	public void setToDate(Date toDate) {
		_toDate = toDate;
	}

	public int getPrice() {
		return _price;
	}

	public void setPrice(int price) {
		_price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Reservation that)) return false;
		return _id == that._id && _userId == that._userId && _roomType == that._roomType && _price == that._price && Objects.equals(_reserveDate, that._reserveDate) && Objects.equals(_fromDate, that._fromDate) && Objects.equals(_toDate, that._toDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _userId, _roomType, _reserveDate, _fromDate, _toDate, _price);
	}

	@Override
	public String toString() {
		return "Reservation{" + "_id=" + _id + ", _userId=" + _userId + ", _roomType=" + _roomType + ", " +
				       "_reserveDate=" + _reserveDate + ", _fromDate=" + _fromDate + ", _toDate=" + _toDate + ", _price=" + _price + '}';
	}
}
