package com.csd.beta.provisio.exception;

public class ProvisioException extends Exception {
	public ProvisioException(String message) {
		super(message);
	}

	public ProvisioException(Exception e) {
		super(e);
	}


	public static class UserRepositoryException extends ProvisioException {
		public UserRepositoryException(String message) {
			super(message);
		}
	}

	public static class ReservationRepositoryException extends ProvisioException {
		public ReservationRepositoryException(String message) {
			super(message);
		}
	}

	public static class NewsRepositoryException extends ProvisioException {
		public NewsRepositoryException(String message) {
			super(message);
		}
	}
}
