package xaw.rest_services.infrastructure.impl;

import java.util.HashMap;
import java.util.List;

import xaw.rest_services.domain.infrastructure_service.ILicenseUserRepository;
import xaw.rest_services.domain.model.LicenseUser;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public class LicenseUserRepository extends GenericRepository<LicenseUser, Long>
		implements ILicenseUserRepository {

	public LicenseUserRepository() {
		super(LicenseUser.class);
	}

	@Override
	public List<LicenseUser> findByLicenseId(Long licenseId)
			throws UnexpectedPersistenceException {
		String sql = "select lu from LicenseUser lu where lu.licenseId=:licenseId";
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("licenseId", licenseId);

		return findByQuery(sql, parameters);
	}

}
