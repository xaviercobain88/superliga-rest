package xaw.rest_services.domain.domain_service;

import javax.ejb.Local;

import xaw.rest_services.domain.exception.DomainModelNotCreatedException;
import xaw.rest_services.domain.exception.DomainModelNotLoadedException;
import xaw.rest_services.domain.exception.InvalidUserConfirmationException;
import xaw.rest_services.domain.model.User;

@Local
public interface IRegistrationDS {
	public User signUp(User user) throws DomainModelNotCreatedException;

	public User confirmRegistration(Long id, String token)
			throws DomainModelNotLoadedException,
			InvalidUserConfirmationException;
}
