package core.infrastructure.impl;

import core.domain.enums.StatusEnum;
import core.domain.contract.ITeamRepository;
import core.domain.model.Team;
import core.infrastructure.exception.UnexpectedPersistenceException;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;

@Stateless
public class TeamRepository extends GenericRepository<Team, Long> implements
		ITeamRepository{

	public TeamRepository() {
		super(Team.class);
	}

	@Override
	public List<Team> findByPlayerId(Long playerId) throws UnexpectedPersistenceException {
		String jpql = "select p.team from Player p where p.id = :playerId and p.status = :status";
		HashMap<String, Object> params = new HashMap<>();
		params.put("playerId", playerId);
		StatusEnum status = StatusEnum.ACTIVE;
		params.put("status", status);
		return findByQuery(jpql, params);
	}
}
