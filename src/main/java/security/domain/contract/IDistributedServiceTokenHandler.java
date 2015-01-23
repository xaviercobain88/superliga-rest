package security.domain.contract;

import core.domain.exception.DomainModelNotLoadedException;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.exception.UnauthorizedException;
import utils.exception.InvalidArgumentException;

import javax.ejb.Local;

/**
 * Created by xavier on 1/23/15.
 */
@Local
public interface IDistributedServiceTokenHandler {
    boolean isValid(String token);

    public String getToken(String username, String hashedPassword) throws InvalidArgumentException, UnexpectedPersistenceException, DomainModelNotLoadedException, UnauthorizedException;
}
