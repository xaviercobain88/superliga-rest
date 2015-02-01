package core.application.contract;

import core.application.dto.InvitationDTO;
import core.application.dto.TeamDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.domain.model.Team;

import javax.ejb.Local;
import javax.validation.constraints.Min;

@Local
public interface IInvitationService {
    TeamDTO acceptTournamentParticipation(@Min(1) Long invitationId) throws InternalServerErrorException, BadRequestException;

    boolean refuseTournamentParticipation(@Min(1) Long invitationId) throws InternalServerErrorException, BadRequestException;

    InvitationDTO load(@Min(1) Long invitationId) throws InternalServerErrorException;
}
