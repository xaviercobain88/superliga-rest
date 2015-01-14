package xaw.rest_services.domain.exception;

public class DomainModelNotLoadedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DomainModelNotLoadedException() {
		super("Domain model connot be loaded");
	}

	public DomainModelNotLoadedException(String message) {
		super(message);
	}

}
