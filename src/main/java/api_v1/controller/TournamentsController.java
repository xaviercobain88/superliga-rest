package api_v1.controller;

import api_v1.util.BaseApiResponse;
import api_v1.util.RestApiListResponse;
import core.application.contract.ITournamentService;
import core.application.dto.StageDTO;
import core.application.exception.*;
import org.hibernate.validator.constraints.NotEmpty;
import security.aop.LoggedUser;
import security.aop.SecuredByToken;
import security.application.dto.UserDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xavier on 1/24/15.
 */
@Path("/tournaments")
@RequestScoped
public class TournamentsController extends BaseController {


    @Inject
    protected ITournamentService tournamentService;
    @Inject
    @LoggedUser
    protected UserDTO userDTO;

    @Path("/{tournamentId}/stages")
    @SecuredByToken
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseApiResponse setStages(@PathParam("tournamentId") @Min(1) Long tournamentId,
                                     @Valid List<StageDTO> stageDTOs) {
        RestApiListResponse<StageDTO> restApiListResponse = new RestApiListResponse<>();

        try {
            stageDTOs = tournamentService.setStages(tournamentId, stageDTOs);
            restApiListResponse.setData((ArrayList<StageDTO>) stageDTOs);

        } catch (core.application.exception.InternalServerErrorException
                | core.application.exception.BadRequestException e) {
            e.printStackTrace();
            restApiListResponse.addDangerMessage(e.getMessage());
        }


        return restApiListResponse;

    }

    @Path("/{tournamentId}/invitations")
    @SecuredByToken
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BaseApiResponse sendInvitations(@PathParam("tournamentId") @Min(1) Long tournamentId,
                                           @NotEmpty List<String> emails) {
        Set<String> emailSet = new HashSet<>(emails);
        RestApiListResponse<UserDTO> restApiListResponse = new RestApiListResponse<>();

        try {
            List<UserDTO> userDTOs = tournamentService.sendInvitations(tournamentId, emailSet, userDTO);
            restApiListResponse.setData((ArrayList<UserDTO>) userDTOs);

        } catch (core.application.exception.InternalServerErrorException | core.application.exception.BadRequestException e) {
            e.printStackTrace();
            restApiListResponse.addDangerMessage(e.getMessage());
        }


        return restApiListResponse;

    }
}
