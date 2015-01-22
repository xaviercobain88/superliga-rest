package core.application.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class LicensePlanDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer totalCompanies;
	protected BigDecimal price;
	protected String description;
	protected Long id;
	


	public Integer getTotalCompanies() {
		return totalCompanies;
	}

	public void setTotalCompanies(Integer totalCompanies) {
		this.totalCompanies = totalCompanies;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
