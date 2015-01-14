package xaw.rest_services.application.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import xaw.rest_services.application.contract.ILicensePlanService;
import xaw.rest_services.application.dto.LicensePlanDTO;
import xaw.rest_services.application.exception.ServiceException;
import xaw.rest_services.domain.infrastructure_service.ILicensePlanRepository;
import xaw.rest_services.domain.model.LicensePlan;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LicensePlanService implements ILicensePlanService {

	@Inject
	protected ILicensePlanRepository licensePlanRepository;

	@Override
	public List<LicensePlanDTO> findAvailable() throws ServiceException {

		try {
			List<LicensePlan> licensePlans = licensePlanRepository
					.findAllActive();
			List<LicensePlanDTO> licensePlanDTOs = new ArrayList<LicensePlanDTO>();

			if (licensePlans != null && licensePlans.size() > 0) {
				for (LicensePlan licensePlan : licensePlans) {
					LicensePlanDTO licensePlanDTO = new LicensePlanDTO();
					BeanUtils.copyProperties(licensePlanDTO, licensePlan);
					licensePlanDTOs.add(licensePlanDTO);

				}
			}
			return licensePlanDTOs;
		} catch (UnexpectedPersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(
					"Ha habido un problema en servidor, intente más tarde");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new ServiceException(
					"Ha habido un problema en servidor, intente más tarde");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new ServiceException(
					"Ha habido un problema en servidor, intente más tarde");
		}

	}

}
