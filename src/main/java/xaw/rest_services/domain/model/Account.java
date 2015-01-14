package xaw.rest_services.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import xaw.rest_services.domain.enums.AccountTypeEnum;
import xaw.rest_services.domain.enums.AccountValueTypeEnum;

/**
 * @author Xavier Ramírez
 * 
 */
@Entity
public class Account {

	@Id
	protected Long id;
	protected String name;
	protected String description;
	@Enumerated(EnumType.STRING)
	protected AccountValueTypeEnum accountValueType;
	@ManyToOne(optional = true)
	@JoinColumn(name = "parent_account_id")
	protected Account parentAccount;
	@OneToMany
	@JoinColumn(name = "parent_account_id")
	protected List<Account> childAccounts;
	@Enumerated(EnumType.STRING)
	protected AccountTypeEnum accountType;
	@Column(name="order_weight")
	protected Integer order;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Account> getChildAccounts() {
		return childAccounts;
	}

	public AccountTypeEnum getAccountType() {
		return accountType;
	}

	public AccountValueTypeEnum getAccountValueType() {
		return accountValueType;
	}

	public Integer getOrder() {
		return order;
	}
	
	
	

}
