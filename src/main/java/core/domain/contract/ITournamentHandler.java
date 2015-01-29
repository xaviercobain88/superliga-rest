package core.domain.contract;

import core.domain.exception.InvalidArgumentsForTournamentSetupException;
import core.domain.model.Stage;
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
public interface ITournamentHandler {

    Tournament create(@Min(1) Long userId, @Valid Tournament tournament) throws UnexpectedPersistenceException;

    Stage setStages(@Min(1) Long tournamentId,  @Valid @NotEmpty List<Stage> stages)
            throws InvalidArgumentsForTournamentSetupException, UnexpectedPersistenceException;
    List<User> sendInvitations(@Min(1) Long tournamentId, @NotEmpty Set<String> emails, @NotNull User sender) throws UnexpectedPersistenceException;
}
