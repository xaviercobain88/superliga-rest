package security.domain.impl;

import core.domain.enums.StatusEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.infrastructure_service.IUserRepository;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.contract.IDistributedServiceTokenHandler;
import security.domain.contract.IDistributedServiceTokenRepository;
import security.domain.exception.UnauthorizedException;
import security.domain.model.DistributedServiceToken;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;

/**
 * Created by xavier on 1/23/15.
 */
@Stateless
public class DistributedServiceTokenHandler implements IDistributedServiceTokenHandler{
    @Inject
    protected IDistributedServiceTokenRepository distributedServiceTokenRepository;
    @Inject
    protected IUserRepository userRepository;
    @Override
    public boolean isValid(String token) {
        if(token==null||token.length()<1){
            return false;
        }
        try {
            DistributedServiceToken distributedServiceToken = distributedServiceTokenRepository.load(token);
            if(distributedServiceToken!=null){
                return !distributedServiceToken.isExpired();
            }
            return false;
        } catch (UnexpectedPersistenceException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String getToken(String username, String hashedPassword)
            throws InvalidArgumentException, UnexpectedPersistenceException, DomainModelNotLoadedException, UnauthorizedException {
        if(username==null || hashedPassword==null){
            throw  new InvalidArgumentException("Username or password not valid");
        }
        User user = userRepository.findByUsername(username, StatusEnum.ACTIVE);
        if(user.matchedPassword(hashedPassword)){

            DistributedServiceToken distributedServiceToken = new DistributedServiceToken(user);

            distributedServiceTokenRepository.create(distributedServiceToken);
            return distributedServiceToken.getToken();


        }
        throw new UnauthorizedException("username or password not valid");
    }
}
