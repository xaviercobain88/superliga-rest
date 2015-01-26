package api_v1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * Created by xavier on 1/24/15.
 */
public class BaseController {
    @Context
    protected HttpServletRequest httpRequest;
}
