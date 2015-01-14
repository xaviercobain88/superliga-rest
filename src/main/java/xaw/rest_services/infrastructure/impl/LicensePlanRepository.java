package xaw.rest_services.infrastructure.impl;

import java.util.HashMap;
import java.util.List;

import xaw.rest_services.domain.enums.StatusEnum;
import xaw.rest_services.domain.infrastructure_service.ILicensePlanRepository;
import xaw.rest_services.domain.model.LicensePlan;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public class LicensePlanRepository extends GenericRepository<LicensePlan, Long>
		implements ILicensePlanRepository {

	public LicensePlanRepository() {
		super(LicensePlan.class);
	}

	@Override
	public List<LicensePlan> findAll(StatusEnum status)
			throws UnexpectedPersistenceException {

		String sql = "select lp from LicensePlan lp where lp.status=:status";
		if (status == null) {
			status = StatusEnum.ACTIVE;
		}
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("status", status);

		return findByQuery(sql, parameters);

	}

	public List<LicensePlan> findAllActive()
			throws UnexpectedPersistenceException {
		return findAll(StatusEnum.ACTIVE);
	}

}
