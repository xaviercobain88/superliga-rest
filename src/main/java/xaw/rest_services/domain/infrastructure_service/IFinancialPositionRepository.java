package xaw.rest_services.domain.infrastructure_service;

import xaw.rest_services.domain.model.FinancialPosition;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface IFinancialPositionRepository extends
		IGenericRepository<FinancialPosition, Long> {

	FinancialPosition findByCompanyIdPeriodId(Long companyId, Long PeriodId)
			throws UnexpectedPersistenceException;

}
