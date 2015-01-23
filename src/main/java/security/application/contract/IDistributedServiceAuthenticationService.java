package security.application.contract;

import core.application.exception.InternalServerErrorException;
import security.domain.exception.UnauthorizedException;

import javax.ejb.Local;

/**
 * Created by xavier on 1/23/15.
 */
@Local
public interface IDistributedServiceAuthenticationService {
    boolean isValidToken(String token);
    public String getToken(String username, String hashedPassword) throws InternalServerErrorException, UnauthorizedException;
}
