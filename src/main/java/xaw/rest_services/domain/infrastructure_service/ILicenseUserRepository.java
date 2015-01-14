package xaw.rest_services.domain.infrastructure_service;

import java.util.List;

import xaw.rest_services.domain.model.LicenseUser;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface ILicenseUserRepository extends
		IGenericRepository<LicenseUser, Long> {

	List<LicenseUser> findByLicenseId(Long licenseId)
			throws UnexpectedPersistenceException;

}
