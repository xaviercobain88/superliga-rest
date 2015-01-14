package xaw.rest_services.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class License {

	@Id
	@GeneratedValue
	protected Long id;
	@ManyToOne
	@JoinColumn(name = "license_plan_id")
	protected LicensePlan licensePlan;

	protected Integer usedCompanies;
	protected Integer usedUsers;

	public LicensePlan getLicensePlan() {
		return licensePlan;
	}

	public Integer getUsedCompanies() {
		return usedCompanies;
	}

	public Integer getUsedUsers() {
		return usedUsers;
	}

	public Long getId() {
		return id;
	}

	
}
