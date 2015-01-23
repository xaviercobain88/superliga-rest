package core.domain.infrastructure_service;

import core.domain.model.Player;
import core.domain.model.Team;
import core.infrastructure.exception.UnexpectedPersistenceException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ITeamRepository extends IGenericRepository<Team, Long> {

    public List<Team> findByPlayerId(Long playerId) throws UnexpectedPersistenceException;

}
