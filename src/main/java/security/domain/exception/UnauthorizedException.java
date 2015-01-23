package security.domain.exception;

/**
 * Created by xavier on 1/23/15.
 */
public class UnauthorizedException extends Exception{
    public UnauthorizedException(String message) {
        super(message);
    }
}
