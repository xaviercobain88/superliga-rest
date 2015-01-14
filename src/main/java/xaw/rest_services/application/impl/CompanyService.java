package xaw.rest_services.application.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import xaw.rest_services.application.contract.ICompanyService;
import xaw.rest_services.application.dto.CompanyDTO;
import xaw.rest_services.application.exception.BadRequestException;
import xaw.rest_services.application.exception.InternalServerErrorException;
import xaw.rest_services.application.exception.NotFoundException;
import xaw.rest_services.domain.domain_service.ICompanyDS;
import xaw.rest_services.domain.infrastructure_service.ICompanyRepository;
import xaw.rest_services.domain.model.Company;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CompanyService implements ICompanyService {

	@Inject
	protected ICompanyDS companyDS;
	@Inject
	protected ICompanyRepository companyRepository;

	@Override
	public CompanyDTO save(CompanyDTO companyDTO, Long licenseId)
			throws InternalServerErrorException {
		Company company = new Company();
		try {
			BeanUtils.copyProperties(company, companyDTO);
			companyDS.save(company, licenseId);
			companyDTO = new CompanyDTO();
			BeanUtils.copyProperties(companyDTO, company);
			return companyDTO;

		} catch (IllegalAccessException | InvocationTargetException
				| UnexpectedPersistenceException e) {
			e.printStackTrace();
			throw new InternalServerErrorException(
					"Hubo problemas al ejecutar su petición, por favor inténtelo en unos instantes");
		}
	}

	@Override
	public List<CompanyDTO> getCompaniesAssignedToUser(Long userId,
			Long licenseId) throws InternalServerErrorException,
			BadRequestException, NotFoundException {

		if (licenseId != null && licenseId > 0 && userId != null && userId > 0) {
			List<Company> companies = null;
			List<CompanyDTO> companyDTOs = new ArrayList<>();
			try {
				companies = companyRepository.findByUserIdAndLicenseId(userId,
						licenseId);
				for (Company company : companies) {
					CompanyDTO companyDTO = new CompanyDTO();
					BeanUtils.copyProperties(companyDTO, company);
					companyDTOs.add(companyDTO);

				}

			} catch (UnexpectedPersistenceException | IllegalAccessException
					| InvocationTargetException e) {
				e.printStackTrace();
				throw new InternalServerErrorException(
						"Hubo problemas al ejecutar su petición, por favor inténtelo en unos instantes");
			}
			if (companies == null || companies.size() == 0) {
				throw new NotFoundException(
						"No existen asignaciones para este usuario");
			}

			return companyDTOs;

		} else {
			throw new BadRequestException(
					"El id de usuario y/o el id de licencia no es válido");
		}

	}
}
