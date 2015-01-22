package core.domain.infrastructure_service;

import java.io.IOException;

import core.domain.model.User;

public interface ISystemEmailSender {

	void sendConfirmationEmail(User user) throws IOException ;
}
