package xaw.rest_services.application.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import xaw.rest_services.application.contract.ILicenseService;
import xaw.rest_services.application.dto.LicenseDTO;
import xaw.rest_services.application.exception.InternalServerErrorException;
import xaw.rest_services.application.exception.NotFoundException;
import xaw.rest_services.application.exception.ServiceException;
import xaw.rest_services.domain.infrastructure_service.ILicenseRepository;
import xaw.rest_services.domain.model.License;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LicenseService implements ILicenseService {

	@Inject
	protected ILicenseRepository licenseRepository;

	@Override
	public ArrayList<LicenseDTO> getActiveLicensesOfUser(Long userId)
			throws ServiceException {
		ArrayList<License> licenses = null;
		ArrayList<LicenseDTO> licenseDTOs = null;
		try {
			licenses = (ArrayList<License>) licenseRepository
					.findActiveLicensesByUserId(userId);
			licenseDTOs = new ArrayList<>();
			for (License license : licenses) {
				LicenseDTO licenseDTO = new LicenseDTO();
				if (license.getLicensePlan() != null) {
					BeanUtils.copyProperties(licenseDTO,
							license.getLicensePlan());
					licenseDTO.setUsedCompanies(license.getUsedCompanies());
					licenseDTO.setId(license.getId());
					licenseDTO.setLicensePlanId(license.getLicensePlan()
							.getId());
					licenseDTOs.add(licenseDTO);
				}

			}

		} catch (UnexpectedPersistenceException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new InternalServerErrorException(
					"Hubo problemas al ejecutar su petición, por favor inténtelo en unos instantes");
		}

		if (licenses == null || licenses.size() == 0) {
			throw new NotFoundException(
					"El usuario no posee licencias asignadas");
		}
		return licenseDTOs;

	}

}
