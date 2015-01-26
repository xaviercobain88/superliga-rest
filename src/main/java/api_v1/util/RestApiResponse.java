package api_v1.util;

import java.util.List;

public class RestApiResponse<T> extends BaseApiResponse {


	T data;

	public RestApiResponse() {

	}



	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}



}
