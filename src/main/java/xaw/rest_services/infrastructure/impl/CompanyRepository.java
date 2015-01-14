package xaw.rest_services.infrastructure.impl;

import java.util.HashMap;
import java.util.List;

import xaw.rest_services.domain.infrastructure_service.ICompanyRepository;
import xaw.rest_services.domain.model.Company;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public class CompanyRepository extends GenericRepository<Company, Long>
		implements ICompanyRepository {

	public CompanyRepository() {
		super(Company.class);
	}

	@Override
	public List<Company> findByUserIdAndLicenseId(Long userId, Long licenseId)
			throws UnexpectedPersistenceException {
		String sql = "select co from Company co join co.licenseUsers lu where lu.userId = :userId and lu.licenseId=:licenseId";
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("userId", userId);
		parameters.put("licenseId", licenseId);
		return findByQuery(sql, parameters);

	}

}
