package util;

public class Logger {
	// formatted logger
	private final String TAG;

	public Logger(String tag) {
		TAG = tag;
	}


	public void i(String operation, Object o) {
		System.out.println("\n" + TAG + " > " + operation + "\n\t" + o.getClass() + o);
	}

	public void e(String operation, Exception e) {
		System.err.println("\n" + TAG + " > " + operation + "\n\tError " + e);
		e.printStackTrace();
	}
}
