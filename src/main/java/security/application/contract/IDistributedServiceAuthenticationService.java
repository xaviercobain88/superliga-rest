package security.application.contract;

import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import security.application.dto.UserDTO;

import javax.ejb.Local;

/**
 * Created by xavier on 1/23/15.
 */
@Local
public interface IDistributedServiceAuthenticationService {
    boolean isValidToken(String token);
    public String getToken(Long userId) throws InternalServerErrorException, UnauthorizedException;
    UserDTO login(String username, String password) throws InternalServerErrorException, UnauthorizedException;
    UserDTO loginByToken(String token) throws UnauthorizedException, InternalServerErrorException;
}
