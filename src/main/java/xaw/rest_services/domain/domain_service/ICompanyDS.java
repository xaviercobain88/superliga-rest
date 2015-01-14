package xaw.rest_services.domain.domain_service;

import xaw.rest_services.domain.model.Company;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface ICompanyDS {

	Company save(Company company, Long licenseId)
			throws UnexpectedPersistenceException;

}
