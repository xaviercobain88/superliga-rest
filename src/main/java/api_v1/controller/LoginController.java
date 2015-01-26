/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package api_v1.controller;

import api_v1.util.RestApiResponse;
import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import security.aop.LoggedUser;
import security.application.contract.IDistributedServiceAuthenticationService;
import security.application.dto.UserDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 */
@Path("/login")
@RequestScoped
public class LoginController extends BaseController {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Inject
    protected IDistributedServiceAuthenticationService distributedServiceAuthenticationService;
    @Inject
    @LoggedUser
    UserDTO userDTO;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestApiResponse<UserDTO> login(@QueryParam(USERNAME) String username, @QueryParam(PASSWORD) String password) {
        RestApiResponse<UserDTO> restApiResponse = new RestApiResponse<>();
        try {
            UserDTO userDTO = distributedServiceAuthenticationService.login(username, password);
            //httpRequest.login(username, password);
            userDTO.setPassword(null);
            restApiResponse.setData(userDTO);


        } catch (InternalServerErrorException | UnauthorizedException  e) {
            e.printStackTrace();
            restApiResponse.addDangerMessage(e.getMessage());
        }
        return restApiResponse;

    }

}
