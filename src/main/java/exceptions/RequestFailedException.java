package exceptions;

@SuppressWarnings("serial")
public class RequestFailedException extends RuntimeException {

	public RequestFailedException() {
		super();
	}

	public RequestFailedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RequestFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestFailedException(String message) {
		super(message);
	}

	public RequestFailedException(Throwable cause) {
		super(cause);
	}

}
