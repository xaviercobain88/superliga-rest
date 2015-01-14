package xaw.rest_services.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import xaw.rest_services.application.validator.ValidPassword;
import xaw.rest_services.domain.enums.UserRegistrationStatusEnum;
import xaw.rest_services.domain.validator.DTOValidationGroup;
import xaw.rest_services.domain.validator.OnSaveValidationGroup;

@Entity
@ValidPassword(groups = DTOValidationGroup.class, message = "{errors.validation.valid_password.user}")
public class User implements IPasswordable {

	public static final Integer MIN_PASSWORD_LENGHT = 8;

	@Id
	@GeneratedValue
	protected Long id;
	@NotNull(message = "{errors.validation.not_null.user.username}")
	protected String username;
	@NotNull(message = "{errors.validation.not_null.user.first_name}")
	protected String firstName;
	@NotNull(message = "{errors.validation.not_null.user.last_name}")
	protected String lastName;
	@NotNull(groups = OnSaveValidationGroup.class)
	protected String password;
	protected String token;
	@Enumerated(EnumType.STRING)
	protected UserRegistrationStatusEnum registrationStatus;
	@Transient
	protected String rawPassword;
	@Transient
	protected String retypedPassword;

	public User() {
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserRegistrationStatusEnum getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(
			UserRegistrationStatusEnum registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

}
