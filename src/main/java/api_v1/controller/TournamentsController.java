package api_v1.controller;

import api_v1.util.BaseApiResponse;
import api_v1.util.RestApiResponse;
import core.application.contract.ITournamentService;
import core.application.dto.StageDTO;
import core.application.dto.TournamentDTO;
import core.application.exception.UnauthorizedException;
import core.domain.model.Tournament;
import security.aop.SecuredByToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by xavier on 1/24/15.
 */
@Path("/tournaments")
@RequestScoped
public class TournamentsController extends BaseController {


    @Inject
    protected ITournamentService tournamentService;

    @Path("/{tournamentId}/stage/{order}")
    @SecuredByToken
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseApiResponse setStage(@PathParam("tournamentId") @Min(1) Long tournamentId,
                                    @Valid List<StageDTO> stageDTOs) {
        RestApiResponse<TournamentDTO> restApiMapResponse = new RestApiResponse<>();

        try {
            TournamentDTO newTournamentDTO = tournamentService.setStages(tournamentId, stageDTOs);
            restApiMapResponse.setData(newTournamentDTO);

        } catch (core.application.exception.InternalServerErrorException | UnauthorizedException e) {
            e.printStackTrace();
            restApiMapResponse.addDangerMessage(e.getMessage());
        }


        return restApiMapResponse;

    }
}
