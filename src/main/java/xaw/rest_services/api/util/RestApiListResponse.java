package xaw.rest_services.api.util;

import java.util.ArrayList;
import java.util.List;

public class RestApiListResponse<T> {

	RestApiMessages messages;
	ArrayList<T> data;

	public RestApiListResponse() {
		messages = new RestApiMessages();
	}

	public void setInfoMessages(List<String> infoMessages) {
		this.messages.setInfoMessages(infoMessages);
	}

	public void setWarningMessages(List<String> warningMessages) {
		this.messages.setWarningMessages(warningMessages);
	}

	public void setDangerMessages(List<String> dangerMessages) {
		this.messages.setDangerMessages(dangerMessages);
	}

	public void addInfoMessage(String message) {
		this.messages.addInfoMessage(message);

	}

	public void addWarningMessage(String message) {
		this.messages.addWarningMessage(message);

	}

	public void addDangerMessage(String message) {
		this.messages.addDangerMessage(message);

	}

	public ArrayList<T> getData() {
		return data;
	}

	public void setData(ArrayList<T> data) {
		this.data = data;
	}

	public RestApiMessages getRestApiMessages() {
		return messages;
	}

}
