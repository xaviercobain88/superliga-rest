package core.domain.contract;

import java.io.IOException;

import core.domain.model.User;

public interface ISystemEmailSender {

	void sendConfirmationEmail(User user) throws IOException ;
}
