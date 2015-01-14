package xaw.rest_services.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class LicenseUser {

	@Id
	@GeneratedValue
	protected Long id;

	@Column(name="user_id")
	protected Long userId;
	@Column(name="license_id")
	protected Long licenseId;

	@ManyToOne
	@JoinColumn(name = "license_id",insertable = false, updatable = false)
	protected License license;

	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false, updatable = false)
	protected User user;

	@ManyToMany
	@JoinTable(name = "assigment", joinColumns = { @JoinColumn(name = "license_user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "company_id", referencedColumnName = "id") })
	protected List<Company> companies;

	public License getLicense() {
		return license;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	
	

}
