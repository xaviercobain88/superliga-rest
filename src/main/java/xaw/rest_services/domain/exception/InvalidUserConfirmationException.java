package xaw.rest_services.domain.exception;

public class InvalidUserConfirmationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserConfirmationException() {
		super("Invalid user confirmation");
	}

	public InvalidUserConfirmationException(String message) {
		super(message);
	}
}
