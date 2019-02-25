package exceptions;

public class InesperadoException extends RuntimeException {

	private static final long serialVersionUID = -5141246164442856257L;

    public InesperadoException(final String message, final Exception e) {
        super(message, e);
    }

    public InesperadoException(final Exception e) {
        super(e);
    }

    public InesperadoException(final String message) {
        super(message);
    }

}
