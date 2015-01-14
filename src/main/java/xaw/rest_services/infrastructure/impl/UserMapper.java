package xaw.rest_services.infrastructure.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import xaw.rest_services.domain.model.User;

@Stateless
@LocalBean
public class UserMapper extends GenericRepository<User, Long> {

	public UserMapper() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}

}
