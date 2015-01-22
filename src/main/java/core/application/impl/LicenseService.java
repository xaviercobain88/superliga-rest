package core.application.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import core.application.contract.ILicenseService;
import core.application.dto.LicenseDTO;
import core.application.exception.InternalServerErrorException;
import core.application.exception.NotFoundException;
import core.application.exception.ServiceException;
import core.domain.infrastructure_service.ILicenseRepository;
import core.domain.model.License;
import core.infrastructure.exception.UnexpectedPersistenceException;

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
