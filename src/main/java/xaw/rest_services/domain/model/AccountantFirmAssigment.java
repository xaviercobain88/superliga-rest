package xaw.rest_services.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class AccountantFirmAssigment {

	@Id
	@GeneratedValue
	protected Long id;
	@ManyToMany
	protected List<Company> company;
	@ManyToOne(optional = false)
	protected Accountant accountant;
	@ManyToOne(optional = false)
	protected License accountingFirm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Company> getCompany() {
		return company;
	}

	public void setCompany(List<Company> company) {
		this.company = company;
	}

	public Accountant getAccountant() {
		return accountant;
	}

	public void setAccountant(Accountant accountant) {
		this.accountant = accountant;
	}

	public License getAccountingFirm() {
		return accountingFirm;
	}

	public void setAccountingFirm(License accountingFirm) {
		this.accountingFirm = accountingFirm;
	}

}
