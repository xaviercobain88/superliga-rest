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
package xaw.rest_services.api.controller;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import xaw.rest_services.api.exception.NotValidArgumentException;
import xaw.rest_services.api.util.RestApiListResponse;
import xaw.rest_services.api.util.RestApiResponse;
import xaw.rest_services.application.contract.ICompanyService;
import xaw.rest_services.application.contract.ILicenseService;
import xaw.rest_services.application.contract.IRegistrationService;
import xaw.rest_services.application.contract.IUserService;
import xaw.rest_services.application.dto.CompanyDTO;
import xaw.rest_services.application.dto.LicenseDTO;
import xaw.rest_services.application.dto.UserDTO;

@Path("/user")
@RequestScoped
public class UserResource {

	public static final String TOKEN = "token";
	public static final String USER_ID = "userId";
	public static final String LICENSE = "license";
	public static final String LICENSE_ID = "licenseId";
	public static final String COMPANY = "company";
	protected RestValidator<UserDTO> restValidator;
	protected RestApiResponse<UserDTO> restApiResponse;
	@Inject
	protected IRegistrationService registrationService;
	@Inject
	protected IUserService userService;
	@Inject
	protected ILicenseService licenseService;
	@Inject
	protected ICompanyService companyService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestApiResponse<UserDTO> post(UserDTO userDTO) {
		restValidator = new RestValidator<UserDTO>(userDTO);
		restApiResponse = new RestApiResponse<>();
		try {
			restValidator.validateDTO();
			UserDTO newRegisteredUserDTO = registrationService.signUp(userDTO);
			restApiResponse.setData(newRegisteredUserDTO);
		} catch (NotValidArgumentException e) {
			restApiResponse.setDangerMessages(e.getMessages());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			restApiResponse.addDangerMessage(e.getMessage());
		}

		return restApiResponse;

	}

	@Path("/{" + USER_ID + "}/confirm")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public RestApiResponse<UserDTO> confirmUser(
			@PathParam(USER_ID) Long userId, @QueryParam(TOKEN) String token) {
		restApiResponse = new RestApiResponse<>();
		try {
			UserDTO newRegisteredUserDTO = registrationService
					.confirmRegistration(userId, token);
			restApiResponse.setData(newRegisteredUserDTO);
		} catch (NotValidArgumentException e) {
			restApiResponse.setDangerMessages(e.getMessages());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			restApiResponse.addDangerMessage(e.getMessage());
		}

		return restApiResponse;

	}

	@Path("/{" + USER_ID + "}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RestApiResponse<UserDTO> get(@PathParam(USER_ID) Long userId) {
		restApiResponse = new RestApiResponse<>();
		try {
			UserDTO newRegisteredUserDTO = userService.getById(userId);
			restApiResponse.setData(newRegisteredUserDTO);
		} catch (Exception e) {
			e.printStackTrace();
			restApiResponse.addDangerMessage(e.getMessage());
		}

		return restApiResponse;

	}

	@Path("/{" + USER_ID + "}/" + LICENSE)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RestApiListResponse<LicenseDTO> getLicenses(
			@PathParam(USER_ID) Long userId) {
		RestApiListResponse<LicenseDTO> restApiListResponse = new RestApiListResponse<>();
		try {
			ArrayList<LicenseDTO> licenseDTOs = licenseService
					.getActiveLicensesOfUser(userId);
			restApiListResponse.setData(licenseDTOs);
		} catch (Exception e) {
			e.printStackTrace();
			restApiListResponse.addDangerMessage(e.getMessage());
		}

		return restApiListResponse;

	}

	@Path("/{" + USER_ID + "}/" + LICENSE + "/{" + LICENSE_ID + "}/" + COMPANY)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestApiResponse<CompanyDTO> postCompany(CompanyDTO companyDTO,
			@PathParam(USER_ID) Long userId,
			@PathParam(LICENSE_ID) Long licenseId) {
		RestApiResponse<CompanyDTO> restApiResponse = new RestApiResponse<>();
		try {
			companyDTO = companyService.save(companyDTO, licenseId);
			restApiResponse.setData(companyDTO);
		} catch (Exception e) {
			e.printStackTrace();
			restApiResponse.addDangerMessage(e.getMessage());
		}

		return restApiResponse;

	}

	@Path("/{" + USER_ID + "}/" + LICENSE + "/{" + LICENSE_ID + "}/" + COMPANY)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RestApiListResponse<CompanyDTO> getCompanies(
			@PathParam(USER_ID) Long userId,
			@PathParam(LICENSE_ID) Long licenseId) {
		RestApiListResponse<CompanyDTO> restApiListResponse = new RestApiListResponse<>();
		try {
			ArrayList<CompanyDTO> companyDTOs = (ArrayList<CompanyDTO>) companyService
					.getCompaniesAssignedToUser(userId, licenseId);
			restApiListResponse.setData(companyDTOs);
		} catch (Exception e) {
			e.printStackTrace();
			restApiResponse.addDangerMessage(e.getMessage());
		}

		return restApiListResponse;

	}

}
