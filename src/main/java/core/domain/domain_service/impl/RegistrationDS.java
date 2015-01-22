package core.domain.domain_service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import core.domain.domain_service.IRegistrationDS;
import core.domain.enums.UserRegistrationStatusEnum;
import core.domain.exception.DomainModelNotCreatedException;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.exception.InvalidUserConfirmationException;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import core.infrastructure.impl.UserMapper;

@Stateless
public class RegistrationDS implements IRegistrationDS {

	@Inject
	protected UserMapper userMapper;

	public User signUp(User user) throws DomainModelNotCreatedException {

		try {
			// TODO Validaciones del dominio
			user.setToken(Integer.toString(hashCode()));
			user.setRegistrationStatus(UserRegistrationStatusEnum.NO_CONFIRMED);
			userMapper.create(user);
		} catch (UnexpectedPersistenceException e) {
			e.printStackTrace();
			throw new DomainModelNotCreatedException(
					"exceptions.domain.registration.contact_admin");

		}

		return user;
	}

	public User confirmRegistration(Long id, String token)
			throws DomainModelNotLoadedException,
			InvalidUserConfirmationException {
		try {
			if (id != null && token != null) {
				User user = userMapper.load(id);
				if (user != null && user.getToken() != null) {
					Boolean isValidToken = user.getToken().equals(token);
					if (isValidToken) {
						user.setRegistrationStatus(UserRegistrationStatusEnum.CONFIRMED);
						userMapper.update(user);
						return user;

					}
				}
			}
		} catch (UnexpectedPersistenceException e) {
			e.printStackTrace();
			throw new DomainModelNotLoadedException(
					"exceptions.domain.registration.contact_admin");

		}

		throw new InvalidUserConfirmationException(
				"exceptions.domain.registration.invalid_confirmation");

	}

}
