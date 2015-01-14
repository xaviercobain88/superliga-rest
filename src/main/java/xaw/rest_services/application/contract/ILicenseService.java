package xaw.rest_services.application.contract;

import java.util.ArrayList;

import javax.ejb.Local;

import xaw.rest_services.application.dto.LicenseDTO;
import xaw.rest_services.application.exception.ServiceException;

@Local
public interface ILicenseService {
	ArrayList<LicenseDTO> getActiveLicensesOfUser(Long userId)
			throws ServiceException;
}
