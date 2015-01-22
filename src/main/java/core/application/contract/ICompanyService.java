package core.application.contract;

import java.util.List;

import javax.ejb.Local;

import core.application.dto.CompanyDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.application.exception.NotFoundException;

@Local
public interface ICompanyService {

	CompanyDTO save(CompanyDTO companyDTO, Long licenseId)
			throws InternalServerErrorException;

	List<CompanyDTO> getCompaniesAssignedToUser(Long userId, Long licenseId)
			throws InternalServerErrorException, BadRequestException, NotFoundException;
}
