package xaw.rest_services.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import xaw.rest_services.domain.enums.StatusEnum;

@Entity
public class LicensePlan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	protected Long id;
	protected Integer totalUsers;
	protected Integer totalCompanies;
	protected BigDecimal price;
	protected String description;
	@Enumerated(EnumType.STRING)
	protected StatusEnum status;
	@Transient
	protected Boolean selected;
	public Long getId() {
		return id;
	}
	public Integer getTotalUsers() {
		return totalUsers;
	}
	public Integer getTotalCompanies() {
		return totalCompanies;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public Boolean getSelected() {
		return selected;
	}
	
	

}
