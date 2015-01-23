package core.domain.contract;

import core.domain.exception.DomainModelNotLoadedException;
import core.infrastructure.exception.UnexpectedPersistenceException;
import utils.exception.InvalidArgumentException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by xavier on 1/22/15.
 */
@Local
public interface IUserHandler {

    List<Long> getTeamIdsOfUserPlayers(Long userId) throws InvalidArgumentException, UnexpectedPersistenceException, DomainModelNotLoadedException;

}
