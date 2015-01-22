package core.domain.domain_service;

import javax.ejb.Local;

import core.domain.exception.DomainModelNotCreatedException;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.exception.InvalidUserConfirmationException;
import core.domain.model.User;

@Local
public interface IRegistrationDS {
	public User signUp(User user) throws DomainModelNotCreatedException;

	public User confirmRegistration(Long id, String token)
			throws DomainModelNotLoadedException,
			InvalidUserConfirmationException;
}
