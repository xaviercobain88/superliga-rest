package core.domain.contract;

import core.domain.exception.DomainModelNotLoadedException;
import core.domain.exception.InvalidArgumentsForTournamentSetupException;
import core.domain.exception.InvalidInvitationException;
import core.domain.model.Stage;
import core.domain.model.Team;
import core.domain.model.Tournament;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Local;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Created by xavier on 1/25/15.
 */
@Local
public interface IInvitationHandler {
    Team acceptTournamentParticipation(@Min(1) Long invitationId)
            throws UnexpectedPersistenceException, InvalidInvitationException, DomainModelNotLoadedException;
    public void refuseTournamentParticipation(@Min(1) Long invitationId) throws UnexpectedPersistenceException, DomainModelNotLoadedException, InvalidInvitationException;
}
