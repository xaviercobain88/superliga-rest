package security.domain.impl;

import core.domain.enums.StatusEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.contract.IUserRepository;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.contract.IAuthenticationHandler;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xavier on 1/23/15.
 */
@Stateless
public class AuthenticationHandler implements IAuthenticationHandler {
    @Inject
    protected IUserRepository userRepository;

    @Override
    public User login(String username, String password) throws InvalidArgumentException, UnexpectedPersistenceException, DomainModelNotLoadedException, NoSuchAlgorithmException {
        if (username == null || username.length() < 1 || password == null || password.length() < 1) {
            throw new InvalidArgumentException("Incorrect username or password");
        }

        User user = userRepository.findByUsername(username, StatusEnum.ACTIVE);
        MessageDigest mdEnc = MessageDigest.getInstance("MD5");
        mdEnc.update(password.getBytes(), 0, password.length());
        String hashedPassword = new BigInteger(1, mdEnc.digest()).toString(16); // Hash value
        if (user.matchedPassword(hashedPassword)) {
            return user;
        }
        throw new InvalidArgumentException("Incorrect username or password");
    }
}
