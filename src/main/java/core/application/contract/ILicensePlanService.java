package core.application.contract;

import java.util.List;

import javax.ejb.Local;

import core.application.dto.LicensePlanDTO;
import core.application.exception.ServiceException;

@Local
public interface ILicensePlanService {

	public List<LicensePlanDTO> findAvailable() throws ServiceException;

}
