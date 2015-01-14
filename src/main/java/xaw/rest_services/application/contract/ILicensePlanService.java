package xaw.rest_services.application.contract;

import java.util.List;

import javax.ejb.Local;

import xaw.rest_services.application.dto.LicensePlanDTO;
import xaw.rest_services.application.exception.ServiceException;

@Local
public interface ILicensePlanService {

	public List<LicensePlanDTO> findAvailable() throws ServiceException;

}
