package core.application.impl;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.apache.commons.beanutils.BeanUtils;

import core.application.contract.IUserService;
import core.application.dto.UserDTO;
import core.application.exception.InternalServerErrorException;
import core.application.exception.NotFoundException;
import core.application.exception.ServiceException;
import core.domain.infrastructure_service.IUserRepository;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserService implements IUserService {

	@Inject
	protected IUserRepository userRepository;

	@Override
	public UserDTO getById(Long id) throws ServiceException {
		User user = null;
		UserDTO userDTO = null;
		if (id != null && id > 0) {
			try {
				user = userRepository.load(id);
				if (user != null) {
					userDTO = new UserDTO();
					BeanUtils.copyProperties(userDTO, user);
				}

			} catch (UnexpectedPersistenceException | IllegalAccessException
					| InvocationTargetException e) {
				e.printStackTrace();
				// TODO: i18n
				throw new InternalServerErrorException(
						"Hubo problemas al ejecutar su petición, por favor inténtelo en unos instantes");
			}
			if (user != null) {
				return userDTO;
			} else {
				// TODO: i18n
				throw new NotFoundException("El usuario no fue encontrado");
			}

		} else {
			// TODO: i18n
			throw new BadRequestException("El id de usuario no es válido");
		}

	}

}
