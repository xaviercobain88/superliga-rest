package core.application.exception;

public class UnauthorizedException extends ServiceException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer code = 401;

	public UnauthorizedException(String message) {
		super(message);
	}
	@Override
	public Integer getCode() {
		return code;
	}
}
