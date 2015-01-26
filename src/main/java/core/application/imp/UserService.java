package core.application.imp;

import core.application.contract.IUserService;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.application.exception.NotFoundException;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.contract.IUserRepository;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.apache.commons.beanutils.BeanUtils;
import security.application.dto.UserDTO;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by xavier on 1/24/15.
 */
public class UserService implements IUserService {

    @Inject
    protected IUserRepository userRepository;

    @Override
    public UserDTO getByUsername(String username) throws BadRequestException, InternalServerErrorException, NotFoundException {

        if (username != null && username.length() >0) {
            try {
                User user = userRepository.findByUsername(username);
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userDTO, user);
                return userDTO;
            } catch (UnexpectedPersistenceException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                throw new InternalServerErrorException();
            } catch (DomainModelNotLoadedException e) {
                e.printStackTrace();
                throw new NotFoundException("El usuario no ha sido encontrado");
            }

        }
        throw new BadRequestException("Nombre de usuario inválido");
    }
}
