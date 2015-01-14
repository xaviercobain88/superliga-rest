package xaw.rest_services.domain.infrastructure_service;

import java.util.List;

import xaw.rest_services.domain.enums.StatusEnum;
import xaw.rest_services.domain.model.LicensePlan;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface ILicensePlanRepository extends
		IGenericRepository<LicensePlan, Long> {

	List<LicensePlan> findAll(StatusEnum status)
			throws UnexpectedPersistenceException;

	List<LicensePlan> findAllActive()
			throws UnexpectedPersistenceException;

}
