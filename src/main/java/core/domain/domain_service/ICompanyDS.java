package core.domain.domain_service;

import core.domain.model.Company;
import core.infrastructure.exception.UnexpectedPersistenceException;

public interface ICompanyDS {

	Company save(Company company, Long licenseId)
			throws UnexpectedPersistenceException;

}
