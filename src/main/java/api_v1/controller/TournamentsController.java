package api_v1.controller;

import api_v1.util.BaseApiResponse;
import api_v1.util.RestApiListResponse;
import api_v1.util.RestApiResponse;
import com.google.gson.reflect.TypeToken;
import core.application.contract.ITournamentService;
import core.application.dto.StageDTO;
import core.application.dto.TournamentDTO;
import core.application.exception.UnauthorizedException;
import org.hibernate.validator.constraints.NotEmpty;
import security.aop.LoggedUser;
import security.aop.SecuredByToken;
import security.application.dto.UserDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Consumes(MediaType.TEXT_PLAIN)
    public BaseApiResponse setStages(String stageDTOString, @PathParam("tournamentId") @Min(1) Long tournamentId) {

        List<StageDTO> stageDTOs = gson.fromJson(stageDTOString, new TypeToken<List<StageDTO>>() {
        }.getType());
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

    @Path("/{tournamentId}/stages")
    @SecuredByToken
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BaseApiResponse getStages(@PathParam("tournamentId") @Min(1) Long tournamentId) {
        RestApiListResponse<StageDTO> restApiListResponse = new RestApiListResponse<>();

        //try {
        List<StageDTO> stageDTOs = tournamentService.getStages(tournamentId);
        restApiListResponse.setData((ArrayList<StageDTO>) stageDTOs);

        /*} catch (core.application.exception.InternalServerErrorException
                | core.application.exception.BadRequestException e) {
            e.printStackTrace();
            restApiListResponse.addDangerMessage(e.getMessage());
        }*/


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

    @Path("/{tournamentId}")
    @SecuredByToken
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateTournament(String tournamentDTOString, @PathParam("tournamentId") @NotNull @Min(1) Long tournamentId) {


        TournamentDTO tournamentDTO = gson.fromJson(tournamentDTOString, TournamentDTO.class);
        RestApiResponse<TournamentDTO> restApiMapResponse = new RestApiResponse<>();

        try {
            TournamentDTO newTournamentDTO = tournamentService.update(tournamentId, tournamentDTO);
            restApiMapResponse.setData(newTournamentDTO);

        } catch (core.application.exception.InternalServerErrorException | UnauthorizedException e) {
            e.printStackTrace();
            restApiMapResponse.addDangerMessage(e.getMessage());
            setHttpStatusCode(e.getCode());
        }

        return Response.status(getHttpStatusCode()).entity(restApiMapResponse).build();

    }

    @Path("/{tournamentId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTournament(@PathParam("tournamentId") @NotNull @Min(1) Long tournamentId) {


        RestApiResponse<TournamentDTO> restApiMapResponse = new RestApiResponse<>();

        try {
            TournamentDTO tournamentDTO = tournamentService.getById(tournamentId);
            restApiMapResponse.setData(tournamentDTO);

        } catch (core.application.exception.InternalServerErrorException e) {
            e.printStackTrace();
            restApiMapResponse.addDangerMessage(e.getMessage());
            setHttpStatusCode(e.getCode());
        }

        return Response.status(getHttpStatusCode()).entity(restApiMapResponse).build();

    }
}
