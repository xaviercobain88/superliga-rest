package xaw.rest_services.domain.domain_service;

import java.math.BigDecimal;
import java.util.List;

import xaw.rest_services.domain.exception.DomainModelNotLoadedException;
import xaw.rest_services.domain.model.Account;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface IFinantialPositionDS {

	List<Account> getStructure() throws UnexpectedPersistenceException;
	 List<Account> saveAccountValue(Long accountId, BigDecimal value,
				Long periodId) throws UnexpectedPersistenceException,  DomainModelNotLoadedException;

}
