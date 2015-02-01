package core.domain.exception;

public class InvalidInvitationException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInvitationException() {
		super("Invalid user confirmation");
	}

	public InvalidInvitationException(String message) {
		super(message);
	}
}
