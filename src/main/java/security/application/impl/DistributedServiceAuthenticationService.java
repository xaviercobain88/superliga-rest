package security.application.impl;

import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.contract.IUserRepository;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.apache.commons.beanutils.BeanUtils;
import security.application.contract.IDistributedServiceAuthenticationService;
import security.application.dto.UserDTO;
import security.domain.contract.IAuthenticationHandler;
import security.domain.contract.IDistributedServiceTokenHandler;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xavier on 1/23/15.
 */
@Stateless
public class DistributedServiceAuthenticationService implements IDistributedServiceAuthenticationService {
    @Inject
    protected IDistributedServiceTokenHandler distributedServiceTokenHandler;
    @Inject
    protected IAuthenticationHandler authenticationHandler;
    @Inject
    protected IUserRepository userRepository;

    @Override


    public UserDTO login(String username, String password) throws InternalServerErrorException, UnauthorizedException {
        try {
            User user = authenticationHandler.login(username, password);

            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userDTO, user);
            userDTO.setToken(distributedServiceTokenHandler.getToken(user.getId()));
            return userDTO;

        } catch (InvalidArgumentException | DomainModelNotLoadedException e) {
            e.printStackTrace();
            throw new UnauthorizedException("ERROR_INVALID_USERNAME_OR_PASSWORD");
        } catch (UnexpectedPersistenceException | InvocationTargetException | IllegalAccessException | NoSuchAlgorithmException
                e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
    }

    public UserDTO loginByToken(String token) throws UnauthorizedException, InternalServerErrorException {
        if (isValidToken(token)) {
            try {
                User user = userRepository.findByToken(token);

                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userDTO, user);
                return userDTO;

            } catch (UnexpectedPersistenceException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                throw new InternalServerErrorException();
            } catch (DomainModelNotLoadedException e) {
                e.printStackTrace();
                throw new UnauthorizedException("No estas autorizado para esta acción");
            }
        }
        throw new UnauthorizedException("No estas autorizado para esta acción");
    }

    @Override
    public boolean isValidToken(String token) {
        return distributedServiceTokenHandler.isValid(token);
    }

    @Override
    public String getToken(Long userId) throws InternalServerErrorException, UnauthorizedException {

        try {
            return distributedServiceTokenHandler.getToken(userId);
        } catch (InvalidArgumentException | UnexpectedPersistenceException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        } catch (DomainModelNotLoadedException e) {
            e.printStackTrace();
            throw new UnauthorizedException("No se ha encontrado un usuario válido");
        }
    }
}
