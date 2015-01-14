package xaw.rest_services.application.contract;

import javax.ejb.Local;

import xaw.rest_services.application.dto.UserDTO;
import xaw.rest_services.application.exception.ServiceException;

@Local
public interface IUserService {
	UserDTO getById(Long userId) throws ServiceException;
}
