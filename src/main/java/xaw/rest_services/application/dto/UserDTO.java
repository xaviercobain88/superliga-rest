package xaw.rest_services.application.dto;

import javax.validation.constraints.NotNull;

import xaw.rest_services.application.validator.ValidPassword;
import xaw.rest_services.domain.model.IPasswordable;
import xaw.rest_services.domain.validator.DTOValidationGroup;

@ValidPassword(groups = DTOValidationGroup.class, message = "{errors.validation.valid_password.user}")
public class UserDTO implements IPasswordable {
	protected Long id;
	@NotNull(message = "{errors.validation.not_null.user.username}")
	protected String username;
	@NotNull(message = "{errors.validation.not_null.user.first_name}")
	protected String firstName;
	@NotNull(message = "{errors.validation.not_null.user.last_name}")
	protected String lastName;
	protected String rawPassword;
	protected String retypedPassword;

	@Override
	public String getRawPassword() {
		return rawPassword;
	}

	@Override
	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	@Override
	public String getRetypedPassword() {
		return retypedPassword;
	}

	@Override
	public void setRetypedPassword(String retypedPassword) {
		this.retypedPassword = retypedPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
