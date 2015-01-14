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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import xaw.rest_services.api.util.RestApiResponse;
import xaw.rest_services.application.contract.ITournamentCreationService;
import xaw.rest_services.application.dto.TournamentDTO;
import xaw.rest_services.application.dto.TournamentSettingsDTO;
import xaw.rest_services.application.exception.BadRequestException;
import xaw.rest_services.application.exception.InternalServerErrorException;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 */
@RequestScoped
public class TournamentResource {

	public static final String USER_ID = "userId";
	@Inject
	protected ITournamentCreationService tournamentCreationService;

	@Path("/user/{" + USER_ID + "}/tournament")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestApiResponse<TournamentDTO> get(
			TournamentSettingsDTO tournamentSettingsDTO,
			@PathParam(USER_ID) Long userId) {
		RestApiResponse<TournamentDTO> restApiResponse = new RestApiResponse<>();
		try {
			TournamentDTO tournamentDTO = tournamentCreationService
					.createTournament(tournamentSettingsDTO);

			restApiResponse.setData(tournamentDTO);

		} catch (BadRequestException e) {
			restApiResponse.addDangerMessage(e.getMessage());
			e.printStackTrace();
		}catch (InternalServerErrorException e) {
			restApiResponse.addDangerMessage(e.getMessage());
			e.printStackTrace();
		}
		return restApiResponse;

	}

}
