package core.application.exception;

public class BadRequestException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}
 
}
