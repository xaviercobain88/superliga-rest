package xaw.rest_services.domain.domain_service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import xaw.rest_services.domain.domain_service.IFinantialPositionDS;
import xaw.rest_services.domain.enums.AccountTypeEnum;
import xaw.rest_services.domain.exception.DomainModelNotLoadedException;
import xaw.rest_services.domain.infrastructure_service.IAccountRepository;
import xaw.rest_services.domain.infrastructure_service.IPeriodRepository;
import xaw.rest_services.domain.model.Account;
import xaw.rest_services.domain.model.Period;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

@Stateless
public class FinantialPositionDS implements IFinantialPositionDS {

	@Inject
	protected IAccountRepository accountRepository;
	@Inject
	protected IPeriodRepository periodRepository;

	@Override
	public List<Account> getStructure() throws UnexpectedPersistenceException {
		Account assets = accountRepository
				.findParentByType(AccountTypeEnum.ASSETS);
		Account liabilities = accountRepository
				.findParentByType(AccountTypeEnum.LIABILITIES);
		Account equity = accountRepository
				.findParentByType(AccountTypeEnum.EQUITY);
		List<Account> structure = new ArrayList<>();
		if (assets != null) {
			structure.add(assets);
		}
		if (liabilities != null) {
			structure.add(liabilities);
		}
		if (equity != null) {
			structure.add(equity);
		}

		return structure;
	}

	@Override
	public List<Account> saveAccountValue(Long accountId, BigDecimal value,
			Long periodId) throws UnexpectedPersistenceException,
			DomainModelNotLoadedException {
		Period period = periodRepository.load(periodId);
		Account account = accountRepository.load(accountId);
		if (period == null || account == null) {
			throw new DomainModelNotLoadedException(
					"Domain model does not exist");
		}
		
		

		return null;
	}

}
