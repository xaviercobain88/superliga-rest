package xaw.rest_services.infrastructure.exception;

public class UnexpectedPersistenceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnexpectedPersistenceException() {
		super("Unexpected persistence exception");
	}
	
	public UnexpectedPersistenceException(String message) {
		super(message);
	}

	
	

}
