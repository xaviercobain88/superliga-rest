package security.domain.contract;

import core.domain.exception.DomainModelNotLoadedException;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import utils.exception.InvalidArgumentException;

import javax.ejb.Local;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xavier on 1/23/15.
 */
@Local
public interface IAuthenticationHandler {
    User login(String username, String password) throws InvalidArgumentException, UnexpectedPersistenceException, DomainModelNotLoadedException, NoSuchAlgorithmException;
}
