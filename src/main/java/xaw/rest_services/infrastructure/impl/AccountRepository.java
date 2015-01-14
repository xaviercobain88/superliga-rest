package xaw.rest_services.infrastructure.impl;

import java.util.HashMap;
import java.util.List;

import xaw.rest_services.domain.enums.AccountTypeEnum;
import xaw.rest_services.domain.infrastructure_service.IAccountRepository;
import xaw.rest_services.domain.model.Account;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public class AccountRepository extends GenericRepository<Account, Long>
		implements IAccountRepository {

	public AccountRepository() {
		super(Account.class);
	}

	@Override
	public Account findParentByType(AccountTypeEnum accountType)
			throws UnexpectedPersistenceException {
		String sql = "select a from Account a where a.parentAccount is null and a.accountType=:accountType";
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("accountType", accountType);
		List<Account> accounts = findByQuery(sql, parameters);
		if (accounts != null && accounts.size() > 0) {
			return accounts.get(0);
		}
		return null;
	}

}
