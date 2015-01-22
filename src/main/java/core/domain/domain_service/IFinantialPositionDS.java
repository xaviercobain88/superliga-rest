package core.domain.domain_service;

import java.math.BigDecimal;
import java.util.List;

import core.domain.exception.DomainModelNotLoadedException;
import core.domain.model.Account;
import core.infrastructure.exception.UnexpectedPersistenceException;

public interface IFinantialPositionDS {

	List<Account> getStructure() throws UnexpectedPersistenceException;
	 List<Account> saveAccountValue(Long accountId, BigDecimal value,
				Long periodId) throws UnexpectedPersistenceException,  DomainModelNotLoadedException;

}
