package xaw.rest_services.domain.domain_service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import xaw.rest_services.domain.domain_service.ICompanyDS;
import xaw.rest_services.domain.infrastructure_service.ICompanyRepository;
import xaw.rest_services.domain.infrastructure_service.ILicenseRepository;
import xaw.rest_services.domain.infrastructure_service.ILicenseUserRepository;
import xaw.rest_services.domain.model.Company;
import xaw.rest_services.domain.model.License;
import xaw.rest_services.domain.model.LicenseUser;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

@Stateless
public class CompanyDS implements ICompanyDS {

	@Inject
	protected ILicenseRepository licenseRepository;
	@Inject
	protected ILicenseUserRepository licenseUserRepository;
	@Inject
	protected ICompanyRepository companyRepository;

	@Override
	public Company save(Company company, Long licenseId)
			throws UnexpectedPersistenceException {
		License license = licenseRepository.load(licenseId);
		company.setLicense(license);
		companyRepository.create(company);
		// TODO: change for specific user assigments
		List<LicenseUser> licenseUsers = licenseUserRepository
				.findByLicenseId(licenseId);
		for (LicenseUser licenseUser : licenseUsers) {
			List<Company> companies = licenseUser.getCompanies();
			companies.add(company);
			licenseUser.setCompanies(companies);
			licenseUserRepository.update(licenseUser);
		}

		return company;
	}

}
