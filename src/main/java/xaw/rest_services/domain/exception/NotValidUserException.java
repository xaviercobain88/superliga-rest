package xaw.rest_services.domain.exception;

public class NotValidUserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotValidUserException() {
		super("The provided user is not valid");
	}

	
	

}
