package core.application.contract;

import javax.ejb.Local;

import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.application.exception.NotFoundException;
import security.application.dto.UserDTO;
import core.application.exception.ServiceException;

@Local
public interface IUserService {
	UserDTO getByUsername(String username) throws BadRequestException, InternalServerErrorException, NotFoundException;
}
