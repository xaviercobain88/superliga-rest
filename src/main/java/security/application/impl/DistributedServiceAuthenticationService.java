        package security.application.impl;

import core.application.exception.InternalServerErrorException;
import core.domain.exception.DomainModelNotLoadedException;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.application.contract.IDistributedServiceAuthenticationService;
import security.domain.contract.IDistributedServiceTokenHandler;
import security.domain.exception.UnauthorizedException;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by xavier on 1/23/15.
 */
@Stateless
public class DistributedServiceAuthenticationService implements IDistributedServiceAuthenticationService{
    @Inject
    protected IDistributedServiceTokenHandler distributedServiceTokenHandler;
    @Override
    public boolean isValidToken(String token) {
        return distributedServiceTokenHandler.isValid(token);
    }

    public String getToken(String username, String hashedPassword) throws InternalServerErrorException, UnauthorizedException {

        try {
            return distributedServiceTokenHandler.getToken(username, hashedPassword);
        } catch (InvalidArgumentException | UnexpectedPersistenceException e) {
            e.printStackTrace();
            throw  new InternalServerErrorException("Hubo un error en el sistema, por favor intente más tarde");
        }  catch (DomainModelNotLoadedException | UnauthorizedException e) {
            e.printStackTrace();
            throw  new UnauthorizedException("El nombre de usuario y/o contraseña no es válidos");
        }
    }
}
