package com.csd.beta.provisio.exception;

public class ProvisioException extends Exception {
	public ProvisioException(String message) {
		super(message);
	}

	public ProvisioException(Exception e) {
		super(e);
	}


	public static class LoginException extends ProvisioException {
		public LoginException(String message) {
			super(message);
		}
	}

	public static class RegistrationException extends ProvisioException {
		public RegistrationException(String message) {
			super(message);
		}
	}

	public static class ForgotException extends ProvisioException {
		public ForgotException(String message) {
			super(message);
		}
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

	public static class RoomRepositoryException extends ProvisioException {
		public RoomRepositoryException(String message) {
			super(message);
		}
	}

	public static class LocationRepositoryException extends ProvisioException {
		public LocationRepositoryException(String message) {
			super(message);
		}
	}

	public static class EmailRepositoryException extends ProvisioException {
		public EmailRepositoryException(String message) {
			super(message);
		}
	}
}
