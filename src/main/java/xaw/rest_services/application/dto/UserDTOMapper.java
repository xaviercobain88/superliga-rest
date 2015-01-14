package xaw.rest_services.application.dto;

import xaw.rest_services.domain.model.User;

public class UserDTOMapper {

	public static User mapUserFromUserDTO(UserDTO userDTO) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setUsername(userDTO.getUsername());
		user.setLastName(userDTO.getLastName());
		user.setRawPassword(userDTO.getRawPassword());
		user.setRawPassword(userDTO.getRetypedPassword());
		return user;
	}

	public static UserDTO mapUserDTOFromUser(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setUsername(user.getUsername());
		userDTO.setLastName(user.getLastName());
		userDTO.setRawPassword(user.getRawPassword());
		userDTO.setRawPassword(user.getRetypedPassword());
		return userDTO;
	}

}
