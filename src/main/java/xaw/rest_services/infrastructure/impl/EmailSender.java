package xaw.rest_services.infrastructure.impl;

import java.io.IOException;
import java.util.HashMap;

import xaw.rest_services.util.EmailUtils;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;

/**
 * Session Bean implementation class GwMessage
 */
public class EmailSender {

	final static String fromName = "Cloud Solutions";
	final static String fromEmail = "xaviercobain88@hotmail.com";

	/**
	 * Default constructor.
	 */
	public EmailSender() {
	}

	public void sendSystemEmail(String to, String subject, String templateName,
			HashMap<String, String> parameters) throws IOException {

		SendGrid sendGrid = new SendGrid("xaviercobain88", "axre125423");
		Email email = new Email();
		email.addTo(to);
		email.addToName(fromName);
		email.setFrom(fromEmail);
		email.setSubject(subject);

		EmailUtils emailUtils = new EmailUtils();
		email.setText(emailUtils.getTemplateContent(templateName, parameters));
		try {
			SendGrid.Response response = sendGrid.send(email);
			System.out.println(response.getMessage());
		} catch (SendGridException e) {
			System.err.println(e);
		}

	}

}
