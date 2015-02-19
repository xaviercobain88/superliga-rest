package api_v1.controller;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 * Created by xavier on 1/24/15.
 */
public class BaseController {
    @Context
    protected HttpServletRequest httpRequest;
    @Context
    protected HttpServletResponse httpResponse;
    protected Integer httpStatusCode;
    private static final Integer OK = 200;
    protected Gson gson;

    public BaseController() {
        httpStatusCode=OK;
        gson = new Gson();
    }

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    public HttpServletResponse getHttpResponse() {
        return httpResponse;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }


}
