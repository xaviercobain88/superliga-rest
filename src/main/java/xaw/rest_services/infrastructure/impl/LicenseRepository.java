package xaw.rest_services.infrastructure.impl;

import java.util.HashMap;
import java.util.List;

import xaw.rest_services.domain.infrastructure_service.ILicenseRepository;
import xaw.rest_services.domain.model.License;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public class LicenseRepository extends GenericRepository<License, Long>
		implements ILicenseRepository {

	public LicenseRepository() {
		super(License.class);
	}

	@Override
	public List<License> findActiveLicensesByUserId(Long userId)
			throws UnexpectedPersistenceException {
		String sql = "select lu.license from LicenseUser lu where lu.userId = :userId";
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("userId", userId);
		return findByQuery(sql, parameters);
	}

}
