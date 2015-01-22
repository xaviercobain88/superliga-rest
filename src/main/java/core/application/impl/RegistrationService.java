package core.application.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import core.application.contract.IRegistrationService;
import core.application.dto.UserDTO;
import core.application.dto.UserDTOMapper;
import core.domain.domain_service.IRegistrationDS;
import core.domain.exception.DomainModelNotCreatedException;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.exception.InvalidUserConfirmationException;
import core.domain.model.User;
import core.infrastructure.impl.SystemEmailSender;

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
