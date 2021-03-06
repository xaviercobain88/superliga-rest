package core.application.contract;

import javax.ejb.Local;

import security.application.dto.UserDTO;

@Local
public interface IRegistrationService {
	UserDTO signUp(UserDTO userDTO) throws Exception;

	UserDTO confirmRegistration(Long id, String token) throws Exception;
}
