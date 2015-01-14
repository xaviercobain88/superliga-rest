package xaw.rest_services.domain.infrastructure_service;

import xaw.rest_services.domain.model.AccountValue;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface IAccountValueRepository extends
		IGenericRepository<AccountValue, Long> {

	AccountValue findByFinancialPositionId(Long financialPositionId)
			throws UnexpectedPersistenceException;

}
