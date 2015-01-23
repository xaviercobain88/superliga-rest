package core.application.exception;

public class NotFoundException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer code = 404;

	public NotFoundException(String message) {
		super(message);
	}

	@Override
	public Integer getCode() {
		return code;
	}
}
