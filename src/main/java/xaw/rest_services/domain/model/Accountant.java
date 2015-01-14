package xaw.rest_services.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Accountant {

	@Id
	@GeneratedValue
	protected Long id;
	@OneToMany(mappedBy = "accountant")
	protected List<AccountantFirmAssigment> accountantFirmAssigments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<AccountantFirmAssigment> getAccountantFirmAssigments() {
		return accountantFirmAssigments;
	}

	public void setAccountantFirmAssigments(
			List<AccountantFirmAssigment> accountantFirmAssigments) {
		this.accountantFirmAssigments = accountantFirmAssigments;
	}

}
