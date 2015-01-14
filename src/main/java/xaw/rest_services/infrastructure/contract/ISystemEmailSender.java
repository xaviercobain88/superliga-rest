package xaw.rest_services.infrastructure.contract;

import java.io.IOException;

import xaw.rest_services.domain.model.User;

public interface ISystemEmailSender {

	void sendConfirmationEmail(User user) throws IOException ;
}
