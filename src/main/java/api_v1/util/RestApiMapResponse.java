package api_v1.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestApiMapResponse<T, U> {

	RestApiMessages messages;
	HashMap<T, U> data;

	public RestApiMapResponse() {
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

	public HashMap<T, U> getData() {
		return data;
	}

	public void setData(HashMap<T, U> data) {
		this.data = data;
	}

	public RestApiMessages getRestApiMessages() {
		return messages;
	}

}
