package exceptions;


@SuppressWarnings("serial")
public class TimeoutException extends RuntimeException {

	public TimeoutException() {
		super();
	}

	public TimeoutException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeoutException(String message) {
		super(message);
	}

	public TimeoutException(Throwable cause) {
		super(cause);
	}

}

