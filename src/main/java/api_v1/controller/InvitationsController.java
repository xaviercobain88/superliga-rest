package api_v1.controller;

import api_v1.util.BaseApiResponse;
import api_v1.util.RestApiResponse;
import core.application.contract.IInvitationService;
import core.application.dto.TeamDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import security.aop.SecuredByToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by xavier on 1/24/15.
 */
@Path("/invitations")
@RequestScoped
public class InvitationsController extends BaseController {


    @Inject
    protected IInvitationService invitationService;

    @Path("/tournament-participation/{invitationId}")
    @POST
    @SecuredByToken
    @Produces(MediaType.APPLICATION_JSON)
    public BaseApiResponse acceptTournamentParticipation(@PathParam("invitationId") @NotNull @Min(1) Long invitationId) {
        RestApiResponse<TeamDTO> restApiResponse = new RestApiResponse<>();

        try {
            TeamDTO teamDTO = invitationService.acceptTournamentParticipation(invitationId);
            restApiResponse.setData(teamDTO);

        } catch (InternalServerErrorException | BadRequestException e) {
            e.printStackTrace();
            restApiResponse.addDangerMessage(e.getMessage());
        }

        return restApiResponse;

    }

    @Path("/tournament-participation/{invitationId}")
    @DELETE
    @SecuredByToken
    @Produces(MediaType.APPLICATION_JSON)
    public BaseApiResponse refuseTournamentParticipation(@PathParam("invitationId") @NotNull @Min(1) Long invitationId) {
        RestApiResponse<Boolean> restApiResponse = new RestApiResponse<>();

        try {
            invitationService.refuseTournamentParticipation(invitationId);
            restApiResponse.setData(true);

        } catch (InternalServerErrorException | BadRequestException e) {
            e.printStackTrace();
            restApiResponse.addDangerMessage(e.getMessage());
        }

        return restApiResponse;

    }


}
