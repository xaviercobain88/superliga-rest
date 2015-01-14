package xaw.rest_services.api.util;

import java.util.ArrayList;
import java.util.List;

public class RestApiMessages {
	List<String> info;
	List<String> warning;
	List<String> danger;

	public RestApiMessages() {
		info = new ArrayList<>();
		warning = new ArrayList<>();
		danger = new ArrayList<>();
	}

	public List<String> getInfoMessages() {
		return info;
	}

	public void setInfoMessages(List<String> info) {
		if (info != null && info.size() > 0) {
			this.info = info;
		}

	}

	public List<String> getWarningMessages() {
		return warning;
	}

	public void setWarningMessages(List<String> warning) {
		if (warning != null && warning.size() > 0) {
			this.warning = warning;
		}

	}

	public List<String> getDangerMessages() {
		return danger;
	}

	public void setDangerMessages(List<String> danger) {
		if (danger != null && danger.size() > 0) {
			this.danger = danger;
		}
	}

	public void addInfoMessage(String message) {
		if (message != null) {
			info.add(message);
		}

	}

	public void addWarningMessage(String message) {
		if (message != null) {
			warning.add(message);
		}

	}

	public void addDangerMessage(String message) {
		if (message != null) {
			danger.add(message);
		}

	}

}
