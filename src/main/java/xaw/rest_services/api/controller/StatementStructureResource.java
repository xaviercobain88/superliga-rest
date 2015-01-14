package xaw.rest_services.api.controller;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import xaw.rest_services.api.util.RestApiListResponse;
import xaw.rest_services.application.contract.IFinantialPositionService;
import xaw.rest_services.application.dto.AccountDTO;

@Path("/statement")
@RequestScoped
public class StatementStructureResource {

	public static final String STRUCTURE = "structure";
	public static final String STATEMENT_OF_FINANTIAL_POSITION = "statement_of_finantial_position";

	@Inject
	protected IFinantialPositionService statementOfFinantialPositionService;

	@Path("/" + STATEMENT_OF_FINANTIAL_POSITION + "/" + STRUCTURE)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RestApiListResponse<AccountDTO> getStatementOfFinantialPositionStructure() {

		RestApiListResponse<AccountDTO> restApiListResponse = new RestApiListResponse<>();
		try {
			ArrayList<AccountDTO> accountDTOs = statementOfFinantialPositionService
					.getStructure();
			restApiListResponse.setData(accountDTOs);

		} catch (Exception e) {
			e.printStackTrace();
			restApiListResponse.addDangerMessage(e.getMessage());
		}

		return restApiListResponse;


	}
}
