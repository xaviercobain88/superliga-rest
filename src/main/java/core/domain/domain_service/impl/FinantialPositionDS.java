package core.domain.domain_service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import core.domain.domain_service.IFinantialPositionDS;
import core.domain.enums.AccountTypeEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.infrastructure_service.IAccountRepository;
import core.domain.infrastructure_service.IPeriodRepository;
import core.domain.model.Account;
import core.domain.model.Period;
import core.infrastructure.exception.UnexpectedPersistenceException;

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
