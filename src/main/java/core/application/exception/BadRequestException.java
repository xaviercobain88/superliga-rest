package core.application.exception;

public class BadRequestException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer code = 400;

	public BadRequestException(String message) {
		super(message);
	}
	@Override
	public Integer getCode() {
		return code;
	}
}
