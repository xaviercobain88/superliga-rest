package xaw.rest_services.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Company {

	@Id
	@GeneratedValue
	protected Long id;
	// @ManyToMany(mappedBy = "company")
	// protected List<AccountantFirmAssigment> accountantFirmAssigments;
	protected String name;
	protected String identification;
	protected String description;
	@ManyToOne
	@JoinColumn(name = "license_id")
	protected License license;
	@ManyToMany(mappedBy = "companies")
	protected List<LicenseUser> licenseUsers;
	@Transient
	protected FinancialPosition finantialPositionStatement;

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIdentification() {
		return identification;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
