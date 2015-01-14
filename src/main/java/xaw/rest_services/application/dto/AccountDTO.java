package xaw.rest_services.application.dto;

import java.io.Serializable;
import java.util.ArrayList;

import xaw.rest_services.domain.enums.AccountTypeEnum;
import xaw.rest_services.domain.enums.AccountValueTypeEnum;

public class AccountDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String description;
	protected Long id;
	protected ArrayList<AccountDTO> children;
	protected AccountTypeEnum accountType;
	protected AccountValueTypeEnum accountValueType;
	protected Integer order;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ArrayList<AccountDTO> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<AccountDTO> children) {
		this.children = children;
	}

	public AccountTypeEnum getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountTypeEnum accountType) {
		this.accountType = accountType;
	}

	public AccountValueTypeEnum getAccountValueType() {
		return accountValueType;
	}

	public void setAccountValueType(AccountValueTypeEnum accountValueType) {
		this.accountValueType = accountValueType;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
