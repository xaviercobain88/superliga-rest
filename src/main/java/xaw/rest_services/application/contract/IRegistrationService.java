package xaw.rest_services.application.contract;

import javax.ejb.Local;

import xaw.rest_services.application.dto.UserDTO;

@Local
public interface IRegistrationService {
	UserDTO signUp(UserDTO userDTO) throws Exception;

	UserDTO confirmRegistration(Long id, String token) throws Exception;
}
