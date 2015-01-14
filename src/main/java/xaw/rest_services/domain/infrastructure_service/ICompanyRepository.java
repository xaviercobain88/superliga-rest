package xaw.rest_services.domain.infrastructure_service;

import java.util.List;

import xaw.rest_services.domain.model.Company;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface ICompanyRepository extends IGenericRepository<Company, Long> {

	List<Company> findByUserIdAndLicenseId(Long userId, Long licenseId)
			throws UnexpectedPersistenceException;

}
