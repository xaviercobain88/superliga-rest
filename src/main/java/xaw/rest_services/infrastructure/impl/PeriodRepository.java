package xaw.rest_services.infrastructure.impl;

import xaw.rest_services.domain.infrastructure_service.IPeriodRepository;
import xaw.rest_services.domain.model.Period;

public class PeriodRepository extends GenericRepository<Period, Long> implements
		IPeriodRepository {

	public PeriodRepository() {
		super(Period.class);
	}

}
