package xaw.rest_services.domain.infrastructure_service;

import java.util.List;

import xaw.rest_services.domain.model.License;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface ILicenseRepository extends IGenericRepository<License, Long> {

	List<License> findActiveLicensesByUserId(Long userId)
			throws UnexpectedPersistenceException;

}
