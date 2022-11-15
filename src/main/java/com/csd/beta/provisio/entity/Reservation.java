package com.csd.beta.provisio.entity;

import java.util.Date;
import java.util.Objects;

public class Reservation {
	private long _id;
	private long _userId;
	private long _roomType;
	private String _reserveDate;
	private String _fromDate;
	private String _toDate;
	private int _price;

	// no arg
	public Reservation() {}

	// all arg
	public Reservation(long id, long userId, long roomType, String reserveDate, String fromDate,
					   String toDate, int price) {
		_id = id;
		_userId = userId;
		_roomType = roomType;
		_reserveDate = reserveDate;
		_fromDate = fromDate;
		_toDate = toDate;
		_price = price;
	}

	// insert one
	public Reservation(long userId, long roomType, String reserveDate, String fromDate, String toDate, int price) {
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

	public String getReserveDate() {
		return _reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		_reserveDate = reserveDate;
	}

	public String getFromDate() {
		return _fromDate;
	}

	public void setFromDate(String fromDate) {
		_fromDate = fromDate;
	}

	public String getToDate() {
		return _toDate;
	}

	public void setToDate(String toDate) {
		_toDate = toDate;
	}

	public int getPrice() {
		return _price;
	}

	public void setPrice(int price) {
		_price = price;
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
