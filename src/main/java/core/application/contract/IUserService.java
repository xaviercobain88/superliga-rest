package core.application.contract;

import javax.ejb.Local;

import core.application.dto.UserDTO;
import core.application.exception.ServiceException;

@Local
public interface IUserService {
	UserDTO getById(Long userId) throws ServiceException;
}
