package core.domain.domain_service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import core.domain.domain_service.ICompanyDS;
import core.domain.infrastructure_service.ICompanyRepository;
import core.domain.infrastructure_service.ILicenseRepository;
import core.domain.infrastructure_service.ILicenseUserRepository;
import core.domain.model.Company;
import core.domain.model.License;
import core.domain.model.LicenseUser;
import core.infrastructure.exception.UnexpectedPersistenceException;

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
