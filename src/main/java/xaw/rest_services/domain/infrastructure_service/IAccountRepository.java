package xaw.rest_services.domain.infrastructure_service;

import java.util.List;

import xaw.rest_services.domain.enums.AccountTypeEnum;
import xaw.rest_services.domain.model.Account;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface IAccountRepository extends IGenericRepository<Account, Long> {

	Account findParentByType(AccountTypeEnum accountType)
			throws UnexpectedPersistenceException;

}
