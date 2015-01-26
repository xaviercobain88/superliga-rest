package core.application.exception;

public class InternalServerErrorException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer code = 500;

	public InternalServerErrorException() {
		super("Hubo un error en el sistema, por favor intente más tarde");
	}

	@Override
	public Integer getCode() {
		return code;
	}
}
