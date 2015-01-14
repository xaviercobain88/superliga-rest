package xaw.rest_services.application.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import xaw.rest_services.application.contract.IRegistrationService;
import xaw.rest_services.application.dto.UserDTO;
import xaw.rest_services.application.dto.UserDTOMapper;
import xaw.rest_services.domain.domain_service.IRegistrationDS;
import xaw.rest_services.domain.exception.DomainModelNotCreatedException;
import xaw.rest_services.domain.exception.DomainModelNotLoadedException;
import xaw.rest_services.domain.exception.InvalidUserConfirmationException;
import xaw.rest_services.domain.model.User;
import xaw.rest_services.infrastructure.impl.SystemEmailSender;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class RegistrationService implements IRegistrationService {

	@Inject
	protected IRegistrationDS registrationDS;
	@Inject
	protected SystemEmailSender emailSender;

	public UserDTO signUp(UserDTO userDTO) throws Exception {
		try {
			User newUser = UserDTOMapper.mapUserFromUserDTO(userDTO);
			registrationDS.signUp(newUser);
			emailSender.sendUserConfirmationEmail(newUser);

			UserDTO newRegisteredUserDTO = UserDTOMapper
					.mapUserDTOFromUser(newUser);
			return newRegisteredUserDTO;
		} catch (DomainModelNotCreatedException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	public UserDTO confirmRegistration(Long id, String token) throws Exception {

		try {
			User confirmedUser = registrationDS.confirmRegistration(id, token);
			if (confirmedUser != null) {
				emailSender.sendUserConfirmedEmail(confirmedUser);
			}
			UserDTO confirmedUserDTO = UserDTOMapper
					.mapUserDTOFromUser(confirmedUser);
			return confirmedUserDTO;

		} catch (DomainModelNotLoadedException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (InvalidUserConfirmationException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

}
