package api_v1.util;

import java.util.List;

/**
 * Created by xavier on 1/25/15.
 */
public abstract class BaseApiResponse {
    public BaseApiResponse() {
        messages = new RestApiMessages();
    }

    protected  RestApiMessages messages;
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
    public RestApiMessages getRestApiMessages() {
        return messages;
    }

    public abstract Object getData() ;
}
