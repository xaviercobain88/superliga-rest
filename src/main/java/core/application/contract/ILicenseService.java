package core.application.contract;

import java.util.ArrayList;

import javax.ejb.Local;

import core.application.dto.LicenseDTO;
import core.application.exception.ServiceException;

@Local
public interface ILicenseService {
	ArrayList<LicenseDTO> getActiveLicensesOfUser(Long userId)
			throws ServiceException;
}
