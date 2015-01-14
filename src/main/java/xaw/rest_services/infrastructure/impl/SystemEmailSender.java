package xaw.rest_services.infrastructure.impl;

import java.io.IOException;
import java.util.HashMap;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import xaw.rest_services.domain.model.User;

@Stateless
@LocalBean
public class SystemEmailSender {

	@Inject
	protected EmailSender emailSender;

	@Asynchronous
	public void sendUserConfirmationEmail(User user) throws IOException {
		HashMap<String, String> parameters = new HashMap<>();
		parameters.put(
				"__link__",
				"<a href='google.com?token=" + user.getToken() + "&id="
						+ user.getId() + "'>dale click</a>");
		emailSender.sendSystemEmail("xaviercobain88@hotmail.com",
				"Nueva Cuenta", "user_confirmation", parameters);

	}

	public void sendUserConfirmedEmail(User user) throws IOException {
		HashMap<String, String> parameters = new HashMap<>();
		emailSender.sendSystemEmail("xaviercobain88@hotmail.com",
				"Usuario Confirmado", "user_confirmed", parameters);

	}

}
