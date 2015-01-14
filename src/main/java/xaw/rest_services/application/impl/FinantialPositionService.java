package xaw.rest_services.application.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import xaw.rest_services.application.contract.IFinantialPositionService;
import xaw.rest_services.application.dto.AccountDTO;
import xaw.rest_services.application.exception.BadRequestException;
import xaw.rest_services.application.exception.InternalServerErrorException;
import xaw.rest_services.domain.domain_service.IFinantialPositionDS;
import xaw.rest_services.domain.model.Account;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class FinantialPositionService implements IFinantialPositionService {

	@Inject
	protected IFinantialPositionDS statementOfFinantialPositionDS;

	@Override
	public ArrayList<AccountDTO> getStructure()
			throws InternalServerErrorException {

		try {
			ArrayList<Account> structure = (ArrayList<Account>) statementOfFinantialPositionDS
					.getStructure();
			ArrayList<AccountDTO> structureDTO = convertAccountList(structure);
			return structureDTO;
		} catch (UnexpectedPersistenceException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new InternalServerErrorException(
					"Ha habido un problema en servidor, intente más tarde");
		}

	}

	private ArrayList<AccountDTO> convertAccountList(List<Account> accounts)
			throws IllegalAccessException, InvocationTargetException {
		ArrayList<AccountDTO> accountDTOs = new ArrayList<>();
		if (accounts == null || accounts.size() == 0) {
			return null;
		}
		for (Account account : accounts) {

			AccountDTO accountDTO = new AccountDTO();
			BeanUtils.copyProperties(accountDTO, account);
			ArrayList<AccountDTO> childAccountDTOs = null;
			if (account.getChildAccounts() != null
					&& account.getChildAccounts().size() > 0) {
				childAccountDTOs = convertAccountList(account
						.getChildAccounts());
			}
			accountDTO.setChildren(childAccountDTOs);
			accountDTOs.add(accountDTO);
		}
		return accountDTOs;

	}

	@Override
	public ArrayList<AccountDTO> saveAccountValue(Long id, BigDecimal value,
			Long period) throws BadRequestException,
			InternalServerErrorException {

		return null;
	}

}
