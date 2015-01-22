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
package core.api.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import core.api.util.RestApiResponse;
import core.application.contract.ILicensePlanService;
import core.application.dto.LicensePlanDTO;
import core.application.dto.UserDTO;
import core.application.exception.ServiceException;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 */
@Path("/license")
@RequestScoped
public class LicenseResource {

	@Inject
	protected ILicensePlanService licensePlanService;
	protected RestApiResponse<List<LicensePlanDTO>> restApiResponse;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RestApiResponse<List<LicensePlanDTO>> get(UserDTO userDTO) {
		restApiResponse = new RestApiResponse<>();
		try {
			List<LicensePlanDTO> licensePlanDTOs = licensePlanService
					.findAvailable();

			restApiResponse.setData(licensePlanDTOs);

		} catch (ServiceException e) {
			restApiResponse.addDangerMessage(e.getMessage());
			e.printStackTrace();
		}
		return restApiResponse;

	}

}
