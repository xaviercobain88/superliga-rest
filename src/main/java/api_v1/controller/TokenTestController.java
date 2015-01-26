package api_v1.controller;

import api_v1.util.RestApiResponse;
import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import security.aop.SecuredByToken;
import security.aop.TokenGuard;
import security.application.dto.UserDTO;

import javax.enterprise.context.RequestScoped;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by xavier on 1/24/15.
 */
@Path("/test")
@RequestScoped

public class TokenTestController extends BaseController{


    @GET
    @SecuredByToken
    @Path("/secure")
    @Produces(MediaType.APPLICATION_JSON)
    public String securedTest(){
        return httpRequest.getQueryString();
    }

    @GET
    @Path("/non-secure")
    @Produces(MediaType.APPLICATION_JSON)
    public String nonSecuredTest(){
        return "No es seguro";
    }


}
