package api_v1.controller;

import api_v1.util.BaseApiResponse;
import api_v1.util.RestApiListResponse;
import api_v1.util.RestApiResponse;
import core.application.contract.ITournamentService;
import core.application.dto.StageDTO;
import core.application.dto.TournamentDTO;
import core.application.exception.*;
import core.domain.model.Tournament;
import security.aop.SecuredByToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavier on 1/24/15.
 */
@Path("/tournaments")
@RequestScoped
public class TournamentsController extends BaseController {


    @Inject
    protected ITournamentService tournamentService;

    @Path("/{tournamentId}/stages")
    @SecuredByToken
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseApiResponse setStages(@PathParam("tournamentId") @Min(1) Long tournamentId,
                                    @Valid List<StageDTO> stageDTOs) {
        RestApiListResponse<StageDTO> restApiListResponse = new RestApiListResponse<>();

        try {
            stageDTOs= tournamentService.setStages(tournamentId, stageDTOs);
            restApiListResponse.setData((ArrayList<StageDTO>) stageDTOs);

        } catch (core.application.exception.InternalServerErrorException
                |core.application.exception.BadRequestException e) {
            e.printStackTrace();
            restApiListResponse.addDangerMessage(e.getMessage());
        }


        return restApiListResponse;

    }
}
