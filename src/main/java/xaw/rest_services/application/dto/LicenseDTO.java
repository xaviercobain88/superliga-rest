package xaw.rest_services.application.dto;

import java.io.Serializable;

public class LicenseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer totalCompanies;
	protected Long id;
	protected Long licensePlanId;
	protected Integer usedCompanies;

	public Integer getTotalCompanies() {
		return totalCompanies;
	}

	public void setTotalCompanies(Integer totalCompanies) {
		this.totalCompanies = totalCompanies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUsedCompanies() {
		return usedCompanies;
	}

	public void setUsedCompanies(Integer usedCompanies) {
		this.usedCompanies = usedCompanies;
	}

	public Long getLicensePlanId() {
		return licensePlanId;
	}

	public void setLicensePlanId(Long licensePlanId) {
		this.licensePlanId = licensePlanId;
	}
	
	

}
