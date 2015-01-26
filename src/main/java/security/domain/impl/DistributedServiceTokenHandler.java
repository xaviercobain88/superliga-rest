package security.domain.impl;

import core.domain.exception.DomainModelNotLoadedException;
import core.domain.contract.IUserRepository;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.contract.IDistributedServiceTokenHandler;
import security.domain.contract.IDistributedServiceTokenRepository;
import security.domain.model.DistributedServiceToken;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by xavier on 1/23/15.
 */
@Stateless
public class DistributedServiceTokenHandler implements IDistributedServiceTokenHandler {
    @Inject
    protected IDistributedServiceTokenRepository distributedServiceTokenRepository;
    @Inject
    protected IUserRepository userRepository;

    @Override
    public boolean isValid(String token) {
        if (token == null || token.length() < 1) {
            return false;
        }
        try {
            DistributedServiceToken distributedServiceToken = distributedServiceTokenRepository.load(token);
            if (distributedServiceToken != null) {
                return !distributedServiceToken.isExpired();
            }
            return false;
        } catch (UnexpectedPersistenceException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String getToken(Long userId)
            throws InvalidArgumentException, UnexpectedPersistenceException, DomainModelNotLoadedException{
        if (userId == null || userId < 1) {
            throw new InvalidArgumentException("userId not valid");
        }
        User user = userRepository.load(userId);
        //if(user.matchedPassword(hashedPassword)){

        DistributedServiceToken distributedServiceToken = new DistributedServiceToken(user);

        distributedServiceTokenRepository.create(distributedServiceToken);
        return distributedServiceToken.getToken();


        //}
        //throw new UnauthorizedException("username or password not valid");
    }
}
