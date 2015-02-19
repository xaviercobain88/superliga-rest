package api_v1.controller;

import api_v1.util.RestApiResponse;
import core.application.contract.ITournamentService;
import core.application.dto.TournamentDTO;
import core.application.exception.UnauthorizedException;
import security.aop.SecuredByToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by xavier on 1/24/15.
 */
@Path("/users")
@RequestScoped
public class UsersController extends BaseController {


    @Inject
    protected ITournamentService tournamentService;



    @Path("/{userId}/tournaments")
    @SecuredByToken
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmptyTournament(@PathParam("userId") @NotNull @Min(1) Long userId) {


        RestApiResponse<TournamentDTO> restApiMapResponse = new RestApiResponse<>();

        try {
            TournamentDTO newTournamentDTO = tournamentService.createEmpty(userId);
            restApiMapResponse.setData(newTournamentDTO);

        } catch (core.application.exception.InternalServerErrorException | UnauthorizedException e) {
            e.printStackTrace();
            restApiMapResponse.addDangerMessage(e.getMessage());
            setHttpStatusCode(e.getCode());
        }

        return Response.status(getHttpStatusCode()).entity(restApiMapResponse).build();

    }


}
