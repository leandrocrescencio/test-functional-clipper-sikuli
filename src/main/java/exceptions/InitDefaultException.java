package exceptions;


@SuppressWarnings("serial")
public class InitDefaultException extends Exception {

	public InitDefaultException() {
		super();
	}

	public InitDefaultException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InitDefaultException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitDefaultException(String message) {
		super(message);
	}

	public InitDefaultException(Throwable cause) {
		super(cause);
	}

}

