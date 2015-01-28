package api_v1.util;

import java.util.ArrayList;

public class RestApiListResponse<T> extends BaseApiResponse {

    ArrayList<T> data;

    public RestApiListResponse() {
        messages = new RestApiMessages();
    }


    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }


}
