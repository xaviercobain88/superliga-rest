package core.infrastructure.impl;

import core.domain.contract.SecuredManageable;
import core.domain.enums.StatusEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.infrastructure_service.IPlayerRepository;
import core.domain.infrastructure_service.ITeamRepository;
import core.domain.infrastructure_service.IUserRepository;
import core.domain.model.Player;
import core.domain.model.Team;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import utils.exception.InvalidArgumentException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
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
