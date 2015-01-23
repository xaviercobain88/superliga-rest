package core.application.exception;

public abstract class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public ServiceException(String message) {
		super(message);
	}

	public abstract Integer getCode();

}
