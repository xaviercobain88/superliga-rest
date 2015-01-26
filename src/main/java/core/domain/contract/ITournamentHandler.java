package core.domain.contract;

import core.domain.model.Tournament;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.hibernate.annotations.Loader;

import javax.ejb.Local;
import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by xavier on 1/25/15.
 */
@Local
public interface ITournamentHandler {

    Tournament create(@Min(1)Long userId, @Valid Tournament tournament) throws UnexpectedPersistenceException;
}
