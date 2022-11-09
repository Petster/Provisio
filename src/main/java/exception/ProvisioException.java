package exception;

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
}
