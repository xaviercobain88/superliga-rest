package xaw.rest_services.application.contract;

import java.util.List;

import javax.ejb.Local;

import xaw.rest_services.application.dto.CompanyDTO;
import xaw.rest_services.application.exception.BadRequestException;
import xaw.rest_services.application.exception.InternalServerErrorException;
import xaw.rest_services.application.exception.NotFoundException;

@Local
public interface ICompanyService {

	CompanyDTO save(CompanyDTO companyDTO, Long licenseId)
			throws InternalServerErrorException;

	List<CompanyDTO> getCompaniesAssignedToUser(Long userId, Long licenseId)
			throws InternalServerErrorException, BadRequestException, NotFoundException;
}
