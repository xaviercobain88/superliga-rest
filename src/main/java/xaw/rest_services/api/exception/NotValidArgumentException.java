package xaw.rest_services.api.exception;

import java.util.List;

public class NotValidArgumentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<String> messages;

	public NotValidArgumentException(List<String> messages) {
		super("The provided argument for api is not valid");
		this.messages = messages;
		
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	

}
